package dev.ginger.ui.components.dialog.popup

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.toPx

/**
 * An abstract class of GingerDialog
 *  It's includes some components as:
 *  - Title text
 *  - Icon for close dialog
 *  - Positive and negative buttons
 *
 * @property builder
 */
abstract class AbstractDialog(private val builder: AbstractBuilder) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setStyle(STYLE_NORMAL, R.style.GingerDialogStyle)
    }

    override fun onResume() {
        super.onResume()

        dialog?.apply {
            val width = 340.toPx()
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
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
        addHeaderView(view)
        addContainerView(view)
        addFooterView(view)
    }

    abstract fun addHeaderView(container: View)
    abstract fun addFooterView(container: View)
    abstract fun addContainerView(container: View)
    abstract fun onPositiveButtonClick()

    abstract class AbstractBuilder {
        abstract fun build(): Any
    }
}