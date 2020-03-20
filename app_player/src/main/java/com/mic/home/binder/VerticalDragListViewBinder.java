package com.mic.home.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;

import java.util.ArrayList;
import java.util.List;

public class VerticalDragListViewBinder extends ItemViewBinder<AndroidUI, VerticalDragListViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_vertical_drag_list_view,parent,false),inflater);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ListView mListView;
        private List<String> mItems;
        private LayoutInflater inflater;

        public ViewHolder(@NonNull View itemView, LayoutInflater inflater) {
            super(itemView);
            this.inflater = inflater;
            mListView=itemView.findViewById(R.id.list_view);
            initListView(inflater);
        }

        private void initListView(final LayoutInflater inflater){
            mItems = new ArrayList<String>();

            for (int i=0;i<200;i++){
                mItems.add("i -> "+i);
            }

            mListView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return mItems.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView item = (TextView) inflater.inflate(R.layout.item_lv, parent, false);
                    item.setText(mItems.get(position));
                    return item;
                }
            });
        }
    }
}
