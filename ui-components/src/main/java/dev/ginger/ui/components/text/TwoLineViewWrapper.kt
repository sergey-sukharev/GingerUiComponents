package dev.ginger.ui.components.text

import android.view.View
import android.widget.TextView

/**
 * Наследники: TExtView, CheckBox, Switcher, Spinner
 *
 * @constructor
 * TODO
 *
 * @param view
 * @param state
 */
abstract class TwoLineViewWrapper(view: View, state: ViewState):
    BaseViewWrapper<TwoLineViewWrapper.ViewState>(view, state){

    private var subtitleView: TextView? = null

    override fun initViews() {
        super.initViews()
        getSubtitleViewId()?.let { subtitleView = view.findViewById(it) }
    }

    override fun updateState(state: ViewState) {
        super.updateState(state)
        subtitleView?.apply {
            text = state.subtitleText
        }
    }

    protected abstract fun getSubtitleViewId(): Int?

    open class ViewState: BaseViewWrapper.ViewState() {
        var subtitleText: String? = null
    }
}