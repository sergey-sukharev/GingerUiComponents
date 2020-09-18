package dev.ginger.ui.components.dialog.popup

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R

/**
 * An abstract class of GingerDialog
 *  It's includes some components as:
 *  - Title text
 *  - Icon for close dialog
 *  - Positive and negative buttons
 *
 * @property builder
 */
abstract class AbstractDialog(private val builder: AbstractBuilder): DialogFragment(),
    View.OnClickListener {

    private var titleTextView: TextView? = null
    private var closeIcon: ImageView? = null

    private var positiveButton: Button? = null
    private var negativeButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.GingerDialogStyle)
        dialog?.apply {
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ginger_dialog_container_layout, container, false)
    }

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

            setOnClickListener(this@AbstractDialog)
        }

        negativeButton = footer.findViewById(R.id.ginger_dialog_negative_button)
        negativeButton?.apply {
            builder.negativeButtonText?.let {
                text = it
            } ?: run {
                visibility = View.GONE
            }

            setOnClickListener(this@AbstractDialog)
        }

        closeIcon = view.findViewById(R.id.ginger_dialog_close_icon)
        titleTextView = view.findViewById(R.id.ginger_dialog_title_text)

        titleTextView?.text = builder.titleText
        closeIcon?.visibility = builder.enableCloseIcon.let {
            if (it) View.VISIBLE
            else View.GONE
        }

        closeIcon?.setOnClickListener(this)

        createCustomView(view as ViewGroup)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        builder.onStateListener?.onChangeState(DialogButtonState.ON_CANCELLABLE)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ginger_dialog_close_icon -> {
                builder.onStateListener?.onChangeState(DialogButtonState.ON_CANCELLABLE)
                dismiss()
            }
            R.id.ginger_dialog_negative_button -> {
                builder.onStateListener?.onChangeState(DialogButtonState.ON_NEGATIVE_CLICK)
                dismiss()
            }

            R.id.ginger_dialog_positive_button -> {
                builder.onStateListener?.onChangeState(DialogButtonState.ON_POSITIVE_CLICK)
                dismiss()
            }
        }
    }

    abstract fun createCustomView(container: ViewGroup)

    abstract class AbstractBuilder {
        var negativeButtonText: String? = null
        var positiveButtonText: String? = null
        var onStateListener: DialogStateListener? = null
        var titleText: String? = null
        var enableCloseIcon: Boolean = false

        abstract fun build(): Any
    }
}