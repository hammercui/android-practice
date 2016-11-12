package com.fungame.hammer.android_best_practice;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by hammer on 2016/11/12.
 */
public class UIActivity extends BaseActivity {
    public DesignButton button;
    public ImageView btn2;
    private boolean select = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui);
        button = (DesignButton) findViewById(R.id.designbutton_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               button.setSelected(!button.isSelected());
            }
        });

        btn2 = (ImageView)findViewById(R.id.button_test);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = !select;
                btn2.setBackgroundResource(select?R.drawable.react_round_read:R.drawable.react_round_alpha);
            }
        });


    }
}
