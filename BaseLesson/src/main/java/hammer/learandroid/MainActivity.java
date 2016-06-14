package hammer.learandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import hammer.learandroid.views.LessonFiveAvtivity;
import hammer.learandroid.views.LessonOne;
import hammer.learandroid.views.LessonSixActivity;
import hammer.learandroid.views.LessonThreeActivity;
import hammer.learandroid.views.LessonTwoActivity;

/**
 * Created by hammer on 2016/1/19.
 */
public class MainActivity extends AppCompatActivity {
   Button btn1;
    Button btn2;
   ActionBar actionBar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.show();

        LessonOne popViewOne = new LessonOne(this);

        btn1 = (Button)findViewById(R.id.button_1);
        btn1.setOnClickListener((view) -> {
            popViewOne.show();
        });
        btn2 = (Button)findViewById(R.id.button_2);
        RxView.clicks(btn2).subscribe(view -> {
            startActivity(new Intent(this, LessonTwoActivity.class));
        });
        Button btn3 = (Button)findViewById(R.id.button_3);
        RxView.clicks(btn3).subscribe(view->{
            startActivity(new Intent(this,LessonThreeActivity.class));
        });
        Button btn4 = (Button)findViewById(R.id.button_4);
        RxView.clicks(btn4).subscribe(view->{

        });

        Button btn5 = (Button)findViewById(R.id.button_5);
        RxView.clicks(btn5).subscribe(view->{
            startActivity(new Intent(this,LessonFiveAvtivity.class));
        });

        Button btn6 = (Button)findViewById(R.id.button_6);
        RxView.clicks(btn6).subscribe(view->{
            startActivity(new Intent(this,LessonSixActivity.class));
        });

        //学习DrawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open_content, R.string.drawer_close_content);
        drawerLayout.setDrawerListener(drawerToggle);

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
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
