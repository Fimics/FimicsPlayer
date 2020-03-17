package com.mic.user.userinfo;

import com.mic.bb.frame.model.Result;
import com.mic.bb.frame.model.user.User;
import com.mic.bb.frame.mvp.base.BaseModel;
import com.mic.bb.frame.rxretrofit.RxRetrofitClient;
import com.mic.bb.frame.rxretrofit.RxSchedulers;

import io.reactivex.Observable;

public class UserInfoModel extends BaseModel implements UserInfoContract.UserInfoModel {

    @Override
    public Observable<Result<User>> getUser(String name, String password) {
        return RxRetrofitClient.getInstance().getRetrofit()
                .create(RxUserApi.class)
                .login(name,password)
                .compose(RxSchedulers.io_main());
    }
}
