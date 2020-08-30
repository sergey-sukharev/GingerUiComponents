package dev.ginger.ui.components.dialog

import android.text.InputType
import androidx.fragment.app.DialogFragment
import io.reactivex.Observable

interface EditDialogProvider {
    fun observeOnState(): Observable<EditDialogState>
    fun postChangedValue(state: EditDialogState)
    fun postSave(state: EditDialogState): Boolean
    fun postDismiss(state: EditDialogState): Boolean
    fun observeOnDialog(): Observable<DialogFragment>
    fun observeOnToolbar(): Observable<DialogToolbarState>
}

data class EditDialogState(
    val id: String,
    var text: String = "",
    var hint: String? = null,
    var helperText: String? = null,
    var required: Boolean = false,
    var inputType: Int = InputType.TYPE_CLASS_TEXT
) {
    fun copyObject(): EditDialogState {
        return EditDialogState(id, text, hint, helperText, required, inputType)
    }
}