package com.mic.user.login;

import com.mic.Result;
import com.mic.user.model.User;
import com.mic.architecture.mvp.base.BaseView;

import io.reactivex.Observable;

@SuppressWarnings("unused")
public interface LoginContract {

    interface ILoginView extends BaseView{
        void onLoading();
        void onError();
        void onSucceed(User user);
    }

    interface ILoginPresenter{
        void getUser(String name, String password);
    }

    interface LoginModel{
        Observable<Result<User>> getUser(String name, String password);
    }
}
