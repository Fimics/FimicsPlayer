package com.mic.optimization.activity;




import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;


import com.mic.optimization.R;


public class ViewItemActivity extends AppCompatActivity {


    private FragmentTransaction transaction;

    private static final int LEAK_SINGLETON=0;





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
            case LEAK_SINGLETON:
                startActivity(LeakContextActivity.class);
                break;

        }
    }


    private void replaceFragment(Fragment fragment){
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    private void startActivity(Class clazz){
        finish();
        Intent intent = new Intent(ViewItemActivity.this,clazz);
        startActivity(intent);
    }


}
