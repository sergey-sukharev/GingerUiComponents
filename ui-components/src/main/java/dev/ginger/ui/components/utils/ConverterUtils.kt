package dev.ginger.ui.components.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun dpToPx(context: Context, dp: Int): Int {
    return (dp * (context.resources.getDisplayMetrics().xdpi /
            DisplayMetrics.DENSITY_DEFAULT)).roundToInt();
}

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()