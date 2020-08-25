package dev.ginger.ui.components.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class GingerBaseDialog : DialogFragment(), GingerEditDialog {

    // View's list contains view id and view instance
    private val views = mutableMapOf<Int, View>()

    private val layoutResourceId = R.layout.ginger_edit_dialog_template

    private val publishSubject = PublishSubject.create<View>()

    companion object {
        fun display(fragmentManager: FragmentManager): GingerBaseDialog {
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
        val containerView = LayoutInflater.from(requireContext()).inflate(layoutResourceId, null)
        view.findViewById<LinearLayout>(R.id.container).apply {
            addView(containerView)
        }

        findViews(containerView as ViewGroup)

        toolbar?.title = "Some Title"
        toolbar?.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar?.setOnMenuItemClickListener {
            dismiss()
            true
        }
    }

    private fun findViews(root: ViewGroup) {
        for (viewIndex in 0 until root.size) {
            val view = root.getChildAt(viewIndex)

            if (view.id != -1) publishSubject.onNext(view)
//                views[view.id] = view

            if (view is ViewGroup)
                findViews(view)
        }

        publishSubject.onComplete()
    }

    override fun getEditTextView(): EditText {
        (TODO())
    }

    fun getView(resource: Int): View? =
        views[resource]

    override fun getLabelTextView(): TextView {
        (TODO())
    }

    fun getViews() : Observable<View> {
        return publishSubject
    }

}