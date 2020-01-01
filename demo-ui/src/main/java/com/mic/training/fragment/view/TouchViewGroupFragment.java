package com.mic.training.fragment.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mic.customview.view.TouchVIew;
import com.mic.customview.view.TouchViewGroup;
import com.mic.training.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouchViewGroupFragment extends Fragment {

    private static final String TAG="TouchView";
    private TouchViewGroup mTouchViewGroup;
    private TouchVIew mTouchVIew;

    public TouchViewGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_touch_view_group, container, false);
        mTouchViewGroup=view.findViewById(R.id.touch_viewgroup);
        mTouchVIew=view.findViewById(R.id.touch_view);

        mTouchVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"TouchVIew --> onTouch "+"event-->"+event.getAction());
                return false;
            }
        });



//        mTouchVIew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"TouchVIew --> onClick");
//            }
//        });

        return view;
    }

}
