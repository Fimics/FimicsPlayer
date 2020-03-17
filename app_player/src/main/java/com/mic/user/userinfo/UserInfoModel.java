package com.mic.user.userinfo;

import com.mic.user.model.Result;
import com.mic.user.model.user.User;
import com.mic.architecture.mvp.base.BaseModel;
import com.mic.thirdparty.rxretrofit.RxRetrofitClient;
import com.mic.thirdparty.rxretrofit.RxSchedulers;

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
