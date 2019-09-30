package com.mic.ndk;

import com.mic.tools.Logger;

public class NDKTest {

    public void invoke(){
        String msg = NDKInterface.sayHello();
        String signature = NDKInterface.signature("hello");
        Logger.d(Logger.TAG.HOME,msg+" - "+signature);

        NDKModel ndkModel = new NDKModel();
        //native修改普通属性
        ndkModel.changeName();
        Logger.d(Logger.TAG.HOME,"ndkmodel->name:"+ndkModel.getName());
        //native修改静态属性
        NDKModel.changeId();
        Logger.d(Logger.TAG.HOME,"ndkmodel->id:"+NDKModel.id);

        int nativeAddResult = ndkModel.callAddMethod();
        Logger.d(Logger.TAG.HOME,"ndkmodel->add->native:"+nativeAddResult);
    }
}
