package dev.ginger.ui.components.dialog


interface GingerEditDialog: GingerDialog {
    fun setState(state: EditDialogState)
    fun getState(): EditDialogState
    fun setToolbarState(toolbarState: DialogToolbarState)
    fun getToolbarState(): DialogToolbarState
}