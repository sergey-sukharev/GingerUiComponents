package dev.ginger.ui.components.dialog.popup

import android.view.View
import android.widget.*
import dev.ginger.ui.R

/**
 * Alert dialog which include base components and message view
 *
 * @property builder
 */
class AlertDialog(private val builder: Builder) : AbstractDialog(builder) {

    override fun createCustomView(container: View) {
        container.findViewById<FrameLayout>(R.id.ginger_dialog_container_frame)?.apply {
            val textView = TextView(requireContext(), null, R.style.TextAppearance_AppCompat_Body1)
            textView.text = builder.messageText
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            addView(textView)
        }
    }

    class Builder : AbstractBuilder() {
        var messageText: String? = null
        override fun build(): AlertDialog {
            return AlertDialog(this)
        }
    }

}