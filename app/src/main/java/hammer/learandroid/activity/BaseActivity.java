package hammer.learandroid.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.hammer.example.Lesson;

/**
 * Created by hammer on 2016/8/1.
 */
public class BaseActivity extends AppCompatActivity {
    protected Lesson lesson;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
