package hammer.learandroid.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.hammer.example.Lesson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hammer.learandroid.R;
import hammer.learandroid.adapters.DaoResponse;
import hammer.learandroid.adapters.MainRecycleAdapter;
import hammer.learandroid.dao.Lesson_Dao;
import hammer.learandroid.dao.Lesson_Imp;
import hammer.learandroid.util.LogUtil;
import hammer.learandroid.widget.DividerItem;

/**
 * Created by hammer on 2016/1/19.
 */
public class MainActivity extends BaseActivity {


    ActionBar actionBar;
    ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;



    @BindView(R.id.recycleview_main)
    public RecyclerView recyclerView;

    Lesson_Dao lesson_dao;
    List<Lesson> lessons;

    Class<?>[] classes = {
        LessonOneView.class,
            LessonTwoActivity.class,
            LessonThreeActivity.class,
            LessonFourActivity.class,
            LessonFiveAvtivity.class,
            LessonSixActivity.class,
            Lesson7Activity.class
    }  ;

    private LessonOneView lessonOneView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        lesson_dao = new Lesson_Imp();
        lessonOneView = new LessonOneView(this);
        lesson_dao.searchAll(new DaoResponse<List<Lesson>>() {
            @Override
            public void onSuccess(List<Lesson> lessonList) {
                lessons = lessonList;
                initView();
            }

            @Override
            public void onFial(String msg) {
                LogUtil.Debug("lesson查询出错");
            }
        });

        actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setTitle("android课程");

        //学习DrawerLayout
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open_content, R.string.drawer_close_content);
        drawerLayout.setDrawerListener(drawerToggle);

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void initView(){
        if (lessons == null)
            return;

        //创建recycleview
        //线性布局有个问题，不能在column中填充满，所以采用grid布局，每行就1列
        recyclerView.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
        MainRecycleAdapter adapter = new MainRecycleAdapter(this,lessons);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItem(this,LinearLayoutManager.VERTICAL));
        adapter.addRVItemClickListener((v,position)->{
           // LogUtil.Debug("点击了postion:"+position);
            if (position == 0)
            {
                lessonOneView.show();
                return;
            }
            Intent intent = new Intent(this,classes[position]);
            intent.putExtra("data",lessons.get(position));
            startActivity(intent);

        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//添加菜单项
        MenuItem add=menu.add(0,0,0,"add");
        MenuItem del=menu.add(0,0,0,"del");
        MenuItem save=menu.add(0,0,0,"save");
//绑定到ActionBar
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        del.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        save.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
