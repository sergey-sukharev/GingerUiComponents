package dev.ginger.ui.components.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun dpToPx(context: Context, dp: Int): Int {
    return (dp * (context.resources.getDisplayMetrics().xdpi /
            DisplayMetrics.DENSITY_DEFAULT)).roundToInt();
}