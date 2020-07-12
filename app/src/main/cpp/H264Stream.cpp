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


jclass global_class = NULL;
jmethodID get_dec_param_android;

JavaVM *jvm = NULL;

void
get_dec_param(unsigned char *yuv, unsigned int yuvlen, unsigned int width, unsigned int height) {
    __android_log_print(ANDROID_LOG_INFO, "nativeH264", "get_dec_param callback");
    JNIEnv *env = NULL;

    int status;
    bool isAttached = false;
    status = jvm->GetEnv((void **) &env, JNI_VERSION_1_4);
    if (status < 0) {
        if (jvm->AttachCurrentThread(&env, NULL))////将当前线程注册到虚拟机中
        {
            return;
        }
        isAttached = true;
    }
    //实例化该类
    jobject obj = env->AllocObject(global_class);//分配新 Java 对象而不调用该对象的任何构造函数。返回该对象的引用
    //调用Java方法
    jbyteArray byteArray = env->NewByteArray(yuvlen);
    env->SetByteArrayRegion(byteArray, 0, yuvlen, (jbyte *) yuv);
    (env)->CallVoidMethod(obj, get_dec_param_android, byteArray, (jint) yuvlen, (jint) width,
                          (jint) height);

    if (isAttached) {
        jvm->DetachCurrentThread();
    }

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
    jvm = vm;
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

    global_class = (jclass) env->NewGlobalRef(clazz);
    get_dec_param_android = (env)->GetMethodID(global_class, "get_dec_param", "([BIII)V");

    return JNI_VERSION_1_4;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return;
    }
    env->DeleteGlobalRef(global_class);
    return;
}




