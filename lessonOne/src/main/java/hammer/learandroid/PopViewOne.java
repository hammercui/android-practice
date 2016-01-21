package hammer.learandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**弹出页面1
 * Created by hammer on 2016/1/19.
 */
    public class PopViewOne {
    /**
     * 上下文，存储activity信息
     */
    private Context context;
    private ViewGroup decorView; //decorView
    private ViewGroup activityRootView;//activity内容区域的根视图
    private ViewGroup rootView;//我的根视图
    Activity  one;
    ViewGroup contentContainer;
        /**
         * 构造函数
         * @param context
         */
        public PopViewOne(Context context)
        {
            //获得一个xml布局加载器
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //用加载器加载一个xml布局
            View view = layoutInflater.inflate(R.layout.activity_pop_window, contentContainer);
            //获得decorView

        }


    }
