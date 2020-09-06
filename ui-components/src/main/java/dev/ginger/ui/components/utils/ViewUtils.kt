package dev.ginger.ui.components.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// Set cursor position to end
fun EditText.setCursorToEnd() {
    setSelection(text.length)
}

fun EditText.showSoftKeyboard() {
    post {
        if (requestFocus()) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}

fun EditText.hideSoftKeyboard() {
    post {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}

