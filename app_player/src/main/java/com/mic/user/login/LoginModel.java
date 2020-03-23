package com.mic.user.login;

import com.mic.Result;
import com.mic.architecture.mvp.base.BaseModel;
import com.mic.thirdparty.rxretrofit.RxRetrofitClient;
import com.mic.thirdparty.rxretrofit.RxSchedulers;
import com.mic.user.model.User;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.LoginModel {

    @Override
    public Observable<Result<User>> getUser(String name, String password) {
        return RxRetrofitClient.getInstance().getRetrofit()
                .create(LoginApi.class)
                .login(name,password)
                .compose(RxSchedulers.io_main());
    }
}
