package dev.ginger.ui.dialogs.popup

import androidx.fragment.app.DialogFragment
import dev.ginger.ui.dialogs.DialogState

interface OnStateListener {
    fun onChangeState(dialog: DialogFragment, state: DialogState)
}