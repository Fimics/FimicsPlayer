package com.mic.core.architecture.mvp.proxy;


public interface IMvpProxy {
    void bindAndCreatePresenter();// 一个是和创建绑定
    void unbindPresenter();// 一个是解绑
}
