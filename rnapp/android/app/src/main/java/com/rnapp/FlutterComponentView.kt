package com.rnapp
import android.view.ViewGroup
import android.widget.FrameLayout
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class FlutterComponentView : SimpleViewManager<ViewGroup>() {

    override fun getName(): String {
        return "FlutterComponentView"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): FrameLayout {
        val flutterViewManager = FlutterFrameLayout(reactContext)
        return flutterViewManager.getView() as FrameLayout
    }
}