package com.mic.order.impl;

import com.mic.router.annotation.Router;
import com.mic.common.user.BaseUser;
import com.mic.common.user.IUser;
import com.mic.order.model.UserInfo;

/**
 * personal模块实现的内容
 */
@Router(path = "/order/getUserInfo")
public class OrderUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("冯老师");
        userInfo.setAccount("netease_river");
        userInfo.setPassword("666666");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
