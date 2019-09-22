package com.mic.xsample.fragment.view;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mic.customview.taglayout.TagAdapter;
import com.mic.customview.taglayout.TagLayout;
import com.mic.xsample.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TagLayoutFragment extends Fragment {

    private TagLayout mTagLayout;


    public TagLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tag, container, false);
        mTagLayout=view.findViewById(R.id.tag_layout);
        initDataAndAdapter();
        return view;
    }


    private void initDataAndAdapter(){

        List<String> list = new ArrayList<String>();
        list.add("黑龙江");
        list.add("辽宁");
        list.add("吉林");
        list.add("内蒙古");
        list.add("河北");
        list.add("天津");
        list.add("北京");
        list.add("日本");
        list.add("河南");
        list.add("山东");
        list.add("山西");
        list.add("安徽");
        list.add("江苏");
        list.add("浙江");
        list.add("上海");
        list.add("江西");
        list.add("湖南");
        list.add("湖北");
        list.add("四川");
        list.add("重庆");
        list.add("广西");
        list.add("广东");
        list.add("云南");
        list.add("贵州");
        list.add("新疆");
        list.add("清海");
        list.add("宁夏");
        list.add("甘肃");
        list.add("海南");
        list.add("西藏");
        list.add("香港");
        list.add("奥门");
        list.add("陕西");
        XTagAdapter adapter = new XTagAdapter(list,getActivity());
        mTagLayout.setTagAdapter(adapter);

        mTagLayout.setOnTagItemClickListener(new TagLayout.OnTagItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                TextView textView = view.findViewById(R.id.textview);
                String text = (String) textView.getText();
                int pos = position;
                Log.d(text,text+position);
            }
        });

    }


    private static class  XTagAdapter  extends TagAdapter{

        private List<String> list ;
        private Context context;
        private LayoutInflater inflater;

        public XTagAdapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getView(ViewGroup viewGroup,int position) {
            Holder holder;

            if(viewGroup==null){
                holder = new Holder();
                viewGroup = (LinearLayout) inflater.inflate(R.layout.tag_layout_item,null);
                viewGroup.setTag(holder);
            }else{
                holder = (Holder) viewGroup.getTag();
            }

            TextView textView = viewGroup.findViewById(R.id.textview);
            String tag = list.get(position);
            textView.setText(tag);
            return viewGroup;
        }

        @Override
        public Object getItem() {
            return null;
        }

        @Override
        public int getPosition(int position) {
            return position;
        }
    }


    private static class Holder{
        public LinearLayout linearLayout;
    }

}
