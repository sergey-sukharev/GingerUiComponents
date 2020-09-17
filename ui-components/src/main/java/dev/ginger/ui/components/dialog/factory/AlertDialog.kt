package dev.ginger.ui.components.dialog.factory

import android.os.Bundle
import android.view.View


class AlertDialog(private val builder: Builder) : BaseDialogButton(builder) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO inflate message text

    }

    class Builder: BaseDialogButton.Builder() {
        var messageText: String? = null

        override fun build(): AlertDialog {
            return AlertDialog(this)
        }
    }
}



