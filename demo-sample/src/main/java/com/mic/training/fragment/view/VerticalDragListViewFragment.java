package com.mic.training.fragment.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mic.training.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerticalDragListViewFragment extends Fragment {


    private ListView mListView;
    private List<String> mItems;

    public VerticalDragListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_vertical_drag_list_view, container, false);
        mListView=view.findViewById(R.id.list_view);
        initListView(inflater);
        return view;
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
