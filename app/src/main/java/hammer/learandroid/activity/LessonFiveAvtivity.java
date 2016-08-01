package hammer.learandroid.activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;


import com.hammer.example.Lesson;

import java.util.ArrayList;
import java.util.List;

import hammer.learandroid.R;
import hammer.learandroid.fragment.Five1Fragment;
import hammer.learandroid.fragment.Five2Fragment;

/**老崔的android学习第三课，学习design support library库的使用
 * Created by hammer on 2016/1/25.
 */
public class LessonFiveAvtivity extends BaseActivity {
    ActionBar actionBar;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    private List<Fragment> childfragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lesson = (Lesson) this.getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_lesson_five);

        //使用CoordinatorLayout
        rootLayout = (CoordinatorLayout)findViewById(R.id.rootLayout);
        initToolBar();
    }

    /**
     * toolbar的初始化,tool新增
     */
    public void initToolBar(){
        childfragments.add(new Five1Fragment());
        childfragments.add(new Five2Fragment());
        //使用toolBar取代actionBar，以及toolBar的使用
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle(lesson.getName());
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerToggle =  new ActionBarDrawerToggle(LessonFiveAvtivity.this,drawerLayout,R.string.drawer_open_content,R.string.drawer_close_content);
        //设置标题选项卡
        TabLayout tabLayout= (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //设置viewPage
        ViewPager viewPager = (ViewPager)this.findViewById(R.id.viewpager);
        MyTabFragmentAdapter myTabFragmentAdapter = new MyTabFragmentAdapter(this.getSupportFragmentManager(),childfragments);
        viewPager.setAdapter(myTabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(myTabFragmentAdapter);
    }


    public class MyTabFragmentAdapter extends FragmentStatePagerAdapter{
        private List<Fragment> mFragments;
        private String tabTitles[] = new String[]{"recycle试图","旋转木马试图"};

        public MyTabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyTabFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
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
