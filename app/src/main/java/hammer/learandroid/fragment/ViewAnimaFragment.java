package hammer.learandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hammer.learandroid.R;

/**
 * 帧动画，补间动画
 * Created by hammer on 2016/8/2.
 */
public class ViewAnimaFragment extends BaseFragment {
    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_set)
    Button btnSet;

    @BindView(R.id.view_target)
    View viewTarget;

    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_7_viewanima,null);
        initView();
        return rootView;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this,rootView);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.1f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillBefore(true);//动画结束后，还原到开始动画前的状态
        btnAlpha.setOnClickListener(view->{
            viewTarget.startAnimation(alphaAnimation);
        });

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillBefore(true);
        btnScale.setOnClickListener(view->{
            viewTarget.startAnimation(scaleAnimation);
        });

        RotateAnimation rotateAnimation = new RotateAnimation(0,-650,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);
        scaleAnimation.setFillBefore(true);
        btnRotate.setOnClickListener(view->{
            viewTarget.startAnimation(rotateAnimation);
        });

        TranslateAnimation translateAnimation =  new TranslateAnimation(Animation.ABSOLUTE,0,Animation.ABSOLUTE,-80,Animation.ABSOLUTE,0,Animation.ABSOLUTE,80);
        translateAnimation.setDuration(3000);
        scaleAnimation.setFillBefore(true);
        btnTranslate.setOnClickListener(view->{
            viewTarget.startAnimation(translateAnimation);
        });

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);

        animationSet.setDuration(3000);
        animationSet.setFillBefore(true);
        btnSet.setOnClickListener(view->{
            viewTarget.startAnimation(animationSet);
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
