package com.netease.pluginhookandroid9;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Time: 2019-08-10
 * Author: Liudeli
 * Description: 代理Activity,此Activity存在的目的是为了过安检(AMS检查)
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "我是代理Activity....", Toast.LENGTH_SHORT).show();
    }
}
