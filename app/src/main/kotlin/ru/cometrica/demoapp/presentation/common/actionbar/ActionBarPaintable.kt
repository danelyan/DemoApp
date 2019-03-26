package ru.cometrica.demoapp.presentation.common.actionbar

import androidx.annotation.ColorRes

interface ActionBarPaintable {

    fun paintActionBar(@ColorRes colorId: Int)

    fun paintActionBarTitle(@ColorRes colorId: Int)

}
