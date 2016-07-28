package hammer.learandroid.models.dao;

import android.text.TextUtils;

import com.hammer.example.DaoSession;
import com.hammer.example.Note;
import com.hammer.example.NoteDao;

import java.util.ArrayList;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import hammer.learandroid.util.LogUtil;
import hammer.learandroid.adapters.ILessonSixActvity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hammer on 2016/6/15.
 */
public class NoteDaoMiddle {
    private NoteDao noteDao;
    private ILessonSixActvity lessonSixActvity;
    public  ArrayList<Note> notes = new ArrayList<>();

    public NoteDaoMiddle(ILessonSixActvity lessonSixActvity, DaoSession daoSession){
        this.lessonSixActvity = lessonSixActvity;
        this.noteDao = daoSession.getNoteDao();
    }


    public void add(Note note){
        Observable.create(subscriber->{
            noteDao.insert(note);
            notes.add(note);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io()) //指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) //指定 Subscriber 的回调发生在主线程
                .subscribe(getUpdateSubScriber());
    }

    public void search(String title){
        Observable.create(subscriber->{
            //为空
            if (TextUtils.isEmpty(title)){
                // Query 类代表了一个可以被重复执行的查询
                Query<Note> query = noteDao.queryBuilder()
                        .orderAsc(NoteDao.Properties.Date)
                        .build();
                //      查询结果以 List 返回
                notes = (ArrayList<Note>) query.list();
            }
            else{
                Query query = noteDao.queryBuilder()
                        .where(NoteDao.Properties.Text.eq(title))
                        .orderAsc(NoteDao.Properties.Date)
                        .build();
                notes = (ArrayList<Note>) query.list();
            }

            // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
            subscriber.onCompleted();
        })
                .subscribeOn(Schedulers.io()) //指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) //指定 Subscriber 的回调发生在主线程
                .subscribe(getUpdateSubScriber());
    }


    private Subscriber<Object> getUpdateSubScriber(){

        return  new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                lessonSixActvity.onUpdateList(notes);
                LogUtil.Debug("onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                LogUtil.Debug("onError:"+e.toString());
            }

            @Override
            public void onNext(Object o) {
            }
        };
    }
}
