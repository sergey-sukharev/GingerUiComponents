package dev.ginger.ui.components.factory

import android.view.View
import android.widget.ImageView
import dev.ginger.ui.components.utils.dpToPx

class StartIconHolder(
    view: ImageView,
    drawableRes: Int?
) : BaseIconHolder(view, drawableRes) {

    override fun measureSize() {
        when (type) {
            0 -> view.visibility = View.GONE
            1 -> {
                view.layoutParams.apply {
                    width = dpToPx(view.context, sizeDefault[0])
                    height = dpToPx(view.context, sizeDefault[1])
                }
            }
            4 -> {
                view.layoutParams.apply {
                    width = dpToPx(view.context, sizeLarge[0])
                    height = dpToPx(view.context, sizeLarge[1])
                }
            }
            else -> {
                view.layoutParams.apply {
                    width = dpToPx(view.context, sizeMiddle[0])
                    height = dpToPx(view.context, sizeMiddle[1])
                }
            }
        }
    }

}