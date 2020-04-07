package com.mic.tabs;


import android.content.Context;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mic.annotation.FragmentDestination;
import com.mic.user.model.User;
import com.mic.core.architecture.mvp.base.BaseMvpFragment;
import com.mic.core.architecture.mvp.inject.InjectPresenter;
import com.mic.R;
import com.mic.user.login.LoginContract;
import com.mic.user.login.LoginPresenter;


@FragmentDestination(pageUrl ="main/tabs/user" ,asStarter = false)
public class TabUserFragment extends BaseMvpFragment<LoginPresenter> implements LoginContract.ILoginView {

    private NestedScrollView mNestedScrollView;
    private ImageView mNserAvatar;
    private TextView mUserName;
    private TextView mAddress;

    @InjectPresenter
    private LoginPresenter mUserInfoPresenter;

    public TabUserFragment() {

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
    }

    @Override
    protected void initData() {
        mUserInfoPresenter.getUser("admin","admin");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_tab;
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
//        mCardViews.updateCardViews(user);
    }
}
