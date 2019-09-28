//
// Created by lipengju on 2019-09-28.
//

#include "com_mic_ndk_NDKInterface.h"
#include "NDK.h"


JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_sayHello(JNIEnv *env,  jobject jclass){

  return env->NewStringUTF("hello ndk");
  }

 JNIEXPORT jstring JNICALL
Java_com_mic_ndk_NDKInterface_signature(JNIEnv *env, jclass clazz, jstring params_) {
//  const char *params = env->GetStringUTFChars(params_,0);
//  env->ReleaseStringChars(params_,params);
  return env->NewStringUTF("签名成功");
}