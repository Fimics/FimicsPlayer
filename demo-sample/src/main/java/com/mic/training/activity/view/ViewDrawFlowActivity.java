package com.mic.training.activity.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.mic.training.R;

public class ViewDrawFlowActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draw_flow);
        mTextView=this.findViewById(R.id.textview);


        Log.e("TAG", "height1 -> " + mTextView.getMeasuredHeight()); // 0

        mTextView.post(new Runnable() {
            // 保存到Queue中，什么都没干，会在dispatchAttachedToWindow会在测量完毕之后调用中执行，executeActions（）
            @Override
            public void run() {
                Log.e("TAG", "height2 -> " + mTextView.getMeasuredHeight()); // 高度
            }
        });

        mTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("TAG", "height5 -> " + mTextView.getMeasuredHeight());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "height3 -> " + mTextView.getMeasuredHeight()); // 0
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "height4 -> " + mTextView.getMeasuredHeight()); // 0
    }

}
