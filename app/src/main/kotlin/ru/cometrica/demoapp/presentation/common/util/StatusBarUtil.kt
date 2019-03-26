package ru.cometrica.demoapp.presentation.common.util

import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import ru.cometrica.demoapp.device.AndroidUtil


object StatusBarUtil {

    fun paintStatusBar(window: Window, colorResId: Int, isDarkStatusBar: Boolean) {
        if (isDarkStatusBar) {
            setupDarkStatusBar(window)
        } else {
            setupLightStatusBar(window)
        }

        @ColorInt
        val statusBarColor = ContextCompat.getColor(window.context, colorResId)
        setStatusBarColor(window, statusBarColor)
    }

    private fun setStatusBarColor(window: Window, @ColorInt color: Int) {
        if (AndroidUtil.hasLollipop()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    private fun setupLightStatusBar(window: Window) {
        if (AndroidUtil.hasMarshmallow()) {
            val decor = window.decorView
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }
    }

    private fun setupDarkStatusBar(window: Window) {
        if (AndroidUtil.hasMarshmallow()) {
            val decor = window.decorView
            var flags = decor.systemUiVisibility
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            decor.systemUiVisibility = flags
        }
    }

}