package com.mic.news.multitype.multiselectable;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.core.BaseFragment;
import com.mic.news.multitype.common.Category;
import com.mic.news.multitype.common.CategoryItemViewBinder;
import com.mic.core.thirdparty.multitype.Items;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;

import java.util.TreeSet;

import static com.mic.core.thirdparty.multitype.MultiTypeAsserts.assertAllRegistered;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiSelectableFragment extends BaseFragment {

    private static final int SPAN_COUNT = 5;
    Items items = new Items();
    MultiTypeAdapter adapter;
    Button fab;
    private TreeSet<Integer> selectedSet;
    public MultiSelectableFragment() {
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
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (items.get(position) instanceof Category) ? SPAN_COUNT : 1;
            }
        });

        selectedSet = new TreeSet<>();

        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(Category.class, new CategoryItemViewBinder());
        adapter.register(Square.class, new SquareViewBinder(selectedSet));

        loadData();

        assertAllRegistered(adapter, items);
        recyclerView.setAdapter(adapter);

    }


    private void loadData() {
        Category spacialCategory = new Category("特别篇");
        items.add(spacialCategory);
        for (int i = 0; i < 7; i++) {
            items.add(new Square(i + 1));
        }
        Category currentCategory = new Category("本篇");
        items.add(currentCategory);
        for (int i = 0; i < 1000; i++) {
            items.add(new Square(i + 1));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
        setupFAB();
    }

    private void setupFAB() {
        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            StringBuilder content = new StringBuilder();
            for (Integer number : selectedSet) {
                content.append(number).append(" ");
            }
            Toast.makeText(v.getContext(),
                    "Selected items: " + content, Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multi_selectable;
    }

}
