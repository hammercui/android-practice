package hammer.learandroid.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by hammer on 2016/8/3.
 */
public class MyPointView extends View {
    private MyPoint mCurrMyPoint;
    private ValueAnimator valueAnimator;
    public MyPointView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrMyPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300,100, mCurrMyPoint.getRadius(),paint);
        }
    }

    public void doPointAnima(){
        valueAnimator = ValueAnimator.ofObject(new MyPointEvaluator(),new MyPoint(20),new MyPoint(200));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrMyPoint = (MyPoint) animation.getAnimatedValue();
                //请求view树重绘，更新view树的显示
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(3000);//单位是毫秒1s = 1000 millisceconds
        valueAnimator.start();
    }

    public void stopPointAnima(){
        if (valueAnimator!= null)
            valueAnimator.cancel();
    }

}
