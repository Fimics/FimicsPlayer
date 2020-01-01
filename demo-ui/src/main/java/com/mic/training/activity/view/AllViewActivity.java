package com.mic.training.activity.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mic.libcore.utils.system.DisplayUtil;
import com.mic.training.R;

import java.util.ArrayList;
import java.util.List;

public class AllViewActivity extends AppCompatActivity {


    private GridView mGridView;
    private List<String>  mViews = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);
        mGridView= this.findViewById(R.id.gridview);
        initGridView();
        mViews.add("XTextView");
        mViews.add("QQStep");
        mViews.add("TrackTextView");
        mViews.add("ProgressBar");
        mViews.add("ViewPager");
        mViews.add("ShapeView");
        mViews.add("RatingBar");
        mViews.add("LetterSideBar");
        mViews.add("ViewDrawFlow");
        mViews.add("TagLayout");
        mViews.add("TouchView");
        mViews.add("TouchViewGroup");
        mViews.add("SlidingMenu");
        mViews.add("QQSlidingMenu");
        mViews.add("VerticalDragListView");
        mViews.add("LockPatternView");
        mViews.add("SwipeRefreshLayout");
        mViews.add("NestedScrollView");
        mViews.add("StatusBar");
        mViews.add("MyScrollView");
        mViews.add("Behavior");
        mViews.add("LoadingView");
        mViews.add("ListMenu");
        mViews.add("CircleLoadingView");
        mViews.add("MessageBubbleView");
        mViews.add("MessageBubbleView1");
        mViews.add("LoveLayout");
    }



    private void initGridView(){
        mGridView.setAdapter(new GridAdapter(mViews,this));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AllViewActivity.this,ViewItemActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name",mViews.get(position));
                startActivity(intent);
            }
        });
    }

    private class  GridAdapter extends BaseAdapter {

        private List<String> mViews;
        private Context mContext;

        public GridAdapter(List<String> mViews,Context context) {
            this.mViews = mViews;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public Object getItem(int position) {
            return mViews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder=null;

            if(convertView==null){
                holder = new Holder();
                convertView = View.inflate(mContext,R.layout.gridview_item,null);
                holder.item = convertView.findViewById(R.id.tv_gridview_item);

                int screenWidth = DisplayUtil.getScreenWidth(mContext);
                int itemWidth = screenWidth/4;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth,itemWidth);
                holder.item.setLayoutParams(params);

                convertView.setTag(holder);
            }else{
                holder= (Holder) convertView.getTag();
            }

            holder.item.setText(mViews.get(position));
            if(position%2==0){
                holder.item.setTextColor(Color.RED);
            }
            return convertView;
        }



    }

    private static class Holder{
        public TextView item;
    }
}
