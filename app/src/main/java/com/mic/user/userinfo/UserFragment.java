package com.mic.user.userinfo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mic.frame.model.user.User;
import com.mic.frame.mvp.base.BaseMvpFragment;
import com.mic.frame.mvp.inject.InjectPresenter;
import com.mic.home.R;
import com.mic.user.customview.UserInfoLinearLayout;



@SuppressWarnings("all")
public class UserFragment extends BaseMvpFragment<UserInfoPresenter> implements UserInfoContract.UserInfoView {

    private NestedScrollView mNestedScrollView;
    private ImageView mNserAvatar;
    private TextView mUserName;
    private TextView mAddress;
    private UserInfoLinearLayout mCardViews;

    @InjectPresenter
    private UserInfoPresenter mUserInfoPresenter;

    public UserFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected void initView() {
       mNestedScrollView=rootView.findViewById(R.id.nestedscrview);
       mNserAvatar=rootView.findViewById(R.id.iv_user_avater);
       mUserName=rootView.findViewById(R.id.tv_username);
       mAddress=rootView.findViewById(R.id.tv_user_address);
       mCardViews=rootView.findViewById(R.id.cardviews);
    }

    @Override
    protected void initData() {
        mUserInfoPresenter.getUser("admin","admin");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSucceed(User user) {
        mCardViews.updateCardViews(user);
    }
}
