package dev.ginger.ui.dialogs

import androidx.fragment.app.DialogFragment

interface OnDialogItemClickListener {
    fun onClicked(dialog: DialogFragment, id: String)
}