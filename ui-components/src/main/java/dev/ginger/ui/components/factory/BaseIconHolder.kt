package dev.ginger.ui.components.factory


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat

abstract class BaseIconHolder(
    val view: ImageView,
    drawableRes: Int?
) {

    val sizeDefault = listOf(32, 32)
    val sizeSmall = listOf(24, 24)
    val sizeMiddle = listOf(40, 40)
    val sizeLarge = listOf(100, 56)

    var type: Int = 1
    set(value) {
        field = value
        measureSize()
    }

    var drawable: Drawable? = null
        set(value) {
            field = value
            field?.also { draw ->
                tint?.let {
                    DrawableCompat.setTint(draw, it)
                }
            } ?: run {
                type = 0
            }
            measureSize()
        }

    var tint: Int? = null
        set(value) {
            field = value
            field?.let {
                DrawableCompat.setTint(view.drawable, it)
            }
        }

    init {
        this.drawable = getDrawableFromRes(drawableRes)
    }

    fun setDrawableFromRes(resId: Int?) {
        drawable = getDrawableFromRes(resId)
    }

    private fun getDrawableFromRes(resId: Int?): Drawable? {
        return resId?.let {
            view.resources.getDrawable(it, null)
        } ?: run { null }
    }

    abstract fun measureSize()

}


