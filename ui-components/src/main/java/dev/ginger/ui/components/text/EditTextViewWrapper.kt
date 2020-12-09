package dev.ginger.ui.components.text

import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

abstract class EditTextViewWrapper(view: View, state: ViewState) :
    BaseViewWrapper<EditTextViewWrapper.ViewState>(view, state) {

    private var editTextView: EditText? = null
    private var helperTextView: TextView? = null

    override fun initViews() {
        super.initViews()
        getEditTextViewId()?.let { editTextView = view.findViewById(it) }
        getHelperTextViewId()?.let { helperTextView = view.findViewById(it) }
    }

    override fun updateState(state: ViewState) {
        super.updateState(state)
        editTextView?.apply {
            setText(state.inputValue)
            inputType = state.inputType
        }

        helperTextView?.apply {
            text = state.helperText
        }
    }

    abstract fun getEditTextViewId(): Int?

    abstract fun getHelperTextViewId(): Int?

    abstract fun getProgressBarViewId(): Int?

    class ViewState: BaseViewWrapper.ViewState() {
        var inputValue: String? = null
        var helperText: String? = null
        var inputType: Int = InputType.TYPE_CLASS_TEXT
    }
}