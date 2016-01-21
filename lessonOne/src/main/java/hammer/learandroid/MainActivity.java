package hammer.learandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

/**
 * Created by hammer on 2016/1/19.
 */
public class MainActivity extends AppCompatActivity {
   Button btn1;
    Button btn2;
   ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.show();

        btn1 = (Button)findViewById(R.id.button_1);
        btn1.setOnClickListener((view)->{

        });

        btn2 = (Button)findViewById(R.id.button_2);
        btn2.setOnClickListener(view->{

        });


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
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
