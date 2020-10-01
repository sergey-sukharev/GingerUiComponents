package dev.ginger.ui.dialogs.alert

import android.view.View
import android.widget.*
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.toPx
import dev.ginger.ui.dialogs.popup.AbstractPopupDialog

/**
 * Alert dialog which include base components and message view
 *
 * @property builder
 */
class AlertDialog(private val builder: Builder) : AbstractPopupDialog(builder) {

    override fun addContainerView(container: View) {
        container.findViewById<FrameLayout>(R.id.ginger_dialog_container_content)?.apply {
            val textView = TextView(requireContext())
            textView.text = builder.message
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16.toPx(), 0, 16.toPx(), 16.toPx())
            }
            textView.textSize = 17f
            addView(textView)
        }
    }

    class Builder : AbstractPopupDialog.Builder() {
        var message: String? = null
        override fun build(): AlertDialog {
            return AlertDialog(this)
        }
    }

}