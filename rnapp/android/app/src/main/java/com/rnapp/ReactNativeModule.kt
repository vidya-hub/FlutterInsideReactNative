package com.rnapp
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import io.flutter.plugin.common.MethodChannel


class ReactNativeModule(reactContext: ReactApplicationContext,) : ReactContextBaseJavaModule(reactContext) {
    var methodChannel:MethodChannel? = null;

    @ReactMethod
    fun callFlutterMethod(method: String, arguments:String?, promise: Promise) {
        if (methodChannel==null){
            methodChannel = FlutterCacheManager.getMethodChannel()
        }
        methodChannel?.let {
            currentActivity?.runOnUiThread {
                run {
                    println("Method channel called ${method}")
                    methodChannel?.invokeMethod(method, arguments)
                }
            }
        }
    }
    override fun getName(): String {
        return "ReactNativeModule";
    }
}