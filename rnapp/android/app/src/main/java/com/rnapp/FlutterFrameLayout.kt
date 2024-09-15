package com.rnapp
import android.view.View
import android.widget.FrameLayout
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.uimanager.ThemedReactContext


class FlutterFrameLayout(private val reactContext: ThemedReactContext) : LifecycleEventListener {

    private val frameLayout: FrameLayout = FrameLayout(reactContext)
    val flutterViewEngine: FlutterViewEngine = FlutterViewEngine(reactContext.applicationContext)

    init {
        reactContext.addLifecycleEventListener(this)
        setupFlutterView()
    }

    private fun setupFlutterView() {
        val activity = reactContext.currentActivity
        if (activity != null) {
            flutterViewEngine.attachFlutterView(activity)
            flutterViewEngine.getFlutterView()?.let { flutterView ->
                frameLayout.addView(flutterView)
            }
        }
    }

    fun getView(): View {
        return frameLayout
    }

    override fun onHostResume() {
        flutterViewEngine.onResume()
    }

    override fun onHostPause() {
        flutterViewEngine.onPause()
    }

    override fun onHostDestroy() {
        flutterViewEngine.detachFlutterView()
        flutterViewEngine.onDestroy()
        reactContext.removeLifecycleEventListener(this)
    }
}