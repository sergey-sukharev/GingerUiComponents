package dev.ginger.ui.components.factory

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import dev.ginger.ui.R
import dev.ginger.ui.components.holders.GingerActionType
import dev.ginger.ui.components.utils.dpToPx

class GingerLineItemViewFactory(
    private val viewContainer: View,
    actionType: GingerActionType
) {

    val startIcon: ImageView? = viewContainer.findViewById(R.id.iconStart)

    //    val endIcon: ImageView? = viewContainer.findViewById(R.id.iconEnd)
    val titleView: TextView? = viewContainer.findViewById(R.id.ginger_label_title)
    val subtitleView: TextView? = viewContainer.findViewById(R.id.ginger_label_subtitle)
    val dividerView: View? = viewContainer.findViewById(R.id.ginger_divider)
    val containerView: ConstraintLayout? = viewContainer.findViewById(R.id.container)
    val textContentView: LinearLayout? = viewContainer.findViewById(R.id.ginger_text_content)

    var actionView: Any? = null

    init {
        println()
        if (actionType == GingerActionType.SWITCH)
            addSwitchAction()
        else if (actionType == GingerActionType.CHECKBOX)
            addCheckboxAction()
        else
            addIconAction()
    }

    private fun addCheckboxAction() {
        val checkbox = CheckBox(viewContainer.context)
        val id = View.generateViewId()
        checkbox.id = id

        containerView?.addView(checkbox)

        val constraintSet = ConstraintSet()
        constraintSet.clone(containerView)

        // END indent
        constraintSet.connect(
            id, ConstraintSet.END,
            R.id.container, ConstraintSet.END, dpToPx(viewContainer.context, 16)
        )

        // TOP indent
        constraintSet.connect(
            id, ConstraintSet.TOP,
            R.id.container, ConstraintSet.TOP, dpToPx(viewContainer.context, 16)
        )

        // BOTTOM indent
        constraintSet.connect(
            id, ConstraintSet.BOTTOM,
            R.id.container, ConstraintSet.BOTTOM, dpToPx(viewContainer.context, 16)
        )

        //
        constraintSet.connect(
            R.id.ginger_text_content, ConstraintSet.END,
            id, ConstraintSet.START, dpToPx(viewContainer.context, 16)
        )


        constraintSet.applyTo(containerView)

        actionView = checkbox
    }

    private fun addSwitchAction() {
        val switch = Switch(viewContainer.context)
        val id = View.generateViewId()
        switch.id = id

        containerView?.addView(switch)

        val constraintSet = ConstraintSet()
        constraintSet.clone(containerView)

        // END indent
        constraintSet.connect(
            id, ConstraintSet.END,
            R.id.container, ConstraintSet.END, dpToPx(viewContainer.context, 16)
        )

        // TOP indent
        constraintSet.connect(
            id, ConstraintSet.TOP,
            R.id.container, ConstraintSet.TOP, dpToPx(viewContainer.context, 16)
        )

        // BOTTOM indent
        constraintSet.connect(
            id, ConstraintSet.BOTTOM,
            R.id.container, ConstraintSet.BOTTOM, dpToPx(viewContainer.context, 16)
        )

        //
        constraintSet.connect(
            R.id.ginger_text_content, ConstraintSet.END,
            id, ConstraintSet.START, dpToPx(viewContainer.context, 16)
        )


        constraintSet.applyTo(containerView)

        actionView = switch
    }

    private fun addIconAction() {
        val imageView = ImageView(viewContainer.context)
        imageView.visibility = View.GONE

        val id = View.generateViewId()
        imageView.setImageDrawable(viewContainer.resources.getDrawable(R.drawable.ic_ginger, null))

        val params = ConstraintLayout.LayoutParams(
            dpToPx(viewContainer.context, 24),
            dpToPx(viewContainer.context, 24)
        )
        imageView.layoutParams = params
        imageView.layoutParams
        imageView.id = id
        containerView?.addView(imageView)

        val constraintSet = ConstraintSet()
        constraintSet.clone(containerView)

        // END indent
        constraintSet.connect(
            id, ConstraintSet.END,
            R.id.container, ConstraintSet.END, dpToPx(viewContainer.context, 16)
        )

        // TOP indent
        constraintSet.connect(
            id, ConstraintSet.TOP,
            R.id.container, ConstraintSet.TOP, dpToPx(viewContainer.context, 16)
        )

        // BOTTOM indent
        constraintSet.connect(
            id, ConstraintSet.BOTTOM,
            R.id.container, ConstraintSet.BOTTOM, dpToPx(viewContainer.context, 16)
        )

        //
        constraintSet.connect(
            R.id.ginger_text_content, ConstraintSet.END,
            id, ConstraintSet.START, dpToPx(viewContainer.context, 16)
        )


        constraintSet.applyTo(containerView)

        actionView = imageView

    }
}