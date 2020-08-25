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
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.setCursorToEnd
import dev.ginger.ui.components.utils.showSoftKeyboard
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class GingerEditDialogFragment: DialogFragment(), GingerEditDialog {
    // View's list contains view id and view instance
    private val views = mutableMapOf<Int, View>()

    private val layoutResourceId = R.layout.ginger_edit_dialog_template

    private val publishSubject = PublishSubject.create<GingerEditDialog>()

    private val onSaveSubject = PublishSubject.create<String>()
    private val onDismissSubject = PublishSubject.create<String>()
    private val onTextChangeSubject = PublishSubject.create<String>()

    private var xFragmentManager: FragmentManager? = null

    companion object {
        fun display(fragmentManager: FragmentManager): GingerEditDialogFragment {
            val dialog = GingerEditDialogFragment()
            dialog.xFragmentManager = fragmentManager
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

    fun onDismissAction(): Observable<String> {
        return onDismissSubject
    }

    fun onSaveAction(): Observable<String> {
        return onSaveSubject
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.ginger_base_dialog_, container, false)
        toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    fun onTextChange(): Observable<String> = onTextChangeSubject

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val containerView = LayoutInflater.from(requireContext()).inflate(layoutResourceId, null)
        view.findViewById<LinearLayout>(R.id.container).apply {
            addView(containerView)
        }

        findViews(containerView as ViewGroup)

        toolbar?.title = "Some Title"
        toolbar?.setNavigationOnClickListener { v: View? ->
            run {
                onDismissSubject.onNext((views[R.id.edit_field_input] as? EditText)?.text.toString())
                dismiss()
            }
        }
        toolbar?.setOnMenuItemClickListener {
            onSaveSubject.onNext((views[R.id.edit_field_input] as? EditText)?.text.toString())
            dismiss()
            true
        }

        getEditTextView().apply {
            setCursorToEnd()
            showSoftKeyboard()
            doOnTextChanged { text, start, before, count ->
                onTextChangeSubject.onNext(text.toString())
            }
        }

        publishSubject.onNext(this)

    }

    override fun setTitle(title: String) {
        toolbar?.title = title
    }

    fun show() : Observable<GingerEditDialog> {
        show(xFragmentManager!!, null)
        return publishSubject
    }

    private fun findViews(root: ViewGroup) {
        for (viewIndex in 0 until root.size) {
            val view = root.getChildAt(viewIndex)

            if (view.id != -1)
                views[view.id] = view

            if (view is ViewGroup)
                findViews(view)
        }

    }


    override fun dismiss() {
        super.dismiss()
    }

    override fun getEditTextView(): EditText {
        return views[R.id.edit_field_input] as EditText
    }

    override fun getLabelTextView(): TextView {
        return views[R.id.edit_field_label] as TextView
    }
}