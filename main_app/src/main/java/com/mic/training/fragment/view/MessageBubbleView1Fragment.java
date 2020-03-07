package com.mic.training.fragment.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.training.view.BubbleMessageTouchListener;
import com.mic.training.view.MessageBubbleView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageBubbleView1Fragment extends Fragment {


    public MessageBubbleView1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_message_bubble_view1, container, false);

        MessageBubbleView.attach(view.findViewById(R.id.text_view), new  BubbleMessageTouchListener.BubbleDisappearListener() {
            @Override
            public void dismiss(View view) {

            }
        });
        return view;
    }

}
