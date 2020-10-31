package dev.ginger.ui.components.holders.icon

import android.view.View
import android.widget.ImageView
import dev.ginger.ui.components.holders.icon.BaseIconHolder

class StartIconHolder(
    view: ImageView,
    drawableRes: Int?
) : BaseIconHolder(view, drawableRes) {

    override fun measureSize() {
        when (type) {
            0 -> view.visibility = View.GONE
            1 -> {
                setImageViewSize(sizeIcon[0], sizeIcon[1])
            }
            2 -> {
                setImageViewSize(sizeSmall[0], sizeSmall[1])
            }
            3 -> {
                setImageViewSize(sizeMiddle[0], sizeMiddle[1])
            }
            4 -> {
                setImageViewSize(sizeLarge[0], sizeLarge[1])
            }
        }
    }

}