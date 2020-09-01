package dev.ginger.ui.components.dialog

// A toolbar state for custom dialog
data class DialogToolbarState(
    var title: String? = null,
    var subtitle: String? = null,
    var style: Int? = null
)