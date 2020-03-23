package ndk;


import com.mic.utils.Logger;

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

        //c 调用java方法
        int nativeAddResult = ndkModel.callAddMethod();
        Logger.d(Logger.TAG.HOME,"ndkmodel->add->native:"+nativeAddResult);

        //c 调用java 的static方法
        String uuid = NDKModel.callStaticMethod();
        Logger.d(Logger.TAG.HOME,"ndkmodel->callStaticMethod--uuid:"+uuid);

        //怎么样在c层构建java对象，并返回给java层
        Point point =ndkModel.createPoint();
        if(point!=null){
            Logger.d(Logger.TAG.HOME,"ndkmodel->createPoint-->x-->"+point.getX()+"  y ->"+point.getY());
        }


        int[] arr ={1,4,9,0,-5,-90,44,66,92,23,-2};
        ndkModel.sort(arr);
        for (int k:arr) {
            Logger.d(Logger.TAG.HOME,"k--> "+k);
        }

        ndkModel.setGlobalRef("hello global");
        String globalRef = ndkModel.getGlobalRef();
        Logger.d(Logger.TAG.HOME,"nkdmodel->globalref-->"+globalRef);

    }
}
