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
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    requestFocus()
    imm.showSoftInput(this, 0)
}

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}