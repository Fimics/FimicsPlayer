package com.mic.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mic.R;
import com.mic.demo.viewwy.recyclerview.FeedAdapter;


public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FeedAdapter mFeedAdapter;
    private RelativeLayout mSuspensionBar;
    private TextView mSuspensionTv;
    private ImageView mSuspensionIv;

    private int mSuspensionHeight;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mSuspensionBar = findViewById(R.id.suspension_bar);
        mSuspensionTv = findViewById(R.id.tv_nickname);
        mSuspensionIv = findViewById(R.id.iv_avatar);

        mRecyclerView = findViewById(R.id.recyclerView);
         final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mFeedAdapter = new FeedAdapter();
        mRecyclerView.setAdapter(mFeedAdapter);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取悬浮条的高度
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //对悬浮条的位置进行调整
                //找到下一个itemView
                View view = layoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null){
                    if (view.getTop() <= mSuspensionHeight){
                        //需要对悬浮条进行移动
                        mSuspensionBar.setY(-(mSuspensionHeight-view.getTop()));
                    }else{
                        //保持在原来的位置
                        mSuspensionBar.setY(0);
                    }
                }

                if (mCurrentPosition != layoutManager.findFirstVisibleItemPosition()){
                    mCurrentPosition = layoutManager.findFirstVisibleItemPosition();
                    updateSuspensionBar();
                }

            }
        });
        updateSuspensionBar();
    }

    private void updateSuspensionBar() {
        Glide.with(this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .into(mSuspensionIv);
        mSuspensionTv.setText("NetEase "  + mCurrentPosition);
    }

    private int getAvatarResId(int position){
        switch (position % 4){
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }

}