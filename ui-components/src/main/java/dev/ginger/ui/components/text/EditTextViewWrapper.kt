package dev.ginger.ui.components.text

import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import dev.ginger.ui.components.utils.hideSoftKeyboard

abstract class EditTextViewWrapper(view: View, state: ViewState) :
    BaseViewWrapper<EditTextViewWrapper.ViewState>(view, state), View.OnFocusChangeListener {

    private var editTextView: FocusableEditText? = null
    private var helperTextView: TextView? = null

    private var onFocusChangeListener: View.OnFocusChangeListener? = null

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
            hint = state.hintText
            onFocusChangeListener = this@EditTextViewWrapper
        }

        helperTextView?.apply {
            text = state.helperText
        }
    }

    abstract fun getEditTextViewId(): Int?

    abstract fun getHelperTextViewId(): Int?

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) (v as EditText).hideSoftKeyboard()
        onFocusChangeListener?.onFocusChange(v, hasFocus)
    }

    fun setOnFocusChangeListener(listener: View.OnFocusChangeListener?) {
        onFocusChangeListener = listener
    }

    fun removeOnFocusChangeListener(listener: View.OnFocusChangeListener?) {
        if (onFocusChangeListener == listener) onFocusChangeListener = null
    }

    fun addTextChangeListener(watcher: TextWatcher?) {
        editTextView?.addTextChangedListener(watcher)
    }

    fun removeTextChangeListener(watcher: TextWatcher?) {
        editTextView?.removeTextChangedListener(watcher)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun removeCursor() {
        editTextView?.removeCursor()
    }

    class ViewState: BaseViewWrapper.ViewState() {
        var inputValue: String? = null
        var helperText: String? = null
        var hintText: String? = null
        var inputType: Int = InputType.TYPE_CLASS_TEXT
    }
}