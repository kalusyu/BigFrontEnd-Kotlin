//
// Created by kalus on 2020/7/12.
//


//#include <jni.h>
//#include <android_runtime/AndroidRuntime.h>
//#include <android_runtime/android_view_Surface.h>
//#include <gui/Surface.h>
//#include <assert.h>
//#include <utils/Log.h>
//#include <JNIHelp.h>
//#include <media/stagefright/foundation/ADebug.h>
//#include <ui/GraphicBufferMapper.h>
//#include <cutils/properties.h>
//using namespace android;
//
//static sp<Surface> surface;
//
//static int ALIGN(int x, int y) {
//    // y must be a power of 2.
//    return (x + y - 1) & ~(y - 1);
//}
//
//static void render(
//        const void *data, size_t size, const sp<ANativeWindow> &nativeWindow,int width,int height) {
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//    sp<ANativeWindow> mNativeWindow = nativeWindow;
//    int err;
//    int mCropWidth = width;
//    int mCropHeight = height;
//
//    int halFormat = HAL_PIXEL_FORMAT_YV12;//颜色空间
//    int bufWidth = (mCropWidth + 1) & ~1;//按2对齐
//    int bufHeight = (mCropHeight + 1) & ~1;
//
//    CHECK_EQ(0,
//             native_window_set_usage(
//                     mNativeWindow.get(),
//                     GRALLOC_USAGE_SW_READ_NEVER | GRALLOC_USAGE_SW_WRITE_OFTEN
//                     | GRALLOC_USAGE_HW_TEXTURE | GRALLOC_USAGE_EXTERNAL_DISP));
//
//    CHECK_EQ(0,
//             native_window_set_scaling_mode(
//                     mNativeWindow.get(),
//                     NATIVE_WINDOW_SCALING_MODE_SCALE_CROP));
//
//    // Width must be multiple of 32???
//    //很重要,配置宽高和和指定颜色空间yuv420
//    //如果这里不配置好，下面deque_buffer只能去申请一个默认宽高的图形缓冲区
//    CHECK_EQ(0, native_window_set_buffers_geometry(
//            mNativeWindow.get(),
//            bufWidth,
//            bufHeight,
//            halFormat));
//
//
//    ANativeWindowBuffer *buf;//描述buffer
//    //申请一块空闲的图形缓冲区
//    if ((err = native_window_dequeue_buffer_and_wait(mNativeWindow.get(),
//                                                     &buf)) != 0) {
//        ALOGW("Surface::dequeueBuffer returned error %d", err);
//        return;
//    }
//
//    GraphicBufferMapper &mapper = GraphicBufferMapper::get();
//
//    Rect bounds(mCropWidth, mCropHeight);
//
//    void *dst;
//    CHECK_EQ(0, mapper.lock(//用来锁定一个图形缓冲区并将缓冲区映射到用户进程
//            buf->handle, GRALLOC_USAGE_SW_WRITE_OFTEN, bounds, &dst));//dst就指向图形缓冲区首地址
//
//    if (true){
//        size_t dst_y_size = buf->stride * buf->height;
//        size_t dst_c_stride = ALIGN(buf->stride / 2, 16);//1行v/u的大小
//        size_t dst_c_size = dst_c_stride * buf->height / 2;//u/v的大小
//
//        memcpy(dst, data, dst_y_size + dst_c_size*2);//将yuv数据copy到图形缓冲区
//    }
//
//    CHECK_EQ(0, mapper.unlock(buf->handle));
//
//    if ((err = mNativeWindow->queueBuffer(mNativeWindow.get(), buf,
//                                          -1)) != 0) {
//        ALOGW("Surface::queueBuffer returned error %d", err);
//    }
//    buf = NULL;
//}
//
//static void nativeTest(){
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//}
//
//static jboolean
//nativeSetVideoSurface(JNIEnv *env, jobject thiz, jobject jsurface){
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//    surface = android_view_Surface_getSurface(env, jsurface);
//    if(android::Surface::isValid(surface)){
//        ALOGE("surface is valid ");
//    }else {
//        ALOGE("surface is invalid ");
//        return false;
//    }
//    ALOGE("[%s][%d]\n",__FILE__,__LINE__);
//    return true;
//}
//static void
//nativeShowYUV(JNIEnv *env, jobject thiz,jbyteArray yuvData,jint width,jint height){
//    ALOGE("width = %d,height = %d",width,height);
//    jint len = env->GetArrayLength(yuvData);
//    ALOGE("len = %d",len);
//    jbyte *byteBuf = env->GetByteArrayElements(yuvData, 0);
//    render(byteBuf,len,surface,width,height);
//}
//static JNINativeMethod gMethods[] = {
//        {"nativeTest",       			"()V",    							(void *)nativeTest},
//        {"nativeSetVideoSurface",		"(Landroid/view/Surface;)Z", 		(void *)nativeSetVideoSurface},
//        {"nativeShowYUV",				"([BII)V",							(void *)nativeShowYUV},
//};
//
//static const char* const kClassPathName = "com/example/myyuvviewer/MainActivity";
//
//// This function only registers the native methods
//static int register_com_example_myyuvviewer(JNIEnv *env)
//{
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//    return AndroidRuntime::registerNativeMethods(env,
//                                                 kClassPathName, gMethods, NELEM(gMethods));
//}
//
//jint JNI_OnLoad(JavaVM* vm, void* reserved)
//{
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//    JNIEnv* env = NULL;
//    jint result = -1;
//
//    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
//        ALOGE("ERROR: GetEnv failed\n");
//        goto bail;
//    }
//    assert(env != NULL);
//    ALOGE("[%s]%d",__FILE__,__LINE__);
//    if (register_com_example_myyuvviewer(env) < 0) {
//        ALOGE("ERROR: MediaPlayer native registration failed\n");
//        goto bail;
//    }
//
//    /* success -- return valid version number */
//    result = JNI_VERSION_1_4;
//
//    bail:
//    return result;
//}