package dev.ginger.ui.components.holders.icon


import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import dev.ginger.ui.components.utils.dpToPx

abstract class BaseIconHolder(
    val view: ImageView,
    drawableRes: Int?
) {

    val sizeIcon = listOf(24, 24)
    val sizeSmall = listOf(40, 40)
    val sizeMiddle = listOf(56, 56)
    val sizeLarge = listOf(100, 56)

    var type: Int = ActionIconType.ICON.ordinal
        set(value) {
            field = value
            drawable ?: run { field = ActionIconType.NONE.ordinal }
            view.setImageDrawable(drawable)
            measureSize()
        }

    var drawable: Drawable? = null
        set(value) {
            field = value
            field?.also { draw ->
                tint?.let {
                    DrawableCompat.setTint(view.drawable, it)
                }
            } ?: run {
                type = ActionIconType.NONE.ordinal
            }
            view.setImageDrawable(field)
            measureSize()
        }

    var tint: Int? = null
        set(value) {
            field = value
            field?.let {
                view.drawable?.let { drawable -> DrawableCompat.setTint(drawable, it) }
            }
        }

    init {
        this.drawable = getDrawableFromRes(drawableRes)
        view.setImageDrawable(this.drawable)
        measureSize()
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

    protected fun setImageViewSize(width: Int, height: Int) {
        view.visibility = View.VISIBLE
        (view.layoutParams as ConstraintLayout.LayoutParams).apply {
            this.width = dpToPx(view.context, width)
            this.height = dpToPx(view.context, height)
        }
        view.requestLayout()
    }

}


