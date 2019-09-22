package com.mic.xsample.activity.opensource;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.mic.xsample.R;

public class OpenSourceItemActivity extends AppCompatActivity {


    private FragmentTransaction transaction;

    private static final int GLIDE=0;
    private static final int HANDLE=1;
    private static final int FIXBUG=2;
    private static final int OKHTTP=3;
    private static final int RETROFIT=4;
    private static final int RXBINDING=5;
    private static final int RXBITMAP=6;
    private static final int RXPERMISSIONS=7;
    private static final int RX_RETROFIT=8;
    private static final int UPDATE_UI =9;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent intent =getIntent();
        int id = intent.getIntExtra("position",0);
        String name = intent.getStringExtra("name");

        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        showItemView(id);
    }

    private void showItemView(int id){
        switch (id){
            case GLIDE:
                startActivity(GlideActivity.class);
                break;
            case HANDLE:
               startActivity(HandleActivity.class);
                break;
            case FIXBUG:
                startActivity(MainActivity.class);
                break;
            case OKHTTP:
                //startActivity(OkhttpActivity.class);
                break;
            case RETROFIT:
                //startActivity(RetrofitActivity.class);
                break;
            case RXBINDING:
                startActivity(RxBitmapActivity.class);
                break;
            case RXBITMAP:
                startActivity(RxBindingActivity.class);
                break;
            case RXPERMISSIONS:
                startActivity(RxPermissionsActivity.class);
                break;
            case RX_RETROFIT:
               // startActivity(RxRetrofitActivity.class);
                break;
            case UPDATE_UI:
                startActivity(UpdateUIActivity.class);
                break;

        }
    }


    private void replaceFragment(Fragment fragment){
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(OpenSourceItemActivity.this,clazz);
        startActivity(intent);
    }


}
