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

public class TouchViewBinder extends ItemViewBinder<AndroidUI, TouchViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_touch_view,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG="TouchView";

        private TouchVIew mTouchVIew;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTouchVIew=itemView.findViewById(R.id.touch_view);
            mTouchVIew.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            Log.d(TAG,"onTouch  --ACTION_DOWN--->");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d(TAG,"onTouch  --ACTION_MOVE--->");
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG,"onTouch  --ACTION_UP--->");
                            break;
                    }


                    return true;
                }
            });

            mTouchVIew.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(TAG,"onLongClick");
                    return false;
                }
            });

            mTouchVIew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"onClick");
                }
            });
        }
    }
}
