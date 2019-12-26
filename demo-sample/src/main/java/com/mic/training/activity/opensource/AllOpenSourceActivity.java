package com.mic.training.activity.opensource;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

public class AllOpenSourceActivity extends AppCompatActivity {


    private GridView mGridView;
    private List<String>  mViews = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);
        mGridView= this.findViewById(R.id.gridview);
        initGridView();
        mViews.add("Glide");
        mViews.add("Handle");
        mViews.add("FixBug");
        mViews.add("Okhttp");
        mViews.add("Retrofit");
        mViews.add("RxBinding");
        mViews.add("RxBitmap");
        mViews.add("RxPermissions");
        mViews.add("RxRetrofit");
        mViews.add("子线程更新UI");
    }




    private void initGridView(){
        mGridView.setAdapter(new GridAdapter(mViews,this));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AllOpenSourceActivity.this,OpenSourceItemActivity.class);
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
