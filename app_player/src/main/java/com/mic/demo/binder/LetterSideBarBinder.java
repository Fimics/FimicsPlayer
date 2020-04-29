package com.mic.demo.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.demo.bean.AndroidUI;
import com.mic.core.thirdparty.multitype.ItemViewBinder;

public class LetterSideBarBinder extends ItemViewBinder<AndroidUI, LetterSideBarBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_letter_side_bar,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
//        mTextView.post(new Runnable() {
//            // 保存到Queue中，什么都没干，会在dispatchAttachedToWindow会在测量完毕之后调用中执行，executeActions（）
//            @Override
//            public void run() {
//                Log.e("TAG", "height2 -> " + mTextView.getMeasuredHeight()); // 高度
//            }
//        });
//
//        mTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.e("TAG", "height5 -> " + mTextView.getMeasuredHeight());
//            }
//        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
