package com.mic.news.multitype.bilibili;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.BaseFragment;
import com.mic.news.multitype.common.Category;
import com.mic.news.multitype.common.CategoryItemViewBinder;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BilibiliFragment extends BaseFragment {

    private static final int SPAN_COUNT = 2;
    @VisibleForTesting
    private List<Object> items;
    @VisibleForTesting
    private MultiTypeAdapter adapter;
    private RecyclerView recyclerView;

    public BilibiliFragment() {
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
    }

    @Override
    protected void initData() {
        adapter = new MultiTypeAdapter();
        adapter.register(Category.class,new CategoryItemViewBinder());
        adapter.register(Post.class,new PostViewBinder());
        adapter.register(PostList.class,new HorizontalPostsViewBinder());

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(),SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = items.get(position);
                return (item instanceof PostList || item instanceof Category) ? SPAN_COUNT : 1;
            }
        };

        layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setLayoutManager(layoutManager);
        int space = getResources().getDimensionPixelSize(R.dimen.normal_space);
        recyclerView.addItemDecoration(new PostItemDecoration(space, spanSizeLookup));
        recyclerView.setAdapter(adapter);

        JsonData data = new JsonData();
        items = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            /* You also could use Category as your CategoryItemContent directly */
            items.add(data.category0);
            items.add(data.postArray[0]);
            items.add(data.postArray[1]);
//            items.add(data.postArray[2]);
//            items.add(data.postArray[3]);
//            items.add(data.postArray[0]);
//            items.add(data.postArray[1]);
            items.add(new PostList(data.postList));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bilibili;
    }

    private static class JsonData {

        private static final String PREFIX = "这是一条长长的达到两行的标题文字";

        private Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
        private Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
        private Post post10 = new Post(R.drawable.img_10, PREFIX + "post10");
        private Post post11 = new Post(R.drawable.img_11, PREFIX + "post11");

        Category category0 = new Category("title0");
        Post[] postArray = { post00, post01, post10, post11 };

        List<Post> postList = new ArrayList<>();

        {
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
            postList.add(post00);
        }
    }

}
