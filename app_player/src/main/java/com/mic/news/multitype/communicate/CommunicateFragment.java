package com.mic.news.multitype.communicate;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.BaseFragment;
import com.mic.news.multitype.normal.TextItem;
import com.mic.thirdparty.multitype.Items;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import static com.mic.thirdparty.multitype.MultiTypeAsserts.assertAllRegistered;
import static com.mic.thirdparty.multitype.MultiTypeAsserts.assertHasTheSameAdapter;
import static java.lang.String.valueOf;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunicateFragment extends BaseFragment {

    private String aFieldValue = "aFieldValue of SimpleActivity";
    private MultiTypeAdapter adapter;
    public CommunicateFragment() {
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

        Items items = new Items();
        adapter = new MultiTypeAdapter();
        adapter.register(TextItem.class, new TextItemWithOutsizeDataViewBinder(aFieldValue));
        recyclerView.setAdapter(adapter);
        assertHasTheSameAdapter(recyclerView, adapter);

        for (int i = 0; i < 20; i++) {
            items.add(new TextItem(valueOf(i)));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        assertAllRegistered(adapter, items);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_communicate;
    }

}
