package dev.ginger.ui.components.factory

import android.view.View
import android.widget.ImageView
import dev.ginger.ui.components.utils.dpToPx

class ActionIconHolder(
    view: ImageView,
    drawableRes: Int?
) : BaseIconHolder(view, drawableRes) {

    override fun measureSize() {
        when(type) {
            0 -> view.visibility = View.GONE
            else -> {
                setImageViewSize(sizeIcon[0], sizeIcon[1])
            }
        }
    }

}