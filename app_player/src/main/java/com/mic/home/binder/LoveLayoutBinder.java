package com.mic.home.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.view.LoveLayout;

public class LoveLayoutBinder extends ItemViewBinder<AndroidUI, LoveLayoutBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_love_layout,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG ="check";
        private LoveLayout mLoveLayout;
        private Button btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLoveLayout = itemView.findViewById(R.id.love_layout);
            btnAdd = itemView.findViewById(R.id.btn_add);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLoveLayout.addLove();
                }
            });
        }
    }
}
