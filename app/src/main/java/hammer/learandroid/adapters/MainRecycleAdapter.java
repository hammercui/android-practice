package hammer.learandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammer.example.Lesson;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import hammer.learandroid.R;

/**
 * Created by hammer on 2016/8/1.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MainViewHolder> {
    private List<Lesson> lessonList;
    private Context context;
    public RVItemClickListener rvItemClickListener;
    public MainRecycleAdapter(Context context,List<Lesson> lessonList){
        this.context = context;
        this.lessonList = lessonList;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main,null));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.tvTitle.setText(lessonList.get(position).getName());
        holder.tvDesc.setText(lessonList.get(position).getDescription());
    }

    public void addRVItemClickListener(RVItemClickListener rvItemClickListener){
        this.rvItemClickListener = rvItemClickListener;
    }

    @Override
    public int getItemCount() {
        return lessonList == null?0:lessonList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview_title)
        TextView tvTitle;
        @BindView(R.id.textview_desc)
        TextView tvDesc;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(view->{
                rvItemClickListener.onClick(view,this.getAdapterPosition());
            });
        }
    }


}
