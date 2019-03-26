package ru.cometrica.demoapp.presentation.common

import android.view.View.VISIBLE
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import ru.cometrica.demoapp.R
import ru.cometrica.demoapp.presentation.common.actionbar.ActionBarConfig
import ru.cometrica.demoapp.presentation.common.actionbar.ActionBarConfiguratorProvider


open class BaseFragment : Fragment() {

    private val contentView get() = activity?.window?.decorView
    private val toolbarConfigurator
        get() = (activity as? ActionBarConfiguratorProvider)?.actionBarConfigurator

    fun showUnknownError() {
        showError(R.string.error_unknown)
    }

    fun showNetworkError() {
        showError(R.string.error_internet_not_available)
    }

    fun showError(@StringRes stringId: Int) {
        showError(getString(stringId))
    }

    fun showError(message: String) {
        showErrorDialog(message)
    }

    private fun showErrorSnackbar(message: String) {
        contentView?.let {
            Snackbar.make(it, message, LENGTH_SHORT).show()
        }
    }

    private fun showErrorDialog(message: String) {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    protected fun applyActionBarConfig(toolbarConfig: ActionBarConfig) {
        toolbarConfigurator?.applyConfig(toolbarConfig)
    }

    protected fun showActionBar() {
        toolbarConfigurator?.showActionBar()
    }

    protected fun hideActionBar() {
        toolbarConfigurator?.hideActionBar()
    }

    protected fun paintActionBar(@ColorRes colorId: Int) {
        toolbarConfigurator?.paintActionBar(colorId)
    }

    protected fun paintActionBarTitle(@ColorRes colorId: Int) {
        toolbarConfigurator?.paintActionBarTitle(colorId)
    }

    protected fun flatActionBar() {
        toolbarConfigurator?.flatActionBar()
    }

    protected fun elevateActionBar(elevation: Float) {
        toolbarConfigurator?.elevateActionBar(elevation)
    }

    protected fun paintStatusBar(@ColorRes colorId: Int, isDarkActionBar: Boolean) {
        (activity as? StatusBarPaintable)?.paintStatusBar(colorId, isDarkActionBar)
    }

    protected open fun setupStatusBar() {
        paintStatusBar(R.color.status_bar, false)
    }

    protected open fun setupActionBar() {
        applyActionBarConfig(
            ActionBarConfig(
                visible = VISIBLE,
                displayHomeAsNav = true,
                elevation = 0f,
                backgroundColor = R.color.action_bar,
                titleColor = R.color.light_text,
                iconColor = R.color.navigation
            )
        )
    }

}