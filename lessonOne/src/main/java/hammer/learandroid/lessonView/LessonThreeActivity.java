package hammer.learandroid.lessonView;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import hammer.learandroid.R;
import hammer.learandroid.util.LogUtil;

/**老崔的android学习第三课，学习design support library库的使用
 * Created by hammer on 2016/1/25.
 */
public class LessonThreeActivity extends AppCompatActivity {
    ActionBar actionBar;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);

        //使用CoordinatorLayout
        rootLayout = (CoordinatorLayout)findViewById(R.id.rootLayout);
        //使用fab 推荐40dp的文件
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab_1);
        RxView.clicks(fab1).throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(view -> {
                    LogUtil.Debug("点击了fab");
                    //使用snackbar
                    Snackbar.make(rootLayout,"点击了fab1",Snackbar.LENGTH_LONG).setAction("取消",v->{
                        LogUtil.Debug("点击了取消");
                    }).show();
                });

       initToolBar();
    }

    /**
     * toolbar的初始化,tool新增
     */
    public void initToolBar(){
        //使用toolBar取代actionBar，以及toolBar的使用
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerToggle =  new ActionBarDrawerToggle(LessonThreeActivity.this,drawerLayout,R.string.drawer_open_content,R.string.drawer_close_content);
        //设置标题选项卡
        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabLayout);
        for (int i=0;i<8;i++)
            tabLayout.addTab(tabLayout.newTab().setText("选项卡："+i));

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new MyRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class MyViewHolder extends  RecyclerView.ViewHolder{
            public TextView textView;
            public MyViewHolder(View itemView)
            {
                super(itemView);
                textView = (TextView)itemView.findViewById(android.R.id.text1);
            }
    }

    public class MyRecyclerViewAdapter extends  RecyclerView.Adapter<MyViewHolder>{
        private LayoutInflater layoutInflater;
        public MyRecyclerViewAdapter(Activity activity)
        {
            super();
            layoutInflater = activity.getLayoutInflater();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText("数据position:" + position);
        }

        @Override
        public int getItemCount() {
            return 40;
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
