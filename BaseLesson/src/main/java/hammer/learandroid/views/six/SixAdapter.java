package hammer.learandroid.views.six;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hammer.example.Note;

import java.util.ArrayList;

import hammer.learandroid.R;
import hammer.learandroid.util.LogUtil;

/**
 * Created by hammer on 2016/6/14.
 */
public class SixAdapter extends BaseAdapter{
    private ArrayList<Note> noteLists;
    private LayoutInflater layoutInflater;

    public SixAdapter(Context context,ArrayList<Note> lists){
        this.layoutInflater = LayoutInflater.from(context);
        noteLists = lists;
    }

    public void setData(ArrayList<Note> lists){
        noteLists = lists;
    }

    @Override
    public int getCount() {
       // LogUtil.Debug("SixAdapter getCount:"+noteLists.size());
        return noteLists.size();
    }

    @Override
    public Object getItem(int position) {
        return noteLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView  = layoutInflater.inflate(R.layout.six_listitem,null);

        TextView title = (TextView)convertView.findViewById(R.id.ItemTitle);
        TextView content = (TextView)convertView.findViewById(R.id.ItemText);

        title.setText(noteLists.get(position).getText());
        content.setText(noteLists.get(position).getComment());

        return convertView;
    }
}
