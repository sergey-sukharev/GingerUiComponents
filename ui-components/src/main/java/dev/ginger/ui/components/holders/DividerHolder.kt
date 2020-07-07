package dev.ginger.ui.components.holders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx


class DividerHolder(
    val view: View,
    dividerType: DividerType,
    rootContainer: ConstraintLayout
) {

    init {
        resize(dividerType, rootContainer)
    }

    fun resize(dividerType: DividerType, rootContainer: ConstraintLayout) {
        when (dividerType) {
            DividerType.NONE -> view.visibility = View.INVISIBLE
            DividerType.FULL_BLEED -> {
                measureIndent(rootContainer, ConstraintSet.START, 0)
                measureIndent(rootContainer, ConstraintSet.END, 0)
            }
            DividerType.INSET -> {
                measureIndent(rootContainer, ConstraintSet.START, 32)
                measureIndent(rootContainer, ConstraintSet.END, 0)
            }
            DividerType.MIDDLE -> {
                measureIndent(rootContainer, ConstraintSet.START, 16)
                measureIndent(rootContainer, ConstraintSet.END, 16)
            }
        }
    }

    private fun measureIndent(rootContainer: ConstraintLayout, site: Int, indent: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootContainer)
        constraintSet.clear(R.id.ginger_divider, site)
        constraintSet.connect(
            R.id.ginger_divider, site,
            R.id.parent, site,
            dpToPx(rootContainer.context, indent)
        )
        constraintSet.applyTo(rootContainer)
    }

}