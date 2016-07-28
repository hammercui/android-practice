package hammer.learandroid.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hammer.example.Note;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hammer.learandroid.R;
import hammer.learandroid.adapters.DaoResponse;
import hammer.learandroid.dao.Note_dao;
import hammer.learandroid.adapters.SixAdapter;
import hammer.learandroid.dao.Note_imp;

/**
 * Created by hammer on 2016/6/13.
 */
public class LessonSixActivity extends AppCompatActivity {

    @BindView(R.id.editTextNote)
    EditText editText;

    @BindView(R.id.buttonAdd)
    Button btnAdd;

    @BindView(R.id.buttonSearch)
    Button btnSerach;

    @BindView(R.id.listview)
    ListView listView;

   // private ListView listView;
    private Cursor cursor;
    private SixAdapter sixAdapter;
    private Note_dao noteDao;
    private  ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_six);
        ButterKnife.bind(this);
        noteDao = new Note_imp();

        btnAdd.setOnClickListener(view->{
            addNote();
        });

        btnSerach.setOnClickListener(view->{
            search(editText.getText().toString());
        });

        //生成适配器
        sixAdapter = new SixAdapter(this,noteDao.getAllNotes());
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
       progressDialog = ProgressDialog.show(this,"添加中","努力添加中，请稍等",true,false);
       noteDao.add(note, new DaoResponse<List<Note>>() {
           @Override
           public void onSuccess(List<Note> notes) {
               onUpdateList(notes);
               progressDialog.dismiss();
           }
           @Override
           public void onFial(String msg) {
           }
       });

   }

    private void search(String title) {
        progressDialog = ProgressDialog.show(this,"查询中","努力查询中，请稍等",true,false);
       noteDao.search(title, new DaoResponse<List<Note>>() {
           @Override
           public void onSuccess(List<Note> notes) {
               progressDialog.dismiss();
               onUpdateList(notes);
           }
           @Override
           public void onFial(String msg) {

           }
       });
    }


    public void onUpdateList(List<Note> notes) {
        sixAdapter.setData(notes);
        sixAdapter.notifyDataSetChanged();
    }
}
