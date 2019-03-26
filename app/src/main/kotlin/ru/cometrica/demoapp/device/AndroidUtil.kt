package ru.cometrica.demoapp.device

import android.os.Build

/**
 *  Предоставляет специфичные для платформы Android утилитарные методы.
 */

object AndroidUtil {

    fun hasLollipop() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun hasNougat() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    fun hasOreo() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

}
