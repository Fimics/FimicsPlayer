package com.mic.order.debug;

import android.os.Bundle;
import android.util.Log;

import com.mic.common.utils.Cons;
import com.mic.order.R;

public class Order_DebugActivity extends Order_DebugBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_debug);

        Log.e(Cons.TAG, "order/debug/Order_DebugActivity");
    }
}
