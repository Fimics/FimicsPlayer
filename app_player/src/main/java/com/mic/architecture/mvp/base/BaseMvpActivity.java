package com.mic.architecture.mvp.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.mic.BaseActivity;
import com.mic.architecture.mvp.proxy.ActivityMvpProxy;
import com.mic.architecture.mvp.proxy.ActivityMvpProxyImpl;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityMvpProxy mMvpProxy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvpProxy = createMvpProxy();
    }

    /**
     * 创建 Mvp 的代理  自己去写 Fragment
     * @return
     */
    private ActivityMvpProxy createMvpProxy() {
        if(mMvpProxy == null){
            mMvpProxy = new ActivityMvpProxyImpl<>(this);
        }
        return mMvpProxy;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mMvpProxy.unbindPresenter();
    }


}
