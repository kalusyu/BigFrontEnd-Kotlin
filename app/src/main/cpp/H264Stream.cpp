//
// Created by Administrator on 2020/7/10.
//

#include "H264Stream.h"
#include <jni.h>
#include <string>
#include <stdio.h>
#include "mpp_err.h"
#include <android/log.h>
#include "JniHelper.h"



void
get_dec_param(unsigned char *yuv, unsigned int yuvlen, unsigned int width, unsigned int height) {
    JNIEnv* env = JniHelper::getEnv();
    jclass clazz = (*env).FindClass("com/kalusyu/bigfrontend_kotlin/rtspclient/internal/video/H264Stream");//参数为类路径
    jmethodID mid = (*env).GetMethodID(clazz,"get_dec_param","()V");
//    jobject obj = (*env).NewObject(clazz,mid);
}

class NativeStream {

public:
    NativeStream();

protected:
    virtual ~NativeStream();
};

NativeStream::NativeStream() {
    int result = edde_mpp_init(get_dec_param);
    __android_log_print(ANDROID_LOG_INFO, "native",
                        "result = %d", result);
}

NativeStream::~NativeStream() {

}

static jstring setH624Stream(JNIEnv *env, jobject thiz) {

    unsigned char *buf;
    edde_decode_frame(buf, sizeof(buf));
}

static jlong nativeInit(JNIEnv *env, jclass clazz) {
    NativeStream *im = new NativeStream();
    return reinterpret_cast<jlong>(im);
}


static const JNINativeMethod gMethods[] = {
        {"setH624Stream", "([B)V", (void *) setH624Stream},
        {"nativeInit",    "()J",   (void *) nativeInit}
};


JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {

    __android_log_print(ANDROID_LOG_INFO, "native", "Jni_OnLoad");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    jclass clazz = env->FindClass(
            "com/kalusyu/bigfrontend_kotlin/rtspclient/internal/video/H264Stream");
    if (!clazz) {
        __android_log_print(ANDROID_LOG_INFO, "native",
                            "cannot get class: com/kalusyu/bigfrontend_kotlin/rtspclient/internal/video/H264Stream");
        return -1;
    }
    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0]))) {
        __android_log_print(ANDROID_LOG_INFO, "native", "register native method failed!\n");
        return -1;
    }
    return JNI_VERSION_1_4;
}



