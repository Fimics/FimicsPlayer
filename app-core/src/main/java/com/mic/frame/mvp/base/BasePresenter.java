package com.mic.frame.mvp.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by hcDarren on 2018/1/1.
 */

public class BasePresenter<V extends BaseView,M extends BaseModel> {
    // 目前两个两个公用方法 ，传递的时候 会有不同的 View ，怎么办？泛型
    // SoftReference 会在 OOM 前，其实就是 Activity 被回收，这哥们也回收
    // WeakReference 这个就是一 GC 就没了
    private V mView;
    private V mProxyView;
    // View 一般都是 Activity ,涉及到内存泄漏，但是已经解绑了不会，如果没解绑就有可能会泄漏
    // 最好还是用一下软引用

    // 动态创建的 model 的对象
    private M mModel;

    // View 有一个特点，都是接口
    // GC 回收的算法机制（哪几种）标记清楚法
    public void attach(V view){
        this.mView = view;

        // 用代理对象
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 执行这个方法 ，调用的是被代理的对象
                if(mView == null) {
                    return null;
                }
                // 没解绑执行的是原始被代理 View 的方法
                return method.invoke(mView,args);
            }
        });

        // 创建我们的 Model ，动态创建？ 获取 Class 通过反射 （Activity实例创建的？class 反射创建的，布局的 View 对象怎么创建的？反射）
        // 获取 Class 对象
        Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            // 最好要判断一下类型
            mModel = (M) ((Class)params[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void detach(){
        // 不解绑的问题 Activity -> Presenter  ,Presenter 持有了 Activity 应该是会有内存泄漏
        this.mView = null;
        // 注释
        // this.mProxyView = null;
    }

    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mProxyView;
    }
}
