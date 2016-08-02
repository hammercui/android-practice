package hammer.learandroid.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by hammer on 2016/8/2.
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;

    /**
     * 初始化ui
     */
    protected abstract  void initView();
}
