package com.mic.customview.taglayout;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract class TagAdapter<T> {

    public TagAdapter(){};

    public abstract int getCount();

    public abstract View getView(ViewGroup viewGroup,int position);

    public abstract T getItem();

    public abstract int getPosition(int position);


    // 3.观察者模式及时通知更新
    public void unregisterDataSetObserver(DataSetObserver observer){

    }

    public void registerDataSetObserver(DataSetObserver observer){

    }

}
