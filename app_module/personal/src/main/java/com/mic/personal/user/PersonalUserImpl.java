package com.mic.personal.user;

import com.mic.router.annotation.Router;
import com.mic.common.user.BaseUser;
import com.mic.common.user.IUser;
import com.mic.personal.model.UserInfo;

/**
 * personal模块实现的内容
 */
@Router(path = "/personal/getUserInfo")
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
