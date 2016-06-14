package hammer.learandroid.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;
import com.hammer.example.Note;
import com.hammer.example.NoteDao;
import com.jakewharton.rxbinding.view.RxView;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import hammer.learandroid.MyApplication;
import hammer.learandroid.R;
import hammer.learandroid.util.LogUtil;
import hammer.learandroid.views.six.adapters.SixAdapter;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hammer on 2016/6/13.
 */
public class LessonSixActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private EditText editText;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqlDB;
    private NoteDao noteDao;
    private ListView listView;
    private ArrayList<Note> noteLists = new ArrayList<>();
    private Cursor cursor;
    SixAdapter sixAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_six);
        daoSession = MyApplication.getIns().getDaoSession(this);
        sqlDB = MyApplication.getIns().getSQLDB(this);
        noteDao = daoSession.getNoteDao();

        editText = (EditText)this.findViewById(R.id.editTextNote);

        Button btnAdd = (Button)this.findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(view->{
            addNote();
        });
        Button btnSerach = (Button)this.findViewById(R.id.buttonSearch);
        btnSerach.setOnClickListener(view->{
            search(editText.getText().toString());
        });


        listView = (ListView)this.findViewById(R.id.listview);

//        String textColumn = NoteDao.Properties.Text.columnName;
//        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
//        cursor = sqlDB.query(getNoteDao().getTablename(),getNoteDao().getAllColumns(), null, null, null, null, orderBy);
//        String[] from = {textColumn, NoteDao.Properties.Comment.columnName};
//        int[] to = {R.id.ItemTitle, R.id.ItemText};

        //生成适配器
        sixAdapter = new SixAdapter(this,noteLists);
       // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.six_listitem,cursor,from,to);
        listView.setAdapter(sixAdapter);
        search(null);

        String[] names = {"a","aa","aaa","aaaa"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        LogUtil.Debug(name);
                    }
                });
    }


   private void addNote(){
       String noteText = editText.getText().toString();
       editText.setText("");
       final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
       String comment = "Added on " + df.format(new Date());

       // 插入操作，简单到只要你创建一个 Java 对象
       Note note = new Note(null, noteText, comment, new Date());
       getNoteDao().insert(note);
       LogUtil.Debug("Inserted new note, ID: " + note.getId());
       //
       Observable.create(subscriber->{
               noteLists.add(note);
               subscriber.onCompleted();
       }).subscribeOn(Schedulers.io()) //指定 subscribe() 发生在 IO 线程
       .observeOn(AndroidSchedulers.mainThread()) //指定 Subscriber 的回调发生在主线程
       .subscribe(getUpdateSubScriber());
   }

    private void search(String title) {
        Observable.create(subscriber->{
            //为空
            if (TextUtils.isEmpty(title)){
                // Query 类代表了一个可以被重复执行的查询
                Query<Note> query = getNoteDao().queryBuilder()
                        //  .where(NoteDao.Properties.Text.eq(editText.getText().toString()))
                        .orderAsc(NoteDao.Properties.Date)
                        .build();
                //      查询结果以 List 返回
                noteLists = (ArrayList<Note>) query.list();
            }
            else{
                Query query = getNoteDao().queryBuilder()
                        .where(NoteDao.Properties.Text.eq(title))
                        .orderAsc(NoteDao.Properties.Date)
                        .build();
                noteLists = (ArrayList<Note>) query.list();
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
         Subscriber<Object> up = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                sixAdapter.setData(noteLists);
                sixAdapter.notifyDataSetChanged();
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
        return up;
    }


    private void setupDatabase() {

    }

    private NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }

}
