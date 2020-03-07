//
// Created by lipengju on 2019-09-30.
//

#include "com_mic_ndk_NDKInterface.h"
#include <string>

using namespace std;

#include <android/log.h>
#include <jni.h>

/**
 * JINIEXPORT :JNI 一个关键字，不能少（编译能通过），标记为该方法可以被外部调用
 * jstring :代表java中的string
 * JNICALL:也是一个关键字，可以少的
 * JNIEnv :这个是c和java相互调用的桥梁，(env 结构体指针的别名)
 * jobject :java传递下来的对象，就是本项目中的JinSample java对象
 * jclass :java 传递下来的class对象，就是本项目中的JniSimple.class
 *
 */

// 输出内部类型签名
//lipengju@Mac ~/code/android/FimicsPlayer/app/build/intermediates/javac/debug/compileDebugJavaWithJavac/classes/com/mic/ndk (dev*) $ javap -p -s NDKModel

/**
 * native修改java普通属性
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_changeName(JNIEnv *env, jobject obj) {

    jclass jclz = env->GetObjectClass(obj);
    jfieldID jfieldId = env->GetFieldID(jclz, "name", "Ljava/lang/String;");
    jstring name = static_cast<jstring>(env->GetObjectField(obj, jfieldId));
    char *c_name = const_cast<char *>(env->GetStringUTFChars(name, NULL));
    __android_log_print(ANDROID_LOG_DEBUG, "JNI_TAG", "c-------->name: %s", c_name);
    jstring jackName = env->NewStringUTF("jack");
    env->SetObjectField(obj, jfieldId, jackName);
}


//native修改静态属性
extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_changeId(JNIEnv *env, jclass clazz) {
    //clazz 在哪个类调用就是哪个类
    jfieldID jfieldId = env->GetStaticFieldID(clazz, "id", "Ljava/lang/String;");
    jstring jid = env->NewStringUTF("456");
    env->SetStaticObjectField(clazz, jfieldId, jid);
}

//c 调用java方法
extern "C"
JNIEXPORT jint JNICALL
Java_com_mic_ndk_NDKModel_callAddMethod(JNIEnv *env, jobject thiz) {
    jclass jclz = env->GetObjectClass(thiz);
    jmethodID jmethodId = env->GetMethodID(jclz, "add", "(II)I");
    jint ji = env->CallIntMethod(thiz, jmethodId, 5, 6);
    return ji;
}

//c 调用java 的static方法
extern "C"
JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKModel_callStaticMethod(JNIEnv *env, jclass clazz) {
    jmethodID jmethodId = env->GetStaticMethodID(clazz, "getUUID", "()Ljava/lang/String;");
//    jobject  jobj =env->CallStaticObjectMethod(clazz,jmethodId);
//    return static_cast<jstring>(jobj);
    jstring jstr = static_cast<jstring>(env->CallStaticObjectMethod(clazz, jmethodId));
    return jstr;
}

/**
 * 方法签名怎么写
 * Point get(int x,float y);
 * (IF)Ljava/awt/Point;  String ->Ljava/lang/String;
 * Object[]->[L全类名;
*/

//怎么样在c层构建java对象，并返回给java层
extern "C"
JNIEXPORT jobject JNICALL
Java_com_mic_ndk_NDKModel_createPoint(JNIEnv *env, jobject thiz) {
    //方式一
//    jclass  point_clz = env->FindClass("com.mic.ndk.Point");
//    jmethodID jmethodId = env->GetStaticMethodID(point_clz,"createPoint","(II)Lcom/mic/ndk/Point;");
//    jobject point = env->CallStaticObjectMethod(point_clz,jmethodId,3,4);

    //方式二
    jclass  point_clz = env->FindClass("com/mic/ndk/Point");
    jmethodID  jmethodId =env->GetMethodID(point_clz,"<init>","(II)V");
    jobject point = env->NewObject(point_clz,jmethodId,4,5);

    return point;
}

int compare(const jint *num1, const jint *num2){
    return *num2-*num1;
}

//native 对java数组排序
extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_sort(JNIEnv *env, jobject thiz, jintArray arr) {
    //对arr进行排序
   jint * intArray =env->GetIntArrayElements(arr,NULL);
   int length = env->GetArrayLength(arr);
   qsort(intArray, length, sizeof(int),
         reinterpret_cast<int (*)(const void *, const void *)>(compare));

   //同步数组的数据给java数组，intArray并不是arr,可以简单的理解为copy
   //0 即要同步数据给arr,又要释放intArray
   //JNI_COMMOT:会同步数据给arr , 但是不会释放intArray
   //JNI_ABORT: 不会同步数据给arr ,但是会释放intArray
   env->ReleaseIntArrayElements(arr,intArray,0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_localRef(JNIEnv *env, jobject thiz) {
    /**
    * 1.在native层构建的java对象，你不用了怎么管理？
    * 2.native层开辟的内存由谁管理，你能开辟多大？
    */

    //字符串截取 String
    jclass  str_clz= env->FindClass("java/lang/String");
    jmethodID init_mid=env->GetMethodID(str_clz,"<init>","()V");
    jobject j_str=env->NewObject(str_clz,init_mid);

    //j_str 不再使用了，要回收，
    env->DeleteGlobalRef(j_str);
    //回收之后就不能再使用了 c /c++都需要自己释放内存(静态开辟的不需要加收，动态开辟的内存需要回收)
}


jstring  globalStr;


extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_setGlobalRef(JNIEnv *env, jobject thiz, jstring global) {
//     globalStr = static_cast<jstring>(env->NewGlobalRef(global));
//     env->NewWeakGlobalRef();
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKModel_getGlobalRef(JNIEnv *env, jobject thiz) {
    return globalStr;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKModel_delGlobalRef(JNIEnv *env, jobject thiz) {
    env->DeleteGlobalRef(globalStr);
}

//异常处理
extern "C"
JNIEXPORT void JNICALL
Java_com_mic_ndk_NDKModel_exception(JNIEnv *env, jobject thiz) {

//    // 假设现在想给 ，name 赋值 name3
//    jfieldID f_id = (*env)->GetStaticFieldID(env, jclz, "name3", "Ljava/lang/String;");
//
//    // 好几种方式
//    // 1. 补救措施 ，name3 我拿 name
//    // 1.1 有没有异常
//    jthrowable throwable = (*env)->ExceptionOccurred(env);
//    /*if (throwable){
//        // 补救措施，先把异常清除
//        printf("有异常");
//        // 清除异常
//        (*env)->ExceptionClear(env);
//        // 重新获取 name 属性
//        f_id = (*env)->GetStaticFieldID(env, jclz, "name", "Ljava/lang/String;");
//    }*/
//
//    // 2. 想给 java 层抛一个异常
//    if (throwable){
//        // 清除异常
//        (*env)->ExceptionClear(env);
//        // Throw 抛一个 java 的 Throwable 对象
//        jclass no_such_clz = (*env)->FindClass(env,"java/lang/NoSuchFieldException");
//        (*env)->ThrowNew(env, no_such_clz,"NoSuchFieldException name3");
//
//        return;// 记得 return
//    }
//
//    jstring name = (*env)->NewStringUTF(env, "Darren");
//    (*env)->SetStaticObjectField(env, jclz, f_id, name);
}