package com.mic.demo.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.demo.bean.AndroidUI;
import com.mic.core.thirdparty.multitype.ItemViewBinder;
import com.mic.home.adapter.ListScreenMenuAdapter;
import com.mic.exoplayer.ListDataScreenView;

public class ListMenuBinder extends ItemViewBinder<AndroidUI, ListMenuBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_list_menu,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ListDataScreenView mListDataScreenView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mListDataScreenView = itemView.findViewById(R.id.list_data_screen_view);
            mListDataScreenView.setAdapter(new ListScreenMenuAdapter(mListDataScreenView.getContext()));
        }
    }
}
