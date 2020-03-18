package com.mic.news.multitype.one2many;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.BaseFragment;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneToManyFragment extends BaseFragment {

    @VisibleForTesting
    RecyclerView recyclerView;
    @VisibleForTesting
    MultiTypeAdapter adapter;

    public OneToManyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView() {
        recyclerView = rootView.findViewById(R.id.list);
        adapter = new MultiTypeAdapter();
    }

    @Override
    protected void initData() {
        adapter.register(Data.class).to(
                new DataType1ViewBinder(),
                new DataType2ViewBinder()
        ).withClassLinker((position, data) -> {
            if (data.type == Data.TYPE_2) {
                return DataType2ViewBinder.class;
            } else {
                return DataType1ViewBinder.class;
            }
        });

        List<Data> dataList = getDataFromService();
        adapter.setItems(dataList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multitype_normal;
    }

    @VisibleForTesting
    List<Data> getDataFromService() {
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < 30; i = i + 2) {
            list.add(new Data("title: " + i, Data.TYPE_1));
            list.add(new Data("title: " + i + 1, Data.TYPE_2));
        }
        return list;
    }

}
