package com.ozantok.plantapp.presentation.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView

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

fun TextView.applyGoldGradient() {

    this.background = null
    this.alpha = 1.0f
    val paint = this.paint
    paint.clearShadowLayer()
    paint.isAntiAlias = true

    val width = paint.measureText(this.text.toString())
    val textShader: Shader = LinearGradient(
        0f, 0f, width, this.textSize,
        intArrayOf(
            Color.parseColor("#E5C990"),
            Color.parseColor("#E4B046")
        ),
        null,
        Shader.TileMode.CLAMP
    )

    this.paint.shader = textShader
    this.invalidate()
}

fun TextView.addSoftShadow() {
    this.setShadowLayer(1.5f, 0.5f, 1f, Color.parseColor("#50000000"))
}


fun TextView.applyGradient(startColor: String, endColor: String) {
    val paint = this.paint
    val width = paint.measureText(this.text.toString())
    val textShader: Shader = LinearGradient(
        0f, 0f, width, this.textSize,
        intArrayOf(
            Color.parseColor(startColor),
            Color.parseColor(endColor)
        ),
        null,
        Shader.TileMode.CLAMP
    )
    this.paint.shader = textShader
}