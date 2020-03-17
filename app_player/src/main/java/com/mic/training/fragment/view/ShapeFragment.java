package com.mic.training.fragment.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.view.singleview.ShapeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShapeFragment extends Fragment {

    private ShapeView mShapeView;
    private Button mBtnExchange;

    public ShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_shape, container, false);
        mShapeView = view.findViewById(R.id.shape_view);
        mBtnExchange=view.findViewById(R.id.btn_exchange);
        mBtnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchange();
            }
        });
        return view;
    }

    public void exchange() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mShapeView.exchange();
                                }
                            });
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }

}
