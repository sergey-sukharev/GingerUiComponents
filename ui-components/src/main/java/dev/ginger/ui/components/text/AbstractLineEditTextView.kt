package dev.ginger.ui.components.text

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import dev.ginger.ui.R

abstract class AbstractLineEditTextView(id: String, view: View, state: ViewState):
    AbstractLineTextView<EditText, AbstractLineEditTextView.ViewState>(id, view, state) {

    private var progressBar: ProgressBar? = null

    fun updateState(state: ViewState) {
        super.updateState(state)
        updateProgressState()
        setInputType()
    }

    private fun updateProgressState() {
        if ((state as ViewState).isLoading) {
            progressBar?.visibility = View.VISIBLE
            metaIconView?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.GONE
            metaIconView?.visibility = View.VISIBLE
        }
    }

    override fun initViews() {
        super.initViews()

        progressBar = view.findViewById(R.id.progressBar)
        (valueTextView as EditText).addTextChangedListener {
            state.value = it?.toString()
            checkOnRequired()
        }
    }

    override fun renderTextState(state: AbstractLineTextView.ViewState) {
        (valueTextView as EditText).apply {
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    post {
                        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
                    }
                }
            }
        }

        state.value?.let {
            (valueTextView as EditText).apply {
                setText(it)
            }

        } ?: run {
            (valueTextView as EditText).apply {
                setHint(state.hint)
            }
        }
    }

    private fun setInputType() {
        getEditText()?.inputType = state.inputType
    }

    private fun getEditText(): EditText? = valueTextView

    override fun getIconWarningTint(): Int {
        return super.getIconErrorTint()
    }

    open class ViewState : AbstractLineTextView.ViewState() {
        var helperText: String? = null
        var isLoading = false
        var inputType: Int = InputType.TYPE_CLASS_TEXT
    }

    abstract fun getProgressViewResId(): Int?

}