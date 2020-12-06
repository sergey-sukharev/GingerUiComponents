package dev.ginger.ui.components.text

interface OnViewClickListener<T : AbstractLineTextView.ViewState> {
    fun onClick(viewId: String, state: T)
}