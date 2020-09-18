package dev.ginger.ui.components.dialog.factory

import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

abstract class AbstractDialog: DialogFragment() {

    abstract fun createCustomView(container: ViewGroup)

}