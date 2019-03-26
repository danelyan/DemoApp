package ru.cometrica.demoapp.presentation.common.actionbar

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat


interface ActionBarConfigurator :
    ActionBarConfigurable,
    ActionBarHideable,
    ActionBarPaintable,
    ActionBarFlattable,
    TitleChangeable {

    override fun hideActionBar()

    override fun showActionBar()

    override fun paintActionBar(@ColorRes colorId: Int)

    override fun paintActionBarTitle(@ColorRes colorId: Int)

    override fun flatActionBar()

    override fun elevateActionBar(elevation: Float)

    override fun setTitle(title: String)

    override fun applyConfig(config: ActionBarConfig)

    fun setOverflow(overflow: Boolean)
}

class ToolbarActionBarConfiguratorImpl(private val toolbar: Toolbar) :
    ActionBarConfigurator {

    override fun hideActionBar() {
        toolbar.visibility = View.GONE
    }

    override fun showActionBar() {
        toolbar.visibility = View.VISIBLE
    }

    override fun paintActionBar(colorId: Int) {
        val color = ContextCompat.getColor(toolbar.context, colorId)
        toolbar.background = ColorDrawable(color)
    }

    override fun paintActionBarTitle(colorId: Int) {
        toolbar.setTitleTextColor(ContextCompat.getColor(toolbar.context, colorId))
    }

    override fun flatActionBar() {
        toolbar.elevation = 0f
    }

    override fun elevateActionBar(elevation: Float) {
        toolbar.elevation = elevation
    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }

    override fun setOverflow(overflow: Boolean) {
        if (overflow) {
            // TODO change layout
        }
    }

    override fun applyConfig(config: ActionBarConfig) {
        config.visible?.let { toolbar.visibility = it }
        config.elevation?.let { elevateActionBar(it) }
        config.backgroundColor?.let { paintActionBar(it) }
        config.title?.let { toolbar.title = it }
        config.titleColor?.let { paintActionBarTitle(it) }
        config.displayHomeAsNav?.let {
            if (it) toolbar.navigationIcon = toolbar.navigationIcon
            else toolbar.navigationIcon = null
        }

        config.iconColor?.let {
            (toolbar.navigationIcon as? DrawerArrowDrawable)?.color =
                ContextCompat.getColor(toolbar.context, it)
        }
    }

}
