package hammer.learandroid.views.five;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hammer.learandroid.R;

/**
 * Created by hammer on 2016/5/17.
 */
public class Five1Fragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = (View)inflater.inflate(R.layout.fragment_five_1,null);
        initView();
        return  view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initView(){
        //设置RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new MyRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }


    private class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView)itemView.findViewById(android.R.id.text1);
        }
    }
    public class MyRecyclerViewAdapter extends  RecyclerView.Adapter<MyViewHolder>{
        private LayoutInflater layoutInflater;
        public MyRecyclerViewAdapter(Activity activity)
        {
            super();
            layoutInflater = activity.getLayoutInflater();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText("数据position:" + position);
        }

        @Override
        public int getItemCount() {
            return 40;
        }
    }
}
