package com.mic.news.multitype.payload;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.BaseFragment;
import com.mic.thirdparty.multitype.Items;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayloadFragment extends BaseFragment {


    public PayloadFragment() {
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
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        recyclerView.setAdapter(adapter);

        adapter.register(HeavyItem.class, new HeavyItemViewBinder());

        Items items = new Items();
        for (int i = 0; i < 30; i++) {
            items.add(new HeavyItem("1000" + i));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        Toast.makeText(getActivity(), "Try to click or long click items", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_pay_ground;
    }

}
