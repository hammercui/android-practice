package hammer.learandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**使用rxandroid绑定控件
 * Created by hammer on 2016/1/22.
 */
public class LessonTwoActivity extends AppCompatActivity{
    EditText edText1;
    CheckBox checkBox1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lesson_two);
        edText1 =(EditText)findViewById(R.id.editText_1);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox_1);
        btn1 = (Button)findViewById(R.id.button_1);
        //开始使用rxandroid绑定
        // 绑定Button 防止重复点击500s
        RxView.clicks(btn1).throttleFirst(500,TimeUnit.MILLISECONDS).subscribe(event->{

        });
        
        Func1<OnCheckedChangeListener,Boolean> function = new Func1<OnCheckedChangeListener, Boolean>() {
            @Override
            public Boolean call(OnCheckedChangeListener onCheckedChangeListener) {
                return true;
            }
        };
        //绑定EditeText编辑器
        RxTextView.afterTextChangeEvents(edText1)
                .map(new Func1<TextViewAfterTextChangeEvent, Boolean>() {
            @Override
            public Boolean call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {

            }
        });



    }
}
