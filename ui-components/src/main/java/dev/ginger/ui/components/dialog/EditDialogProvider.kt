package dev.ginger.ui.components.dialog

import androidx.fragment.app.DialogFragment
import io.reactivex.Observable

interface EditDialogProvider {
    fun observeOnState(): Observable<EditDialogState>
    fun postSave(value: String): Boolean
    fun postDismiss(value: String): Boolean
    fun observeOnDialog(): Observable<DialogFragment>
    fun observeOnToolbar(): Observable<DialogToolbarState>
}

data class EditDialogState(
    var text: String? = null,
    var hint: String? = null,
    var helperText: String? = null,
    var required: Boolean = false
)