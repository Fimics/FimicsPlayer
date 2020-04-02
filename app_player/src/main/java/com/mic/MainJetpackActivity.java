package com.mic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mic.tabs.utils.NavGraphBuilder;

public class MainJetpackActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

   private NavController navController;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MainJetpackActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jetpack);
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(fragment);
//        NavigationUI.setupWithNavController(navView, navController);

        NavGraphBuilder.build(this,navController,fragment.getId());
        navView.setOnNavigationItemSelectedListener(this);
        //处理DeepLink
       // navController.handleDeepLink(getIntent());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navController.navigate(item.getItemId());
        return TextUtils.isEmpty(item.getTitle());
    }
}
