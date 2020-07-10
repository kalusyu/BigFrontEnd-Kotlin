#include <jni.h>
#include <string>
#include <stdio.h>
#include "mpp_err.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_kalusyu_bigfrontend_1kotlin_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    int result = edde_mpp_main();
//    char *hello = "Hello from C++";
    const char *str = "abc";
    char buff[30] = {0};

    snprintf(buff, sizeof(buff), "%d%s", result, str);
    return env->NewStringUTF(buff);
}
