package dev.ginger.ui.components.dialog.factory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx

open class AlertDialog(private val builder: Builder) : BaseDialog(builder) {

    private var positiveButton: Button? = null
    private var negativeButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonGroup = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_container_footer, null)

        val footer = view.findViewById<FrameLayout>(R.id.ginger_dialog_container_footer)
        footer.addView(buttonGroup)

        positiveButton = footer.findViewById(R.id.ginger_dialog_positive_button)
        positiveButton?.apply {
            builder.positiveButtonText?.let {
                text = it
            } ?: run {
                visibility = View.GONE
            }
        }

        negativeButton = footer.findViewById(R.id.ginger_dialog_negative_button)
        negativeButton?.apply {
            builder.negativeButtonText?.let {
                text = it
            } ?: run {
                visibility = View.GONE
            }

            setOnClickListener { dismiss() }
        }

        createCustomView(view as ViewGroup)
    }

    override fun createCustomView(container: ViewGroup) {
        container.findViewById<FrameLayout>(R.id.ginger_dialog_container_frame)?.apply {
            val textView = TextView(requireContext(), null, R.style.TextAppearance_AppCompat_Body1)
            textView.text = builder.messageText
            textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(textView)
        }
    }

    open class Builder: ButtonBuilder() {
        var messageText: String? = null

        override fun build(): AlertDialog {
            return AlertDialog(this)
        }
    }

    open class ButtonBuilder: BaseDialog.Builder() {
        var negativeButtonText: String? = null
        var positiveButtonText: String? = null
    }

}