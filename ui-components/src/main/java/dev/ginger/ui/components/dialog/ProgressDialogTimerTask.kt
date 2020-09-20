package dev.ginger.ui.components.dialog

import androidx.fragment.app.DialogFragment
import java.util.*

class ProgressDialogTimerTask(): TimerTask() {

    private var x: () -> Unit = {
        println("SDADASD")
    }

    constructor(x: () -> Unit): this() {
        this.x = x
    }

    override fun run() = x()
}