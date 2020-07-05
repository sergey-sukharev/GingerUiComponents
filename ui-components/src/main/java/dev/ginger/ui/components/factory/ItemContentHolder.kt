package dev.ginger.ui.components.factory

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx

class ItemContentHolder(
    val rootContainer: ConstraintLayout,
    val titleView: TextView,
    val subtitleView: TextView,
    imageType: Int
) {

    init {
        setLeftMarginByIconType(imageType)
    }

    // Set a left indent from start icon by icon type (dp)
    fun setLeftMarginByIconType(imageType: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootContainer)
        constraintSet.clear(R.id.ginger_text_content, ConstraintSet.START)
        constraintSet.connect(
            R.id.ginger_text_content, ConstraintSet.START,
            R.id.iconStart, ConstraintSet.END,
            getMarginByStartIconType(imageType)
        )
        constraintSet.applyTo(rootContainer)
    }

    private fun getMarginByStartIconType(iconType: Int): Int {
        return when (iconType) {
            1 -> dpToPx(rootContainer.context, 32)
            else -> dpToPx(rootContainer.context, 16)
        }
    }

}