package com.maochunjie.mtoast

import android.graphics.Color
import android.view.Gravity
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import es.dmoral.toasty.Toasty

class MtoastModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "Mtoast"
    }

    @ReactMethod
    open fun show(props: ReadableMap) {
        val config = Toasty.Config.getInstance()
        val title = props.getString("title")
        val titleSize = props.getInt("titleSize")
        val titleColor = props.getString("titleColor")

        val duration = props.getInt("duration")
        val tintColor = props.getString("tintColor")

        val position = props.getString("position")

        val offsetX = props.getInt("offsetX")
        val offsetY = props.getInt("offsetY")

        if (titleSize != 0) {
            config.setTextSize(titleSize)
        }
        config.apply() // required
        var toast: Toast? = null
        if (tintColor!!.length <= 0) {
            toast = Toasty.normal(currentActivity!!, title!!, duration)
        } else {
            toast = Toasty.custom(currentActivity!!, title!!, null, Color.parseColor(tintColor), Color.parseColor(titleColor), duration, false, true)
        }
        if (toast != null) {
            toast.setGravity(getGravity(position!!), offsetX, offsetY)
            toast.show()
        }
    }

    private fun getGravity(gravity: String): Int {
        return when (gravity) {
            "top" -> Gravity.TOP
            "center" -> Gravity.CENTER
            "bottom" -> Gravity.BOTTOM
            else -> Gravity.BOTTOM
        }
    }
}
