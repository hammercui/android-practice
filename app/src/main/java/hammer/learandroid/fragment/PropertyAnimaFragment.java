package hammer.learandroid.fragment;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import hammer.learandroid.R;
import hammer.learandroid.util.LogUtil;

/**
 * 属性动画
 * Created by hammer on 2016/8/2.
 */
public class PropertyAnimaFragment extends BaseFragment {

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.view_target)
    View viewTarget;

    ValueAnimator valueAnimator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (this.rootView == null){
            rootView = inflater.inflate(R.layout.fragment_7_propertyanima,null);
            initView();
        }
        ViewGroup viewGroup = (ViewGroup) this.rootView.getParent();
        if (viewGroup!= null)
            viewGroup.removeView(this.rootView);

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this,rootView);
        btnStart.setOnClickListener(view->{
                valueAnimator = doAnimatorListener();
        });

        btnCancel.setOnClickListener(view->{
                if (valueAnimator != null)
                    valueAnimator.cancel();
        });

        viewTarget.setOnClickListener(view->{
            Toast.makeText(this.getContext(),"点击了view",Toast.LENGTH_SHORT).show();
            LogUtil.Debug("点击了我");
        });

    }

    private ValueAnimator doAnimatorListener(){
        ValueAnimator valueAnimator = new ValueAnimator().ofInt(0,800);
        valueAnimator.setDuration(1000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                viewTarget.layout(viewTarget.getLeft(),curValue,viewTarget.getRight(),viewTarget.getHeight()+curValue);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LogUtil.Debug("start animation");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtil.Debug("end animation");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogUtil.Debug("cancel animation");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogUtil.Debug("repeat animation");
            }
        });

        //设置循环次数INFINITE：无限
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置循环模式RESTART：正序开始，REVERSE：倒序开始
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();

        return valueAnimator;
    }
}
