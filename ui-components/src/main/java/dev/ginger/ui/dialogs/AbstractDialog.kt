package dev.ginger.ui.dialogs

import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.view.Surface.ROTATION_270
import android.view.Surface.ROTATION_90
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
abstract class AbstractDialog(private val builder: AbstractBuilder) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setStyle(STYLE_NORMAL, R.style.GingerDialogStyle)
    }

    override fun onResume() {
        super.onResume()

        dialog?.apply {
            val point = Point()
            window?.windowManager?.defaultDisplay?.getSize(point)

            val rotation = window?.windowManager?.defaultDisplay?.rotation
            var width = point.x * 0.75
            if (ROTATION_90 == rotation || ROTATION_270 == rotation)
                width = point.x * 0.5

            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.setLayout(width.toInt(), height)
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

    abstract class AbstractBuilder {
        abstract fun build(): Any
    }
}