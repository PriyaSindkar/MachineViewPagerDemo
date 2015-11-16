package ui;

/**
 * Created by priyasindkar on 16-11-2015.
 */
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priyasindkar.machineviewpagerdemo.R;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> feedItemList;
    private Context mContext;
    static int VIEW_TYPE;

    public MyAdapter(Context context, List<String> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.list_item, parent, false );
                ListViewHolder listHolder = new ListViewHolder (viewgroup1);
                return listHolder;
            case 1:
                ViewGroup viewgroup2 = ( ViewGroup ) mInflater.inflate(R.layout.grid_item, parent, false);
                GridViewHolder gridHolder = new GridViewHolder (viewgroup2);
                return gridHolder;
            default:
                ViewGroup viewgroup3 = ( ViewGroup ) mInflater.inflate ( R.layout.list_item, parent, false );
                GridViewHolder gridHolder1 = new GridViewHolder (viewgroup3);
                return gridHolder1;
        }
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                final ListViewHolder listHolder = (ListViewHolder) holder;
                listHolder.textView.setText(feedItemList.get(position));
                break;
            case 1:
                final GridViewHolder gridViewHolder = (GridViewHolder) holder;
                gridViewHolder.textView.setText(feedItemList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if(VIEW_TYPE == 0)
            return 0;
        else
            return 1;
    }

    public void setType(int type){
        VIEW_TYPE = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class ListViewHolder extends ViewHolder {
        protected TextView textView, textView1;

        public ListViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.textView1 = (TextView) view.findViewById(R.id.title1);
        }
    }

    public class GridViewHolder extends ViewHolder {
        protected TextView textView;

        public GridViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
