package hammer.learandroid.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.hammer.example.Lesson;

import hammer.learandroid.R;

/**第四课，创建带背景的滚动toolbar,可隐藏小时
 * Created by hammer on 2016/1/26.
 */
public class LessonFourActivity extends BaseActivity {
    CardView cardView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lesson = (Lesson) this.getIntent().getSerializableExtra("data");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lesson_four);
        initToolBar();
    }

    private void initToolBar()
    {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle(lesson.getName());

        //设置CollapasingToolbarv标题
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar);
        collapsingToolbarLayout.setTitle("我是可滚动的，嘿嘿");
    }

    /**
     * 创建toolbar的菜单项
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * toolbar菜单项点击的回掉
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            this.finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
