package com.mic.optimization.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mic.optimization.common.ListenerCollector;

public class LeakView extends View {
    public LeakView(Context context) {
        super(context,null);
    }

    public LeakView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        ListenerCollector listenerCollector = new ListenerCollector();
        listenerCollector.setsListener(this,myListener);
    }

    public interface MyListener{
        public void myListenerCallback();
    }


    private MyListener myListener = new MyListener() {
        @Override
        public void myListenerCallback() {
            System.out.print("xxxxxxxxxxx");
        }
    };
}
