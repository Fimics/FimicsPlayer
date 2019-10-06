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
