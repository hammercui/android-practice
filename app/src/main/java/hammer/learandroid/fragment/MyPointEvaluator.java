package hammer.learandroid.fragment;

import android.animation.TypeEvaluator;

/**
 * point计算器
 * Created by hammer on 2016/8/3.
 */
public class MyPointEvaluator implements TypeEvaluator<MyPoint> {
    @Override
    public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
        int start = startValue.getRadius();
        int end = endValue.getRadius();
        int currValue = (int)(start + (end - start)* fraction);
        return  new MyPoint(currValue);
    }
}
