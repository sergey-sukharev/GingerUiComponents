package dev.ginger.ui.components.dialog

import android.view.View

interface GingerDialog {
    fun show(tag: String = "default_dialog")
    fun dismiss()
}

interface GingerCheckDialog {
    fun setItems()
}

interface GingerRadioDialog {
    fun setRadioItems()
}

enum class GingerDialogState {
    ON_SHOW, ON_DISMISS, ON_MENU_SELECTED
}