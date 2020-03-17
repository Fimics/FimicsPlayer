package com.mic.news.multitype.weibo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.BaseFragment;
import com.mic.news.multitype.weibo.content.SimpleImage;
import com.mic.news.multitype.weibo.content.SimpleImageViewBinder;
import com.mic.news.multitype.weibo.content.SimpleText;
import com.mic.news.multitype.weibo.content.SimpleTextViewBinder;
import com.mic.thirdparty.multitype.Items;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import java.util.List;

import static com.mic.thirdparty.multitype.MultiTypeAsserts.assertAllRegistered;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiboFragment extends BaseFragment {
    private MultiTypeAdapter adapter;
    private Items items;

    /* @formatter:off */
    private static final String JSON_FROM_SERVICE =
            "[\n" +
                    "    {\n" +
                    "        \"content\":{\n" +
                    "            \"text\":\"A simple text Weibo: JSON_FROM_SERVICE.\",\n" +
                    "            \"content_type\":\"simple_text\"\n" +
                    "        },\n" +
                    "        \"createTime\":\"Just now\",\n" +
                    "        \"user\":{\n" +
                    "            \"avatar\":$avatar,\n" +
                    "            \"name\":\"drakeet\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"content\":{\n" +
                    "            \"resId\":$content,\n" +
                    "            \"content_type\":\"simple_image\"\n" +
                    "        },\n" +
                    "        \"createTime\":\"Just now(JSON_FROM_SERVICE)\",\n" +
                    "        \"user\":{\n" +
                    "            \"avatar\":$avatar,\n" +
                    "            \"name\":\"drakeet\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "]";
    /* @formatter:on */

    public WeiboFragment() {
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

        adapter.register(Weibo.class).to(
                new SimpleTextViewBinder(),
                new SimpleImageViewBinder()
        ).withLinker((position, weibo) -> {
            if (weibo.content instanceof SimpleText) {
                return 0;
            } else if (weibo.content instanceof SimpleImage) {
                return 1;
            }
            return 0;
        });

        recyclerView.setAdapter(adapter);

        items = new Items();

        User user = new User("drakeet", R.drawable.avatar_drakeet);
        SimpleText simpleText = new SimpleText("A simple text Weibo: Hello World.");
        SimpleImage simpleImage = new SimpleImage(R.drawable.img_10);
        for (int i = 0; i < 20; i++) {
            items.add(new Weibo(user, simpleText));
            items.add(new Weibo(user, simpleImage));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        assertAllRegistered(adapter, items);

        loadRemoteData();
    }

    private void loadRemoteData() {
        List<Weibo> weiboList = WeiboJsonParser.fromJson(JSON_FROM_SERVICE
                .replace("$avatar", "" + R.drawable.avatar_drakeet)
                .replace("$content", "" + R.drawable.img_00));
        // atomically
        items = new Items(items);
        items.addAll(0, weiboList);
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weibo;
    }

}
