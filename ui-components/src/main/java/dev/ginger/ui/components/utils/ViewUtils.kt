package dev.ginger.ui.components.utils

import android.widget.EditText

// Set cursor position to end
fun EditText.setCursorToEnd() {
    setSelection(text.length)
}