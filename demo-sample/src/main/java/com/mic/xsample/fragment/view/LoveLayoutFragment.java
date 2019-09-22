package com.mic.xsample.fragment.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mic.libretrofit.http.FRetrofit;
import com.mic.libretrofit.http.FRetrofitClient;
import com.mic.xsample.R;
import com.mic.xsample.service.FUserApi;
import com.mic.xsample.view.LoveLayout;
import com.mic.xsample.xaspect.CheckNet;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoveLayoutFragment extends Fragment implements View.OnClickListener {

    private static final String TAG ="check";

    private LoveLayout mLoveLayout;
    private Button btnAdd;
    public LoveLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_love_layout, container, false);

        mLoveLayout = (LoveLayout) view.findViewById(R.id.love_layout);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        return view;
    }


   // @CheckNet
    @Override
    public void onClick(View v) {
        //mLoveLayout.addLove();

        login("java","java");

    }


    private void login(String name,String password){
        FRetrofit fRetrofit =FRetrofitClient.getInstance().getfRetrofit();
        fRetrofit.create(FUserApi.class).login("java","java");
    }

    @CheckNet
    public void sayHello(){

    }
}
