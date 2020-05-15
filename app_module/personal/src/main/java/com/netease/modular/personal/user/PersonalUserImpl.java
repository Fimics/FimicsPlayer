package com.netease.modular.personal.user;

import com.mic.router.annotation.ARouter;
import com.mic.common.user.BaseUser;
import com.mic.common.user.IUser;
import com.netease.modular.personal.model.UserInfo;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/personal/getUserInfo")
public class PersonalUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("熊老师");
        userInfo.setAccount("netease_think");
        userInfo.setPassword("666666");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
