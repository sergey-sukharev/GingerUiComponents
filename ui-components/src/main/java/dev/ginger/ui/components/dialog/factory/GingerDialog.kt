package dev.ginger.ui.components.dialog.factory

import androidx.fragment.app.FragmentManager
import dev.ginger.ui.components.dialog.GingerCheckDialog
import dev.ginger.ui.components.dialog.GingerDialog
import dev.ginger.ui.components.dialog.GingerRadioDialog

class GingerDialog(private val fragmentManager: FragmentManager, private val tag: String? = null)
    :  GingerCheckDialog, GingerRadioDialog {




    fun <T> get(dialog: T): GingerDialog {
        TODO("Not yet implemented")
    }

    override fun setItems() {
        TODO("Not yet implemented")
    }

    override fun setRadioItems() {
        TODO("Not yet implemented")
    }

    fun <T> show(tag: String): T {
        TODO("Not yet implemented")
    }

    fun dismiss() {
        TODO("Not yet implemented")
    }

}