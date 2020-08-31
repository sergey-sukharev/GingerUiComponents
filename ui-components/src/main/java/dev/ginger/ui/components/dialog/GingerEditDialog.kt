package dev.ginger.ui.components.dialog


interface GingerEditDialog: GingerDialog {
    fun setState(state: EditDialogState)
}