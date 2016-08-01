package hammer.learandroid.dao;

import android.app.DownloadManager;

import com.hammer.example.Lesson;
import com.hammer.example.LessonDao;

import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import de.greenrobot.dao.query.Query;
import hammer.learandroid.MyApplication;
import hammer.learandroid.adapters.DaoResponse;
import hammer.learandroid.util.LogUtil;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hammer on 2016/8/1.
 */
public class Lesson_Imp implements Lesson_Dao{
    private LessonDao lessonSql;

    public Lesson_Imp(){
        this.lessonSql = MyApplication.getIns().daoSession.getLessonDao();
    }

    @Override
    public void searchAll(DaoResponse<List<Lesson>> response) {
       Observable.create(subscriber -> {
            Query<Lesson> querry = lessonSql.queryBuilder().build();
            //List<Lesson> lessons = (List<Lesson>)querry.list();
            subscriber.onNext(querry.list());
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object lessons) {
                        LogUtil.Debug("查询完成");
                        response.onSuccess((List<Lesson>)lessons);
                    }
                });

    }
}
