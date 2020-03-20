package com.mic.home.binder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.view.singleview.ShapeView;

public class ShapeViewBinder extends ItemViewBinder<AndroidUI, ShapeViewBinder.ViewHolder> {

    private Activity activity;

    public ShapeViewBinder(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_shape_view, parent, false),activity);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ShapeView mShapeView;
        private Button mBtnExchange;
        private Activity activity;

        public ViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            this.activity = activity;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mShapeView = itemView.findViewById(R.id.shape_view);
            mBtnExchange=itemView.findViewById(R.id.btn_exchange);
            mBtnExchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exchange();
                }
            });
        }

        public void exchange() {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mShapeView.exchange();
                                    }
                                });
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            ).start();
        }
    }
}
