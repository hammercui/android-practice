package com.fungame.hammer.lib;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by hammer on 2016/11/12.
 */
public class BaseActivity extends AppCompatActivity {
    public float density = 0;

    public float getDensity(){
        if (density == 0){
            DisplayMetrics dm = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(dm);
//            int width=dm.widthPixels;
//            int height=dm.heightPixels;
            density = dm.density;
        }
        return density;
    }
}
