package dev.ginger.ui.components.holders.divider

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx

class DividerHolder(
    val view: View,
    dividerType: DividerType,
    dividerColor: Int,
    rootContainer: ConstraintLayout
) {

    init {
        setTint(dividerColor)
        resize(dividerType, rootContainer)
    }

    private fun setTint(color: Int) {
        view.setBackgroundColor(color)
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
                measureRightSide(rootContainer, ConstraintSet.START)
            }
            DividerType.MIDDLE -> {
                measureIndent(rootContainer, ConstraintSet.START, 16)
                measureIndent(rootContainer, ConstraintSet.END, 16)
            }
        }
    }

    private fun measureRightSide(rootContainer: ConstraintLayout, site: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootContainer)
        constraintSet.clear(R.id.ginger_divider, site)
        constraintSet.connect(
            R.id.ginger_divider, site,
            R.id.ginger_text_content, site,
            0
        )
        constraintSet.applyTo(rootContainer)
    }

    private fun measureIndent(rootContainer: ConstraintLayout, site: Int, indent: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootContainer)
        constraintSet.clear(R.id.ginger_divider, site)
        constraintSet.connect(
            R.id.ginger_divider, site,
            R.id.container, site,
            dpToPx(rootContainer.context, indent)
        )
        constraintSet.applyTo(rootContainer)
    }

}