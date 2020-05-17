package com.mic.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.common.user.BaseUser;
import com.mic.common.user.IUser;
import com.mic.router.annotation.Router;
import com.mic.router.annotation.Parameter;
import com.mic.router.api.ParameterManager;
import com.mic.router.api.RouterManager;
import com.mic.common.utils.Cons;

@Router(path = "/personal/Personal_MainActivity")
public class Personal_MainActivity extends AppCompatActivity {

    @Parameter(name = "/demo/getUserInfo")
    IUser iUser;

    @Parameter
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);

        Log.e(Cons.TAG, "personal/Personal_MainActivity");

        // 懒加载方式，跳到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);

        Log.e(Cons.TAG, "接收参数值：" + username);

        BaseUser userInfo = iUser.getUserInfo();
        if (userInfo != null) {
            Log.e(Cons.TAG, userInfo.getName() + " / "
                    + userInfo.getAccount() + " / "
                    + userInfo.getPassword());
        }
    }

    public void jumpApp(View view) {
        RouterManager.getInstance()
                .build("/demo/DemoMainActivity")
                .withResultString("call", "I'am comeback!")
                .navigation(this);
    }

    public void jumpOrder(View view) {
        RouterManager.getInstance()
                .build("/order/Order_MainActivity")
                .withString("name", "simon")
                .navigation(this);
    }
}
