package com.mic.user.userinfo;

import com.mic.user.model.Result;
import com.mic.user.model.user.User;
import com.mic.architecture.mvp.base.BaseView;

import io.reactivex.Observable;

@SuppressWarnings("unused")
public interface UserInfoContract {

    interface  UserInfoView extends BaseView{

        void onLoading();
        void onError();
        void onSucceed(User user);
    }

    interface UserInfoPresenter{
        void getUser(String name, String password);
    }

    interface UserInfoModel{
        Observable<Result<User>> getUser(String name, String password);
    }

}
