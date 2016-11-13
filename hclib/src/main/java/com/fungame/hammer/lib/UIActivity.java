package com.fungame.hammer.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.fungame.hammer.lib.widget.DesignButton;
import com.fungame.hammer.lib.widget.DesignCheckBox;
import com.fungame.hammer.lib.widget.DesignCheckListener;

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

//        button = (DesignButton) findViewById(R.id.designbutton_test);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               button.setSelected(!button.isSelected());
//            }
//        });
//
//        btn2 = (ImageView)findViewById(R.id.button_test);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                select = !select;
//                btn2.setBackgroundResource(select?R.drawable.react_round_read:R.drawable.react_round_alpha);
//            }
//        });


        DesignCheckBox checkBox = (DesignCheckBox) findViewById(R.id.designchecckbox1);
//        int[] res = {R.drawable.ucrop_ic_cross,R.drawable.ucrop_ic_done,R.drawable.ucrop_ic_next,R.drawable.ucrop_ic_reset};
//
        checkBox.initCheckListener(new DesignCheckListener() {
            @Override
            public void onChecked(int id) {
                Log.d("测试","选中了："+id);

            }
        },1);


    }
}
