package hammer.learandroid.fragment;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hammer.learandroid.R;
import hammer.learandroid.activity.BaseActivity;

/**
 * 属性动画的分支--ofObject evaluator的使用
 * Created by hammer on 2016/8/2.
 */
public class ObjectAnimaFragment extends BaseFragment {

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.btn_startPoint)
    Button btnStartPoint;

    @BindView(R.id.btn_cancelPoint)
    Button btnCancelPoint;

    @BindView(R.id.textview_anima)
    TextView tvAnima;

    @BindView(R.id.myPointView)
    MyPointView myPointView;


    Unbinder unbinder;

    ValueAnimator valueAnimator;
    ValueAnimator pointAnimator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (rootView == null){
           rootView = (View)inflater.inflate(R.layout.fragment_7_objectanima,null);
            initView();
       }
        ViewGroup viewGroup = (ViewGroup)this.rootView.getParent();
        if (viewGroup != null)
            viewGroup.removeView(this.rootView);


        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        unbinder =   ButterKnife.bind(this,rootView);

        //1 学习ofObject
        btnStart.setOnClickListener(view->{
            valueAnimator = ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    char text = (char)animation.getAnimatedValue();
                    tvAnima.setText(String.valueOf(text));
                }
            });
            valueAnimator.setDuration(2000);
            valueAnimator.setInterpolator(new AccelerateInterpolator());//加速差值器
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.start();
        });
        btnCancel.setOnClickListener(view->{
            if (valueAnimator != null)
                valueAnimator.cancel();
        });
        //2 ofObejct的自定义对象
        btnStartPoint.setOnClickListener(view->{
            myPointView.doPointAnima();
        });

        btnCancelPoint.setOnClickListener(view->{
            myPointView.stopPointAnima();
        });

        //3 ObjectAnimation动画，其实是对ValueAmimator的封装
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvAnima,"rotation",0,180,0);
        animator.setDuration(2000);
        animator.start();

    }

    /**
     * 字符计算器
     */
    private class CharEvaluator implements TypeEvaluator<Character>{
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int)startValue;
            int endInt = (int)endValue;
            int curInt = (int)(startInt + fraction*(endInt - startInt));
            char result = (char)curInt;
            return result;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
