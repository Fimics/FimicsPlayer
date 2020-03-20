package com.mic.home.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;

public class AndroidUIBinder extends ItemViewBinder<AndroidUI, AndroidUIBinder.ViewHolder> {

    private ItemClickListener listener;

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_android_ui_item,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
         holder.textView.setText(item.getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.android_ui_tv_name);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnClickListener(view -> {
                if(listener!=null){
                    int position = getAdapterPosition();
                    listener.onItemOnClick(position);
                }
            });
        }
    }

    public interface ItemClickListener{
        public void onItemOnClick(int position);
    }
}
