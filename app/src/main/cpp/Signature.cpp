//
// Created by lipengju on 2019-09-28.
//

#include "com_mic_ndk_NDKInterface.h"
#include <string>
using namespace std;
#include "Signature.h"
#include "MD5.h"
#include <android/log.h>
#include <jni.h>

//额外附加字符串
static char * EXTRA_SIGNATURE ="ndk";
static char * PACKAGE_NAME ="com.mic";
static char * APP_SIGNATURE ="xxx";
static int is_verify =0;

/**
 * JINIEXPORT :JNI 一个关键字，不能少（编译能通过），标记为该方法可以被外部调用
 * jstring :代表java中的string
 * JNICALL:也是一个关键字，可以少的
 * JNIEnv :这个是c和java相互调用的桥梁，(env 结构体指针的别名)
 * jobject :java传递下来的对象，就是本项目中的JinSample java对象
 * jclass :java 传递下来的class对象，就是本项目中的JniSimple.class
 *
 */

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_sayHello(JNIEnv *env, jobject jclass) {
    return env->NewStringUTF("hello ndk");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_signature(JNIEnv *env, jclass clazz, jstring params_) {
    const char *params = env->GetStringUTFChars(params_, 0);

    if(is_verify==0){
        return env->NewStringUTF("error");
    }

    //字符串前面加点东西
    string signature_str(params);
    signature_str.insert(0,EXTRA_SIGNATURE);

    //去掉后面两位
    signature_str = signature_str.substr(0,signature_str.length()-2);
    env->ReleaseStringChars(params_, reinterpret_cast<const jchar *>(params));

    //MD5加密
    MD5_CTX *md5Ctx = new MD5_CTX();
    MD5Init(md5Ctx);
    MD5Update(md5Ctx, (unsigned char *) signature_str.data(), strlen(signature_str.data()));
    unsigned char digdet[16];
    MD5Final(digdet,md5Ctx);

    return env->NewStringUTF(reinterpret_cast<const char *>(digdet));
}


/**
 *     public static Signature[] getSignature(Context context) {
        if (context == null) return null;
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getOpPackageName(), PackageManager.GET_SIGNATURES);
        }catch (Exception e){
            TrackingTools.handleException(e);
        }
        Signature[] signatures = packageInfo.signatures;
        return signatures;
    }
 */

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_mic_ndk_NDKInterface_signatureVerify(JNIEnv *env, jclass clazz, jobject context) {

    // 1. 获取包名
    jclass j_clz = env->GetObjectClass(context);
    jmethodID j_mid = env->GetMethodID(j_clz, "getPackageName", "()Ljava/lang/String;");
    jstring j_package_name = (jstring) env->CallObjectMethod(context, j_mid);
    // 2 . 比对包名是否一样
    const char *c_package_name = env->GetStringUTFChars(j_package_name, NULL);
    if (strcmp(c_package_name, PACKAGE_NAME) != 0) {
        return 0;
    }

    __android_log_print(ANDROID_LOG_DEBUG,"JNI_TAG","签名校验成功: %s");
    // 3. 获取签名
    // 3.1 获取 PackageManager
    j_mid = env->GetMethodID(j_clz,"getPackageManager","()Landroid/content/pm/PackageManager;");
    jobject pack_manager = env->CallObjectMethod(context,j_mid);
    // 3.2 获取 PackageInfo
    j_clz = env->GetObjectClass(pack_manager);
    j_mid = env->GetMethodID(j_clz,"getPackageInfo","(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jobject package_info = env->CallObjectMethod(pack_manager,j_mid,j_package_name,0x00000040);
    // 3.3 获取 signatures 数组
    j_clz = env->GetObjectClass(package_info);
    jfieldID j_fid = env->GetFieldID(j_clz,"signatures","[Landroid/content/pm/Signature;");
    jobjectArray signatures = (jobjectArray) env->GetObjectField(package_info, j_fid);
    // 3.4 获取 signatures[0]
    jobject signatures_first = env->GetObjectArrayElement(signatures,0);
    // 3.5 调用 signatures[0].toCharsString();
    j_clz = env->GetObjectClass(signatures_first);
    j_mid = env->GetMethodID(j_clz,"toCharsString","()Ljava/lang/String;");
    jstring j_signature_str = (jstring) env->CallObjectMethod(signatures_first, j_mid);
    const char * c_signature_str = env->GetStringUTFChars(j_signature_str,NULL);
    // 4. 比对签名是否一样
    if (strcmp(c_signature_str, APP_SIGNATURE) != 0) {
        return 1;
    }
    __android_log_print(ANDROID_LOG_DEBUG,"JNI_TAG","签名校验成功: %s",c_signature_str);
    // 签名认证成功
    is_verify = 1;

    return 0;
}
