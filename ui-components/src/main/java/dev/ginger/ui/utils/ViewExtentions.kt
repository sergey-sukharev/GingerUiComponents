package dev.ginger.ui.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView


fun Int.getColor(context: Context): Int {
    return try {
        context.getColor(this)
    } catch (e: Resources.NotFoundException) {
        this
    }
}

fun View.setVisibleOrInvisible(state: Boolean) {
    visibility = if (state) View.VISIBLE else View.INVISIBLE
}

fun View.setVisibleOrGone(state: Boolean): Boolean {
    visibility = if (state) View.VISIBLE else View.GONE
    return state
}

fun ImageView.setIconOrGone(drawable: Drawable?) {
    if (drawable != null)
        setImageDrawable(drawable)
    setVisibleOrGone(drawable != null)
}

fun ImageView.setIconOrInvisible(drawable: Drawable?) {
    if (drawable != null)
        setImageDrawable(drawable)
    setVisibleOrInvisible(drawable != null)
}