package com.ozantok.quizgenius.presentation.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun Fragment.makeStatusBarTransparent(view: View, isLightStatusBar: Boolean = true) {
    val window = requireActivity().window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.TRANSPARENT

    val controller = WindowCompat.getInsetsController(window, view)
    controller.isAppearanceLightStatusBars = isLightStatusBar
}

fun Activity.makeStatusBarTransparent(view: View, isLightStatusBar: Boolean = true) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.TRANSPARENT

    WindowCompat.getInsetsController(window, view)?.let { controller ->
        controller.isAppearanceLightStatusBars = isLightStatusBar
    }
}