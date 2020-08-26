package dev.ginger.ui.components.dialog

import android.view.View
import io.reactivex.Observable

interface GingerDialog {
    fun show(): Observable<GingerDialogHandler>
    fun onDismiss(): Observable<DismissCommand>
}

interface GingerDialogHandler {
    fun getViewByResourceId(resourceId: Int): View?
    fun getState(): GingerDialogState
    fun dismiss()
}

interface DismissCommand : GingerDialogHandler{
    fun dismiss(flag: Boolean)
}

enum class GingerDialogState {
    ON_SHOW, ON_DISMISS, ON_MENU_SELECTED
}