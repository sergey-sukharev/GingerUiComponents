package dev.ginger.ui.components.dialog

import android.widget.EditText
import android.widget.TextView

interface GingerEditDialog {
    fun getEditTextView(): EditText
    fun getLabelTextView(): TextView
    fun setTitle(title: String)
}