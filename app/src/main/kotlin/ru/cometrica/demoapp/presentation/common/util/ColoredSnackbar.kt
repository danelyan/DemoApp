package ru.cometrica.demoapp.presentation.common.util

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ru.cometrica.demoapp.R

object ColoredSnackbar {

    fun info(snackbar: Snackbar) = colorSnackbar(snackbar, R.color.info)

    fun warning(snackbar: Snackbar) = colorSnackbar(snackbar, R.color.warning)

    fun alert(snackbar: Snackbar) = colorSnackbar(snackbar, R.color.alert)

    fun confirm(snackbar: Snackbar) = colorSnackbar(snackbar, R.color.confirm)

    private fun colorSnackbar(snackbar: Snackbar, @ColorRes backgroundColorId: Int): Snackbar {
        snackbar.view.apply {
            setBackgroundColor(ContextCompat.getColor(context, backgroundColorId))
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.snackbar_text))
        }
        return snackbar
    }

}