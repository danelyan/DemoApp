package ru.cometrica.demoapp.presentation.common

import androidx.annotation.ColorRes

interface StatusBarPaintable {

    fun paintStatusBar(@ColorRes colorId: Int, isDarkStatusBar: Boolean)
}