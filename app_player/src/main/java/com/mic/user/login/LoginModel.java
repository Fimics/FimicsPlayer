package com.mic.user.login;

import com.mic.core.Result;
import com.mic.core.architecture.mvp.base.BaseModel;
import com.mic.core.thirdparty.rxretrofit.RxRetrofitClient;
import com.mic.core.thirdparty.rxretrofit.RxSchedulers;
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
