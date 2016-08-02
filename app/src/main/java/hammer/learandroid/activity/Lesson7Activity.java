package hammer.learandroid.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hammer.example.Lesson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hammer.learandroid.R;
import hammer.learandroid.fragment.Five1Fragment;
import hammer.learandroid.fragment.Five2Fragment;
import hammer.learandroid.fragment.ObjectAnimaFragment;
import hammer.learandroid.fragment.PropertyAnimaFragment;
import hammer.learandroid.fragment.ViewAnimaFragment;

/**
 * Created by hammer on 2016/7/14.
 */
public class Lesson7Activity extends BaseActivity {

   @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private List<Fragment> childfragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lesson = (Lesson) getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_lesson_7);
        ButterKnife.bind(this);

        childfragments.add(new ViewAnimaFragment());
        childfragments.add(new PropertyAnimaFragment());
        childfragments.add(new ObjectAnimaFragment());

        //init toobar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(lesson.getName());

        //init  tabbar
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        MyTabFragmentAdapter tabFragmentAdapter = new MyTabFragmentAdapter(this.getSupportFragmentManager(),childfragments);
        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private class MyTabFragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;
        private String tabTitles[] = new String[]{"View动画","属性动画","属性动画2：计算器的使用"};

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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
