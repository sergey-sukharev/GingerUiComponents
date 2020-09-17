package dev.ginger.ui.components.dialog.factory

import androidx.fragment.app.FragmentManager

class GingerDialogA {

    open class Builder(
        private val fragmentManager: FragmentManager,
        private val tag: String? = null
    ) {

        protected var titleText: String? = null
        protected var enableCloseIcon: Boolean = false

        fun setTitle(value: String?): Builder {
            titleText = value
            return this
        }

        fun enableCloseIcon(value: Boolean): Builder {
            enableCloseIcon = value
            return this
        }

        fun build(): BaseDialog {
            val params = DialogParams(titleText, enableCloseIcon)
            return BaseDialog(params)
        }

        data class DialogParams(
            val titleText: String?,
            val enableCloseIcon: Boolean,
            val messageText: String? = null
        )
    }


}