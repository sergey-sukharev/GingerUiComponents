package dev.ginger.ui.components.factory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import dev.ginger.ui.R

class GingerLineItemViewFactory(
    private val viewContainer: View
) {

    val startIcon: ImageView? = viewContainer.findViewById(R.id.iconStart)
    val endIcon: ImageView? = viewContainer.findViewById(R.id.iconEnd)
    val titleView: TextView? = viewContainer.findViewById(R.id.ginger_label_title)
    val subtitleView: TextView? = viewContainer.findViewById(R.id.ginger_label_subtitle)
    val dividerView: View? = viewContainer.findViewById(R.id.ginger_divider)
    val containerView: ConstraintLayout? = viewContainer.findViewById(R.id.container)

    init {
        println()
    }
}