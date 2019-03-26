package ru.cometrica.demoapp.presentation.common

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber

fun NavController.navigateSafely(action: NavDirections) {
    try {
        navigate(action)
    } catch (e: IllegalArgumentException) {
        Timber.e(e.localizedMessage)
        // expected error, do not rethrow
    }
}