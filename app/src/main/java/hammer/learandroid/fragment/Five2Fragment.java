package hammer.learandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.library.listener.OnItemClickListener;
import com.dalong.library.listener.OnItemSelectedListener;
import com.dalong.library.view.LoopRotarySwitchView;

import java.util.logging.Logger;

import hammer.learandroid.R;
import hammer.learandroid.util.LogUtil;

/**
 * Created by hammer on 2016/5/17.
 */
public class Five2Fragment  extends Fragment {
    private View view;
    private LoopRotarySwitchView loopRotarySwitchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_five_2,null);
        initView();
        return  view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView(){
        loopRotarySwitchView = (LoopRotarySwitchView)view.findViewById(R.id.mLoopRotarySwitchView);
        loopRotarySwitchView.setR(300); //设置r的大小
        loopRotarySwitchView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int item, View view) {
                LogUtil.Debug("点击了position:" + item);
            }
        });
        loopRotarySwitchView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void selected(int item, View view) {
                LogUtil.Debug("当前选中position:" + item);
            }
        });
    }
}
