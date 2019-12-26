package com.mic.frame.mvp.proxy;



import com.mic.frame.mvp.base.BasePresenter;
import com.mic.frame.mvp.base.BaseView;
import com.mic.frame.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MvpProxyImpl<V extends BaseView> implements IMvpProxy{
    private V mView;
    private List<BasePresenter> mPresenters;
    public MvpProxyImpl(V view){
        // 做一下判断 是不是 NULL
        this.mView = view;
        mPresenters = new ArrayList<>();
        bindAndCreatePresenter();
    }
    @Override
    public void bindAndCreatePresenter() {
        // 这个地方要去注入 Presenter 通过反射 (Dagger) soEasy
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if(injectPresenter != null){
                // 创建注入
                Class<? extends BasePresenter> presenterClazz = null;
                // 自己去判断一下类型？ 获取继承的父类，如果不是 继承 BasePresenter 抛异常

                presenterClazz = (Class) field.getType();

                //判断这个Class 是不是继承BasePresenter ,如果不是抛出异常
                if(!BasePresenter.class.isAssignableFrom(presenterClazz)){
                    throw  new RuntimeException("no support type is "+presenterClazz.getName());
                }

//                try {
//
//                    //(Class<? extends BasePresenter>) =class
//                    //编译器在运行时候会对我们的泛型进行擦除(一般是针对系统的)
//
//                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
//                } catch (Exception e){
//                    // 乱七八糟一些注入
//                    throw new RuntimeException("No support inject presenter type " + field.getType().getName());
//                }

                BasePresenter basePresenter =null;

                try {
                    // 创建 Presenter 对象
                    basePresenter = presenterClazz.newInstance();
                    // 并没有解绑，还是会有问题，这个怎么办？1 2
                    basePresenter.attach(mView);
                    // 设置
                    field.setAccessible(true);
                    field.set(mView,basePresenter);
                    mPresenters.add(basePresenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 检查我们的View层是否实现了BasePresent的View接口

                checkView(basePresenter);

            }
        }
    }

    private void checkView(BasePresenter basePresenter){

        //1.Presenter的View接口
        Type[] params =((ParameterizedType)basePresenter.getClass().getGenericSuperclass()).getActualTypeArguments();

        Class viewClazz = (Class) params[0];
        //2.要拿到View 层的所有接口

        Class<?>[] viewClasses =mView.getClass().getInterfaces();

        //3.View层没有实现就抛异常

        boolean isImplementsView = false;

        //判断view是否实现了basePresenter接口
        for (Class viiewClass:viewClasses){
            if(viiewClass.isAssignableFrom(viewClazz)){
               isImplementsView=true;
            }
        }


//        if(isImplementsView){
//            throw  new RuntimeException(mView.getClass().getSimpleName()+"view must implements:"+viewClazz.getName());
//        }


    }

    @Override
    public void unbindPresenter() {
        // 一定要解绑
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
        mView = null;
    }
}
