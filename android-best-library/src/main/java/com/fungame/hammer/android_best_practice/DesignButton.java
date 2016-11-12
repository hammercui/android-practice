package com.fungame.hammer.android_best_practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by hammer on 2016/11/12.
 */
public class DesignButton extends FrameLayout {
    private ImageView background;
    private ImageView src;

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        background.setVisibility(selected?VISIBLE:INVISIBLE);
    }

    private boolean selected = false;
    @Override
    public boolean isSelected() {
        return selected;
    }


    public DesignButton(Context context) {
        super(context);
        initView(context,null);

    }

    public DesignButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public void initView(Context context,AttributeSet attrs){
        View view = LayoutInflater.from(context).inflate(R.layout.btn_design, this, true);
        background =  (ImageView)view.findViewById(R.id.designbt_background);
        src = (ImageView)view.findViewById(R.id.designbt_src);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.DesignButton);
        int db = a.getResourceId(R.styleable.DesignButton_designbt_background,0);
        int dsrc = a.getResourceId(R.styleable.DesignButton_designbt_src,0);
        background.setImageResource(db);
        src.setImageResource(dsrc);


        setSelected(false);
        a.recycle();
    }


}
