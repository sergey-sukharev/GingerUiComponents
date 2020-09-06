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
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

open class GingerBaseDialog(protected val dialogFragment: FragmentManager,
                            protected val resourceId: Int) : DialogFragment(), GingerDialog, GingerDialogHandler, DismissCommand {

    // View's list contains view id and view instance
    private val views = mutableMapOf<Int, View>()

    private val layoutResourceId = R.layout.ginger_base_dialog_

    private val publishSubject = PublishSubject.create<View>()

    private val dialogSubject = ReplaySubject.create<GingerDialogHandler>()

    private val dismissSubject = PublishSubject.create<DismissCommand>()

    private var dialogState = GingerDialogState.ON_SHOW

    companion object {
        fun display(fragmentManager: FragmentManager,
                    resourceId: Int = R.layout.ginger_base_dialog_): GingerBaseDialog {
            val dialog = GingerBaseDialog(fragmentManager, resourceId)

            dialog.dialogSubject.onNext(dialog)
//            dialog.show(fragmentManager, null)
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

        val view = inflater.inflate(resourceId, container, false)
//        toolbar = view.findViewById(R.id.toolbar)

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
        toolbar?.setNavigationOnClickListener { v: View? ->
            run {
                dialogState = GingerDialogState.ON_DISMISS
                dialogSubject.onNext(this)
            }
        }
        toolbar?.setOnMenuItemClickListener {
            dialogState = GingerDialogState.ON_MENU_SELECTED
            dialogSubject.onNext(this)
            true
        }

        dialogSubject.onNext(this)
    }

    private fun findViews(root: ViewGroup) {
        for (viewIndex in 0 until root.size) {
            val view = root.getChildAt(viewIndex)

            if (view.id != -1)
                views[view.id] = view

            if (view is ViewGroup)
                findViews(view)
        }

        publishSubject.onComplete()
    }


    override fun getViewByResourceId(resourceId: Int): View? = views[resourceId]

    override fun getState(): GingerDialogState {
        TODO("Not yet implemented")
    }

    override fun dismiss(flag: Boolean) {
        if (flag) super.dismiss()
    }

    override fun show(tag: String) {
        TODO("Not yet implemented")
    }

    override fun dismiss() {
        dismissSubject.onNext(this)
    }

//    override fun getViewByResourceId(resourceId: Int): View? = views[resourceId]
//
//    override fun getState(): GingerDialogState = dialogState


}