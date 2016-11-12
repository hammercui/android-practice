package com.fungame.hammer.android_best_practice;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的选择器
 * Created by hammer on 2016/11/12.
 */
public class DesignCheckBox extends LinearLayout{
    private List<ImageView> imageViewList;
    private Context context;
    public DesignCheckBox(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 初始化视图
     * @param resids
     */
    public void initView(int[]  resids){
        int num = resids.length;
        imageViewList = new ArrayList<>();
        float density = ((BaseActivity)context).getDensity();
        int marginLR = (int)density*20;
        int marginTP = (int)density*10;

        //考虑学习Ucrop的适配器做法
        for (int i=0;i<num;i++){
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(marginLR,marginTP,marginTP,marginLR);
            imageView.setLayoutParams(params);
            imageView.setOnClickListener(new CheckListener());

            this.addView(imageView);
            imageViewList.add(imageView);
        }
    }

    private class CheckListener implements OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }


}
