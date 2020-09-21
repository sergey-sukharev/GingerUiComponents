package dev.ginger.ui.dialogs.popup

import androidx.fragment.app.DialogFragment

interface OnStateListener {
    fun onChangeState(dialog: DialogFragment, state: DialogState)
}