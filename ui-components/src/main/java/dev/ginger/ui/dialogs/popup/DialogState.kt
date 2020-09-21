package dev.ginger.ui.dialogs.popup

/**
 * State of dialog change
 *
 * ON_CANCELLABLE - execute a cancel method (click outside or close icon e.g.)
 * ON_NEGATIVE_CLICK - click on negative button
 * ON_POSITIVE_CLICK - click on positive button
 */
enum class DialogState {
    ON_CANCELLABLE, ON_NEGATIVE_CLICK, ON_POSITIVE_CLICK
}