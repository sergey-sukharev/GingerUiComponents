package dev.ginger.ui.components.holders.icon

import android.view.View
import android.widget.ImageView
import dev.ginger.ui.components.holders.icon.BaseIconHolder

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