//
// Created by lipengju on 2019-09-28.
//

#include "com_mic_ndk_NDKInterface.h"
#include <string>
using namespace std;
#include "NDK.h"
#include "MD5.h"
#include <jni.h>

//额外附加字符串
static char* EXTRA_SIGNATURE ="ndk";


JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_sayHello(JNIEnv *env, jobject jclass) {
    return env->NewStringUTF("hello ndk");
}


JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_signature(JNIEnv *env, jclass clazz, jstring params_) {
    const char *params = env->GetStringUTFChars(params_, 0);

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