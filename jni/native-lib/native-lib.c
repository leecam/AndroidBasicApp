#include <android/log.h>
#include <jni.h>

#define ALOG(...) __android_log_print(ANDROID_LOG_WARN, "BasicAppActivity-native", __VA_ARGS__)

void Java_org_leecam_basic_1app_BasicAppActivity_sayHello(JNIEnv* env, jobject this_obj) {
  ALOG("Hello from JNI");
}