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
    __android_log_print(ANDROID_LOG_INFO, "ybw", "nativeH264 get_dec_param callback");
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
    __android_log_print(ANDROID_LOG_INFO, "ybw",
                        "native result = %d", result);
    // 文件
//    unsigned char sps[]={0x00,0x00,0x00,0x01,0x67,0x64,0x00,0x1F,0xAC,0x1A,0xD0,0x14,0x07,0xB4,0x20,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x05,0x11,0xE2,0x85,0x54};
//    unsigned char pps[]={0x00,0x00,0x00,0x01,0x68,0xEE,0x3C,0xB0};
    unsigned char sps[]={0x00,0x00,0x00,0x01,0x27,0x64,0x00,0x28,0xAC,0x1A,0xD0,0x14,0x07,0xB4,0x20,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x07,0x91,0xE2,0x85,0x54};
    unsigned char pps[]={0x00,0x00,0x00,0x01,0x28,0xEE,0x3C,0xB0};


    // rtsp流
//    unsigned char sps[] = {0x00, 0x00, 0x00, 0x01,0x00, 0x00, 0x00, 0x01, 0x67, 0x4D, 0x40, 0x1F, 0xEC, 0xA0, 0x7E, 0x09,
//                           0x3C, 0xB1, 0x18, 0x08, 0x80, 0x00, 0x00, 0x03, 0x00, 0x80, 0x00, 0x00,
//                           0x18, 0x07, 0x8C, 0x18, 0xCB};
//    unsigned char pps[] = {0x00, 0x00, 0x00, 0x01,0x00, 0x00, 0x00, 0x01, 0x68, 0xEB, 0xEF, 0x20};
//    unsigned char sps[]={0x67,0x64,0x00,0x1F,0xAC,0x1A,0xD0,0x14,0x07,0xB4,0x20,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x05,0x11,0xE2,0x85,0x54};
//    unsigned char pps[]={0x68,0xEE,0x3C,0xB0};


    __android_log_print(ANDROID_LOG_INFO, "ybw",
                        "native result = %d，sps=%d", result, sizeof(sps));
    edde_decode_frame(sps, sizeof(sps));
    edde_decode_frame(pps, sizeof(pps));
}

NativeStream::~NativeStream() {

}

static void setH624Stream(JNIEnv *env, jobject thiz, jbyteArray byte) {
    int length = (*env).GetArrayLength(byte);
    __android_log_print(ANDROID_LOG_INFO, "ybw",
                        "setH624Stream bytebuf = %d", length);

    jboolean isCopy = JNI_TRUE;
    jbyte *bBuffer = env->GetByteArrayElements(byte, &isCopy);
    unsigned char *buff = (unsigned char *) bBuffer;

    __android_log_print(ANDROID_LOG_INFO, "ybw",
                        "setH624Stream convert %x,%x,%x,%x,%x,%x,%x,%x", bBuffer[0], bBuffer[1],
                        bBuffer[2],
                        bBuffer[3], bBuffer[4], bBuffer[5], bBuffer[6],
                        bBuffer[7]);
    edde_decode_frame(buff, length);

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




