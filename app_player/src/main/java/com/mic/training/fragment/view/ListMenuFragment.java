package com.mic.training.fragment.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.training.adapter.ListScreenMenuAdapter;
import com.mic.view.ListDataScreenView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMenuFragment extends Fragment {

    private ListDataScreenView mListDataScreenView;
    public ListMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list_menu, container, false);
        mListDataScreenView = (ListDataScreenView) view.findViewById(R.id.list_data_screen_view);
        mListDataScreenView.setAdapter(new ListScreenMenuAdapter(getActivity()));
        return view;
    }

    public void click(View view){
        Toast.makeText(getActivity(),"111",Toast.LENGTH_LONG).show();
    }
}
