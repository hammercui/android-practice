package hammer.learandroid.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;
import com.hammer.example.Note;
import com.hammer.example.NoteDao;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import hammer.learandroid.MyApplication;
import hammer.learandroid.R;
import hammer.learandroid.models.dao.NoteDaoMiddle;
import hammer.learandroid.adapters.ILessonSixActvity;
import hammer.learandroid.adapters.SixAdapter;

/**
 * Created by hammer on 2016/6/13.
 */
public class LessonSixActivity extends AppCompatActivity implements ILessonSixActvity {
    private SQLiteDatabase db;
    private EditText editText;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqlDB;
    private NoteDao noteDao;
    private ListView listView;
    private Cursor cursor;
    private SixAdapter sixAdapter;
    private NoteDaoMiddle noteDaoMiddle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_six);
        daoSession = MyApplication.getIns().getDaoSession(this);
        noteDaoMiddle = new NoteDaoMiddle(this,daoSession);

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
        //生成适配器
        sixAdapter = new SixAdapter(this,noteDaoMiddle.notes);
        listView.setAdapter(sixAdapter);
        search(null);
    }


   private void addNote(){
       //如果新增为空，弹出
       if (TextUtils.isEmpty(editText.getText())){
           Toast.makeText(this,"add不能为空",Toast.LENGTH_SHORT);
           return;
       }

       String noteText = editText.getText().toString();
       final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
       String comment = "Added on " + df.format(new Date());
       // 插入操作，简单到只要你创建一个 Java 对象
       Note note = new Note(null, noteText, comment, new Date());
       noteDaoMiddle.add(note);

   }

    private void search(String title) {
       noteDaoMiddle.search(title);
    }



    @Override
    public void onUpdateList(ArrayList<Note> notes) {
        sixAdapter.setData(notes);
        sixAdapter.notifyDataSetChanged();
    }
}
