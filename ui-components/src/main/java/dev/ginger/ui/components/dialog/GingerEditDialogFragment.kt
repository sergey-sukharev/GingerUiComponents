package dev.ginger.ui.components.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.addToCompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class GingerEditDialogFragment(
    private val dialogFragment: FragmentManager,
    private val editDialogProvider: EditDialogProvider
) : DialogFragment(),
    GingerEditDialog {

    private val compositeDisposable = CompositeDisposable()

    companion object {
        fun display(
            dialogFragment: FragmentManager,
            editDialogProvider: EditDialogProvider
        ): GingerEditDialogFragment {
            val dialog = GingerEditDialogFragment(dialogFragment, editDialogProvider)
            return dialog
        }
    }

    private var toolbar: Toolbar? = null

    private var valueEditText: EditText? = null
    private var helperText: TextView? = null

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

        // Retain our dialog when change configuration state
        retainInstance = true
        val view = inflater.inflate(R.layout.ginger_base_dialog_, container, false)
        toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val containerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_edit_dialog_template, null)
        view.findViewById<LinearLayout>(R.id.container).apply {
            addView(containerView)
        }

        valueEditText = view.findViewById(R.id.edit_field_input)
        helperText = view.findViewById(R.id.edit_field_label)

        toolbar = view.findViewById(R.id.toolbar)

        toolbar?.setNavigationOnClickListener { v: View? ->
            run {
                if (editDialogProvider.postDismiss(valueEditText?.text.toString())) dismiss()
            }
        }
        toolbar?.setOnMenuItemClickListener {
            if (editDialogProvider.postSave(valueEditText?.text.toString())) dismiss()
            true
        }


        editDialogProvider.observeOnDialog().observeOn(AndroidSchedulers.mainThread()).subscribe {
            it?.show(childFragmentManager, null)
        }.addToCompositeDisposable(compositeDisposable)

        editDialogProvider.observeOnToolbar().observeOn(AndroidSchedulers.mainThread()).subscribe {
            toolbar?.apply {
                title = it.title
                subtitle = it.subtitle
            }
        }.addToCompositeDisposable(compositeDisposable)

        editDialogProvider.observeOnState().observeOn(AndroidSchedulers.mainThread()).subscribe {
            valueEditText?.setText(it.text)
            valueEditText?.hint = it.hint
            helperText?.text = it.helperText
        }.addToCompositeDisposable(compositeDisposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}