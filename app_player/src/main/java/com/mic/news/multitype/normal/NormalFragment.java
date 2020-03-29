package com.mic.news.multitype.normal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.core.BaseFragment;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalFragment extends BaseFragment {
    private MultiTypeAdapter adapter;
    private List<Object> items;

    public NormalFragment() {
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

    }

    @Override
    protected void initData() {
        RecyclerView recyclerView = rootView.findViewById(R.id.list);

        adapter = new MultiTypeAdapter();
        adapter.register(TextItem.class, new TextItemViewBinder());
        adapter.register(ImageItem.class, new ImageItemViewBinder());
        adapter.register(RichItem.class, new RichItemViewBinder());
        recyclerView.setAdapter(adapter);

        TextItem textItem = new TextItem("world");
        ImageItem imageItem = new ImageItem(R.drawable.ic_launcher);
        RichItem richItem = new RichItem("小艾大人赛高", R.drawable.img_11);

        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(textItem);
            items.add(imageItem);
            items.add(richItem);
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multitype_normal;
    }

}
