package dev.ginger.ui.components.text

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import dev.ginger.ui.utils.getColor

/**
 * Наследники: TExtView, CheckBox, Switcher, Spinner
 *
 * @constructor
 * TODO
 *
 * @param view
 * @param state
 */
abstract class TwoLineViewWrapper(view: View, state: ViewState = ViewState()):
    BaseViewWrapper<TwoLineViewWrapper.ViewState>(view, state){

    private var subtitleView: TextView? = null

    override fun initViews() {
        super.initViews()
        getSubtitleViewId()?.let { subtitleView = view.findViewById(it) }
    }

    override fun updateState(state: ViewState) {
        super.updateState(state)
        setSubtitleViewState(state)
    }

    protected fun setSubtitleViewState(state: ViewState) {
        subtitleView?.apply {
            text = state.subtitleText
            state.subtitleTextColor?.let { setTextColor(it.getColor(context)) } ?: run {
                state.subtitleTextColor = currentTextColor
            }

            state.subtitleTextSize?.let { setTextSize(TypedValue.COMPLEX_UNIT_PX, it) } ?: run {
                state.subtitleTextSize = textSize
            }
        }
    }

    protected abstract fun getSubtitleViewId(): Int?

    open class ViewState: BaseViewWrapper.ViewState() {
        var subtitleText: String? = null
        var subtitleTextColor: Int? = null
        // Size in PX.
        var subtitleTextSize: Float? = null
    }
}