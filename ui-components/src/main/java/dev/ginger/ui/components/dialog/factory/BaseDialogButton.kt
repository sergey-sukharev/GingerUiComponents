package dev.ginger.ui.components.dialog.factory

import android.os.Bundle
import android.view.View

open class BaseDialogButton(private val builder: Builder) : BaseDialog(builder) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        builder.negativeButtonText
    }


    open class Builder: BaseDialog.Builder() {
        var negativeButtonText: String? = null
        var positiveButtonText: String? = null

        override fun build(): BaseDialogButton {
            return BaseDialogButton(this)
        }
    }

}