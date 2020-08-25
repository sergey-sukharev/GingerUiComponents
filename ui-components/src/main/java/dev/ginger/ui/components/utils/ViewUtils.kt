package dev.ginger.ui.components.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

// Set cursor position to end
fun EditText.setCursorToEnd() {
    setSelection(text.length)
}

fun EditText.showSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}