package dev.ginger.ui.components.dialog

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.setCursorToEnd
import dev.ginger.ui.components.utils.showSoftKeyboard

class GingerEditDialogFragment(
    private val dialogFragmentManager: FragmentManager,
    private val provider: EditDialogProvider,
    val dialogTag: String?
) : DialogFragment(), GingerEditDialog {

    companion object {
        fun create(
            dialogFragment: FragmentManager,
            editDialogProvider: EditDialogProvider,
            tag: String? = null
        ): GingerDialog {
            return GingerEditDialogFragment(dialogFragment, editDialogProvider, tag)
        }
    }

    private var toolbar: Toolbar? = null

    private var valueEditText: EditText? = null
    private var helperTextView: TextView? = null

    private var state: EditDialogState = EditDialogState("", "", "", "", false)
    private var toolbarState: DialogToolbarState = DialogToolbarState("", "")

    override fun setState(state: EditDialogState) {
        this.state = state
        updateState()
    }

    override fun getState(): EditDialogState = state

    override fun setToolbarState(state: DialogToolbarState) {
        toolbarState = state
        context?.let { updateToolbarState() }
    }

    override fun getToolbarState(): DialogToolbarState = toolbarState

    override fun show() {
        show(dialogFragmentManager, tag)
    }

    private fun updateState() {
        state.apply {
            if (valueEditText?.text.toString() != text)
                valueEditText?.setText(text)
            valueEditText?.inputType = inputType
            valueEditText?.setCursorToEnd()
            valueEditText?.hint = hint
            helperTextView?.text = helperText
        }
    }

    private fun updateToolbarState() {
        toolbarState.apply {
            toolbar?.title = title
            toolbar?.subtitle = subtitle
            style?.let {resId ->
//                toolbar = Toolbar(ContextThemeWrapper(requireContext(), resId))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
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
//        toolbar = view.findViewById(R.id.toolbar)
        val containerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_edit_dialog_template, null)

        view.findViewById<LinearLayout>(R.id.container).apply {
            toolbar = Toolbar(ContextThemeWrapper(requireContext(), toolbarState.style!!))
            addView(toolbar)
            addView(containerView)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_dialog, menu)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valueEditText = view.findViewById(R.id.edit_field_input)
        helperTextView = view.findViewById(R.id.edit_field_label)

//        valueEditText?.addTextChangedListener {
//            it?.let {
//                provider.postChangedValue(state.apply { text = it.toString() })
//            }
//        }

        valueEditText?.setCursorToEnd()
        valueEditText?.showSoftKeyboard()

//        toolbar = view.findViewById(R.id.toolbar)

        toolbar?.setNavigationOnClickListener { v: View? ->
            run {
                if (provider.postDismiss(state)) dismiss()
            }
        }

        toolbar?.inflateMenu(R.menu.menu_dialog)

//        Thread {
//            val r = Toolbar::class.java
//            val field = r.getDeclaredField("mTitleTextView")
//            field.isAccessible = true
//            val view = field.get(toolbar) as? TextView
//            val color = view?.currentTextColor
//            field.isAccessible = false
//            toolbar?.post {
//                toolbar?.menu?.findItem(R.id.action_save)?.icon?.apply {
//                    DrawableCompat.setTint(this, color!!)
//                }
//            }
//            println("TEXTVIEW color $color")
//
//        }.start()
//        val f = r.getDeclaredField("mTitleTextView")
//        f.isAccessible = true
//        val textview = f.get(toolbar) as TextView

        toolbar?.post {
            val r = Toolbar::class.java
            val field = r.getDeclaredField("mTitleTextView")
            field.isAccessible = true
            val view = field.get(toolbar) as? TextView
            val color = view?.currentTextColor
            field.isAccessible = false

            toolbar?.menu?.findItem(R.id.action_save)?.icon?.apply {
                DrawableCompat.setTint(this, color!!)
            }

            toolbar?.navigationIcon = resources.getDrawable(R.drawable.ic_arrow, null).apply {
                DrawableCompat.setTint(this, color!!)
            }
        }



        toolbar?.setOnMenuItemClickListener {
            if (provider.postSave(state)) dismiss()
            true
        }

        updateState()
        updateToolbarState()

        valueEditText?.addTextChangedListener {
            it?.let {
                provider.postChangedValue(state.apply { text = it.toString() })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}