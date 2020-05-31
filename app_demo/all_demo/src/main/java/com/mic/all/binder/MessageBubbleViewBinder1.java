package com.mic.all.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.all.R;
import com.mic.all.bean.AndroidUI;
import com.mic.core.thirdparty.multitype.ItemViewBinder;
import com.mic.all.view.BubbleMessageTouchListener;
import com.mic.all.view.MessageBubbleView;

public class MessageBubbleViewBinder1 extends ItemViewBinder<AndroidUI, MessageBubbleViewBinder1.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_message_bubble_view1,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MessageBubbleView.attach(itemView.findViewById(R.id.text_view), new  BubbleMessageTouchListener.BubbleDisappearListener() {
                @Override
                public void dismiss(View view) {

                }
            });
        }
    }
}
