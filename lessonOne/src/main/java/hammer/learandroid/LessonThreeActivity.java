package hammer.learandroid;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;



import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import hammer.learandroid.R;
/**老崔的android学习第三课，学习design support library库的使用
 * Created by hammer on 2016/1/25.
 */
public class LessonThreeActivity extends AppCompatActivity {
    ActionBar actionBar;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        //actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);//显示返回键

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

        //使用toolBar取代actionBar，以及toolBar的使用
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home://用户按home icon的处理，返回上级activity
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
