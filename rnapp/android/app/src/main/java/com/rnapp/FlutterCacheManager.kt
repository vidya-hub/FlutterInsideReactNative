package com.rnapp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

object FlutterCacheManager {
    private var methodChannel: MethodChannel? = null;
    fun setMethodChannel(flutterEngine: FlutterEngine) {
        methodChannel = flutterEngine.dartExecutor.binaryMessenger.let {
            MethodChannel(
                it,
                "com.vidya.fluttermodule"
            )
        }
    }
    fun getMethodChannel(): MethodChannel? {
        return methodChannel;
    }
}
