package ru.cometrica.demoapp.presentation.common.actionbar

import androidx.annotation.ColorRes

interface ActionBarConfigurable {

    fun applyConfig(config: ActionBarConfig)

}

data class ActionBarConfig(
    val visible: Int? = null,
    val title: String? = null,
    @ColorRes val titleColor: Int? = null,
    @ColorRes val backgroundColor: Int? = null,
    @ColorRes val iconColor: Int? = null,
    val elevation: Float? = null,
    val overflow: Boolean? = null,
    val displayHomeAsNav: Boolean? = null
)

