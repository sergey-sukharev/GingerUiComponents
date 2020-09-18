package dev.ginger.ui.components.dialog.factory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R

open class BaseDialog(private var builder: Builder): AbstractDialog(), View.OnClickListener {

    private var titleTextView: TextView? = null
    private var closeIcon: ImageView? = null

    override fun createCustomView(container: ViewGroup) {

    }

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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.ginger_dialog_close_icon -> { dismiss() }
            R.id.ginger_dialog_negative_button -> { dismiss() }
        }
    }


    abstract class AbstractBuilder {
        abstract fun build(): Any
    }

    open class Builder: AbstractBuilder() {
        var titleText: String? = null
        var enableCloseIcon: Boolean = false

        override fun build(): BaseDialog {
           return BaseDialog(this)
        }
    }

}