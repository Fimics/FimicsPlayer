package com.mic.user.userinfo;

import com.mic.frame.model.Result;
import com.mic.frame.model.user.User;
import com.mic.frame.mvp.base.BaseModel;
import com.mic.frame.rxretrofit.RxRetrofitClient;
import com.mic.frame.rxretrofit.RxSchedulers;

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
