package com.mic.order;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.ARouter;
import com.mic.router.annotation.Parameter;
import com.mic.router.api.ParameterManager;
import com.mic.common.utils.Cons;

@ARouter(path = "/order/Order_DetailActivity")
public class Order_DetailActivity extends AppCompatActivity {

    @Parameter
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_activity);

        Log.e(Cons.TAG, "order/Order_DetailActivity");

        // 懒加载方式，跳到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);

        Log.e(Cons.TAG, "接收参数值：" + username);
    }
}
