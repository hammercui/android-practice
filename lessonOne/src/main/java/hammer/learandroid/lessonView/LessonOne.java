package hammer.learandroid.lessonView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hammer.learandroid.R;

/**弹出页面1
 * Created by hammer on 2016/1/19.
 */
    public class LessonOne {
    /**
     * 上下文，存储activity信息
     */
    private Context context;
    private ViewGroup decorView; //decorView
    private ViewGroup activityRootView;//内容区域的根视图
    private ViewGroup rootView;//我的根视图
    Activity  one;
    ViewGroup contentContainer;
        /**
         * 构造函数
         * @param context
         */
        public LessonOne(Context context)
        {
            //获得一个xml布局加载器
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //用加载器加载一个xml布局
            View view = layoutInflater.inflate(R.layout.activity_lesson_one, contentContainer);
            //获得decorView
            decorView = (ViewGroup)((Activity)context).getWindow().getDecorView();
            Log.d("decorView count", decorView.getChildCount()+"") ;
            //获得内容区域根视图
            activityRootView = (ViewGroup)decorView.findViewById(android.R.id.content);
            Log.d("activityRootView count", activityRootView.getChildCount()+"") ;
            //获得我的根视图
            rootView = (ViewGroup)layoutInflater.inflate(R.layout.activity_lesson_one,null);
            Log.d("rootView count", rootView.getChildCount() + "") ;

            //屏蔽下层触摸
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("lessonOneActivity","点击了本层");
                }
            });
            //关闭本层
            Button btn_close = (Button)rootView.findViewById(R.id.button_1);
            btn_close.setOnClickListener(v ->{
                activityRootView.removeView(rootView);
            });

        }
        public void show(){
            activityRootView.addView(rootView);
            Log.d("decorView count", decorView.getChildCount() + "") ;
            Log.d("activityRootView count", activityRootView.getChildCount()+"") ;
        }


    }
