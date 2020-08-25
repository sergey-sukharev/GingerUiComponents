package dev.ginger.ui.components.dialog

import android.view.View

interface GingerDialog {
    fun getViewByResourceId(resourceId: Int): View?
    fun getState(): GingerDialogState
    fun dismiss()
}

enum class GingerDialogState {
    ON_SHOW, ON_DISMISS, ON_MENU_SELECTED
}