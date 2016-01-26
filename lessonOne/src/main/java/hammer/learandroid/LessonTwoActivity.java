package hammer.learandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

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
        checkBox1.setChecked(false);
        btn1 = (Button)findViewById(R.id.button_1);
        //开始使用rxandroid绑定

        //绑定EditeText编辑器,判断驶入是否为空
        Observable<Boolean> editTextEmpty =  RxTextView.afterTextChangeEvents(edText1)
                .map((textViewAfterTextChangeEvent) -> {
                    textViewAfterTextChangeEvent.toString();
                    Log.d("EditText", textViewAfterTextChangeEvent.editable().toString());
                    return TextUtils.isEmpty(textViewAfterTextChangeEvent.editable());
                });

        //绑定选择器按钮，获得选择框状态
        Observable<Boolean> checkobserable =   RxCompoundButton.checkedChanges(checkBox1);
        checkobserable.subscribe(aBoolean -> {
                 String value = aBoolean ? "true" : "false";
                 Log.d("CheckBox", "status:" + value);
         });
        // 绑定Button 防止重复点击500s
        RxView.clicks(btn1).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(event -> {
            Log.d("Button", "点击了Button1次");
        });

        //关联选择器与输入框，按钮
        Observable<Boolean> objectObservable = Observable.combineLatest(editTextEmpty, checkobserable, new Func2<Boolean, Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean aBoolean1, Boolean aBoolean2) {
                if (aBoolean2)
                {
                    return  !aBoolean1;
                }
                return true;
            }
        });
        objectObservable.subscribe(aBoolean ->
            btn1.setEnabled(aBoolean));
    }
}
