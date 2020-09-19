package dev.ginger.ui.components.dialog.popup

import android.view.View
import android.widget.*
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx
import dev.ginger.ui.components.utils.toDp
import dev.ginger.ui.components.utils.toPx

/**
 * Alert dialog which include base components and message view
 *
 * @property builder
 */
class AlertDialog(private val builder: Builder) : AbstractPopupDialog(builder) {

    override fun addContainerView(container: View) {
        container.findViewById<FrameLayout>(R.id.ginger_dialog_container_frame)?.apply {
            val textView = TextView(requireContext(), null, R.style.TextAppearance_AppCompat_Body1)
            textView.text = builder.messageText
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16.toPx(), 0, 0, 16.toPx())
            }
            addView(textView)
        }
    }

    class Builder : AbstractPopupDialog.Builder() {
        var messageText: String? = null
        override fun build(): AlertDialog {
            return AlertDialog(this)
        }
    }

}