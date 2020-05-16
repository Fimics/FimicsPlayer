package com.mic.order.impl;

import com.mic.order.R;
import com.mic.router.annotation.ARouter;
import com.mic.common.order.drawable.OrderDrawable;

/**
 * 订单模块对外暴露接口实现类，其他模块可以获取返回res资源
 */
@ARouter(path = "/order/getDrawable")
public class OrderDrawableImpl implements OrderDrawable {

    @Override
    public int getDrawable() {
        return R.drawable.ic_ac_unit_black_24dp;
    }
}
