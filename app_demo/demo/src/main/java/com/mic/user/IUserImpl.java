package com.mic.user;

import com.mic.model.UserInfo;
import com.mic.router.annotation.Router;
import com.mic.common.user.BaseUser;
import com.mic.common.user.IUser;

/**
 * personal模块实现的内容
 */
@Router(path = "/demo/getUserInfo")
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
