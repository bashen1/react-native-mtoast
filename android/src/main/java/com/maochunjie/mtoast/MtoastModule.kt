package com.maochunjie.mtoast

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import es.dmoral.toasty.Toasty

class MtoastModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "Mtoast"
    }

    @SuppressLint("CheckResult")
    @ReactMethod
    fun show(props: ReadableMap) {
        try {
            val config = Toasty.Config.getInstance()
            var title = props.getString("title")
            val titleSize = props.getInt("titleSize")
            var titleColor = props.getString("titleColor")
            val duration = props.getInt("duration")
            var tintColor = props.getString("tintColor")
            var position = props.getString("position")
            val offsetX = props.getInt("offsetX")
            val offsetY = props.getInt("offsetY")


            if (title == null) {
                title = ""
            }
            if (titleSize != 0) {
                config.setTextSize(titleSize)
            }
            if (titleColor == null) {
                titleColor = "#FFFFFF"
            }
            if (tintColor == null) {
                tintColor = ""
            }
            if (position == null) {
                position = "center"
            }
            config.apply() // required

            val toast: Toast? = if (tintColor == "") {
                Toasty.normal(reactApplicationContext, title, duration)
            } else {
                Toasty.custom(
                    reactApplicationContext,
                    title,
                    null,
                    Color.parseColor(tintColor),
                    Color.parseColor(titleColor),
                    duration,
                    false,
                    true
                )
            }
            if (toast != null) {
                toast.setGravity(getGravity(position), offsetX, offsetY)
                toast.show()
            }
        } catch (_: Exception) {
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
