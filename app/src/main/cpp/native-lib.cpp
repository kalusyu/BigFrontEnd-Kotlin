#include <jni.h>
#include <string>
#include <stdio.h>
#include "mpp_err.h"
#include <android/log.h>

jstring stringFromJNI(JNIEnv *env, jobject thiz) {
    const char *str = "Hello from C++";
    return env->NewStringUTF(str);
}

static const JNINativeMethod gMethods[] = {
        {"stringFromJNI", "()Ljava/lang/String;", (jstring *) stringFromJNI}
};

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {

    __android_log_print(ANDROID_LOG_INFO, "native", "Jni_OnLoad");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    jclass clazz = env->FindClass("com/kalusyu/bigfrontend_kotlin/MainActivity");
    if (!clazz) {
        __android_log_print(ANDROID_LOG_INFO, "native",
                            "cannot get class: com/kalusyu/bigfrontend_kotlin/MainActivity");
        return -1;
    }
    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0]))) {
        __android_log_print(ANDROID_LOG_INFO, "native", "register native method failed!\n");
        return -1;
    }
    return JNI_VERSION_1_4;
}


