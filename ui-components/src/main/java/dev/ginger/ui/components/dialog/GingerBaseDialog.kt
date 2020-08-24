package dev.ginger.ui.components.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R

class GingerBaseDialog : DialogFragment() {

    companion object {
        fun display(fragmentManager: FragmentManager): DialogFragment {
            val dialog = GingerBaseDialog()
            dialog.show(fragmentManager, null)
            return dialog
        }

    }

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
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

        val view = inflater.inflate(R.layout.ginger_base_dialog_, container, false)
        toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar?.setTitle("Some Title")
        toolbar?.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar?.setOnMenuItemClickListener {
            dismiss()
            true
        }
    }

}