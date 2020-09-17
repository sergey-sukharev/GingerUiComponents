package dev.ginger.ui.components.dialog.factory

import android.app.Dialog
import android.os.Bundle
import android.os.Message
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R
import dev.ginger.ui.components.dialog.GingerRadioDialog

class GingerRadioDialogFragment: DialogFragment(), GingerRadioDialog {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        dialog?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            window?.setLayout(width, height)
        }


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Retain a dialog when change configuration state
        retainInstance = true
        val view = inflater.inflate(R.layout.ginger_dialog_radio, container, false)

//        val containerView = LayoutInflater.from(requireContext())
//                .inflate(R.layout.ginger_edit_dialog_template, null)

//        view.findViewById<LinearLayout>(R.id.container).apply {
//            toolbar = Toolbar(ContextThemeWrapper(requireContext(), toolbarState.style ?:
//            R.style.GingerToolbarTheme))
//            addView(toolbar)
//            addView(containerView)
//        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

    }

    override fun setRadioItems() {
        TODO("Not yet implemented")
    }
}