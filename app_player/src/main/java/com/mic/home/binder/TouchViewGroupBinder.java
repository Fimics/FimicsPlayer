package com.mic.home.binder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.view.singleview.TouchVIew;
import com.mic.view.singleview.TouchViewGroup;

public class TouchViewGroupBinder extends ItemViewBinder<AndroidUI, TouchViewGroupBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_touch_view_group,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG="TouchView";
        private TouchViewGroup mTouchViewGroup;
        private TouchVIew mTouchVIew;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTouchViewGroup=itemView.findViewById(R.id.touch_viewgroup);
            mTouchVIew=itemView.findViewById(R.id.touch_view);

            mTouchVIew.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d(TAG,"TouchVIew --> onTouch "+"event-->"+event.getAction());
                    return false;
                }
            });



//        mTouchVIew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"TouchVIew --> onClick");
//            }
//        });
        }
    }
}
