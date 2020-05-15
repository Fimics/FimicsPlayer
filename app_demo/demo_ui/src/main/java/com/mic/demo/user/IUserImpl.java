package com.mic.demo.user;

import com.mic.demo.model.UserInfo;
import com.mic.router.annotation.ARouter;
import com.netease.common.user.BaseUser;
import com.netease.common.user.IUser;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/app/getUserInfo")
public class IUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("彭老师");
        userInfo.setAccount("netease_simon");
        userInfo.setPassword("666666");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
