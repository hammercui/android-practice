package com.fungame.hammer.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fungame.hammer.lib.R;

/**
 * Created by hammer on 2016/11/12.
 */
public class DesignButton extends FrameLayout {
   // private ImageView background;
    private ImageView src;
    private int checkId;


    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }



    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.setBackgroundResource(selected? R.drawable.react_round_read:R.drawable.react_round_alpha);
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
        //View view = LayoutInflater.from(context).inflate(R.layout.btn_design, this, true);
        src = new ImageView(context);
        FrameLayout.LayoutParams  params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        src.setLayoutParams(params);
        this.addView(src);
        setSelected(selected);


//        background =  (ImageView)view.findViewById(R.id.designbt_background);
//        src = (ImageView)view.findViewById(R.id.designbt_src);
        if (attrs == null)
            return;

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.DesignButton);
        int dsrc = a.getResourceId(R.styleable.DesignButton_designbt_src,0);
        src.setImageResource(dsrc);

        boolean selected = a.getBoolean(R.styleable.DesignButton_designbt_selected,false);
        setSelected(selected);
        a.recycle();
    }

    /**
     * 按钮的图片
     * @param id
     */
    public void setResDrawableId(@DrawableRes int id){
        src.setImageResource(id);
    }


}
