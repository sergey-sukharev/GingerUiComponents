package dev.ginger.ui.dialogs.popup

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R

abstract class AbstractPopupDialog(private val builder: Builder) : AbstractDialog(builder),
    View.OnClickListener {

    protected var onChangeStateListeners = mutableListOf<OnStateListener>()

    abstract class Builder : AbstractBuilder() {
        var negativeButtonText: String? = null
        var positiveButtonText: String? = null
        var onStateListener: OnStateListener? = null
        var title: String? = null
        var enableCloseIcon: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        builder.onStateListener?.let { subscriber ->
            onChangeStateListeners.add(subscriber)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons(view)
        setTitleView(view)
        setIconView(view)
    }

    override fun addHeaderView(container: View) {
        val headerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_container_title, null)
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_header)?.addView(headerView)
    }

    override fun addFooterView(container: View) {
        val footerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_container_footer, null)
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_footer)?.addView(footerView)
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.title?.let {
                text = it
            } ?: run { visibility = View.INVISIBLE }
        } ?: throw NoSuchElementException()
    }

    protected fun getTitleView(): TextView {
        return view?.findViewById<TextView?>(R.id.ginger_dialog_title_text)
            ?: throw NoSuchElementException()
    }

    private fun setIconView(view: View) {
        view.findViewById<ImageView?>(R.id.ginger_dialog_close_icon)?.apply {
            visibility = if (builder.enableCloseIcon) View.VISIBLE else View.GONE
            setOnClickListener(this@AbstractPopupDialog)
        }
    }

    protected fun getIconView(): ImageView {
        return view?.findViewById<ImageView?>(R.id.ginger_dialog_close_icon)
            ?: throw NoSuchElementException()
    }

    private fun setButtons(view: View) {
        val footer = view.findViewById<FrameLayout>(R.id.ginger_dialog_container_footer)

        footer.findViewById<Button>(R.id.ginger_dialog_positive_button)?.apply {
            setButtonText(builder.positiveButtonText, this)
        }

        footer.findViewById<Button>(R.id.ginger_dialog_negative_button)?.apply {
            setButtonText(builder.negativeButtonText, this)
        }
    }

    private fun setButtonText(text: String?, button: Button) {
        text?.let {
            button.text = text
            button.setOnClickListener(this)
        } ?: run { button.visibility = View.GONE }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        sendStateToSubscribers(this, DialogState.ON_CANCELLABLE)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ginger_dialog_close_icon -> {
                if (onChangeStateListeners.size == 0) dismiss()
                sendStateToSubscribers(this, DialogState.ON_CANCELLABLE)
            }
            R.id.ginger_dialog_negative_button -> {
                if (onChangeStateListeners.size == 0) dismiss()
                sendStateToSubscribers(this, DialogState.ON_NEGATIVE_CLICK)
            }
            R.id.ginger_dialog_positive_button -> {
                sendStateToSubscribers(this, DialogState.ON_POSITIVE_CLICK)
            }
        }
    }

    private fun sendStateToSubscribers(dialog: DialogFragment, state: DialogState) {
        onChangeStateListeners.forEach {
            it.onChangeState(dialog, state)
        }
    }


}