package com.mic.training.fragment.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.customview.view.TouchVIew;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouchViewFragment extends Fragment {

    private static final String TAG="TouchView";

    private TouchVIew mTouchVIew;

    public TouchViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_touch_view, container, false);
        mTouchVIew=view.findViewById(R.id.touch_view);

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
        return  view;
    }



}
