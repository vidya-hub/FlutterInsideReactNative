package com.rnapp
import android.app.Activity
import android.content.Context
import android.view.View
import io.flutter.embedding.android.FlutterSurfaceView
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.platform.PlatformPlugin

class FlutterViewEngine(private val context: Context) {
    private var flutterEngine: FlutterEngine? = null
    private var flutterView: FlutterView? = null
    private var platformPlugin: PlatformPlugin? = null

    fun attachFlutterView(activity: Activity) {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(context)
            FlutterCacheManager.setMethodChannel(flutterEngine!!)
            flutterEngine?.dartExecutor?.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
        }
        if (flutterView == null) {
            val flutterSurfaceView = FlutterSurfaceView(context)
            flutterView = FlutterView(context, flutterSurfaceView).apply {
                id = View.generateViewId()
            }
        }
        flutterView?.attachToFlutterEngine(flutterEngine!!)
        platformPlugin = PlatformPlugin(activity, flutterEngine!!.platformChannel)
        flutterEngine?.lifecycleChannel?.appIsResumed()
    }


    fun detachFlutterView() {
        platformPlugin?.destroy()
        platformPlugin = null
        flutterView?.detachFromFlutterEngine()
    }

    fun onResume() {
        flutterEngine?.lifecycleChannel?.appIsResumed()
    }

    fun onPause() {
        flutterEngine?.lifecycleChannel?.appIsInactive()
    }
    fun onDestroy() {
        flutterEngine?.destroy()
        flutterEngine = null
    }

    fun getFlutterView(): FlutterView? = flutterView
}