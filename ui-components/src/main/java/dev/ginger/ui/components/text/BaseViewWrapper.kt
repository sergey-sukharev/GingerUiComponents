package dev.ginger.ui.components.text

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import dev.ginger.ui.utils.getColor
import dev.ginger.ui.utils.setIconOrGone
import dev.ginger.ui.utils.setVisibleOrGone
import dev.ginger.ui.utils.setVisibleOrInvisible

abstract class BaseViewWrapper<T : BaseViewWrapper.ViewState>(val view: View, val state: T) {

    protected var titleTextView: TextView? = null
    protected var dividerView: View? = null
    protected var iconView: ImageView? = null
    protected var metaIconView: ImageView? = null
    protected var progressBarView: ProgressBar? = null

    init {
        initViews()
        updateState(state)
    }

    protected open fun initViews() {
        getTitleTextViewId()?.let { titleTextView = view.findViewById(it) }
        getDividerTextViewId()?.let { dividerView = view.findViewById(it) }
        getIconViewId()?.let { iconView = view.findViewById(it) }
        getMetaIconViewId()?.let { metaIconView = view.findViewById(it) }
        getProgressBarId()?.let { progressBarView = view.findViewById(it) }
    }

    /**
     * Updating a new external state
     * Use this method when need to update completely different state object
     * Recommend to use it how to less
     * @param state - a new state
     */
    open fun updateState(state: T) {
        setRootViewState(state)
        setTitleTextViewState(state)
        setDividerViewState(state)
        setIconViewState(state)
        setMetaIconViewState(state)
        setIsLoadingState(state.isLoading)
    }

    /**
     * Updating a old internal state
     * Use this method when we're update state which was to setting earlier
     * Recommend to use this method when we're updating some fields, but not full state
     */
    fun notifyStateChanged() {
        updateState(state)
    }

    protected open fun setRootViewState(state: T) {
        state.backgroundTint?.let { view.setBackgroundColor(it.getColor(getContext())) } ?: run {
            state.backgroundTint = view.backgroundTintList?.defaultColor
        }
    }

    protected open fun setIconViewState(state: T) {
        iconView?.setIconOrGone(getDrawableByResId(state.iconSrc, state.iconTint))
    }

    protected open fun setMetaIconViewState(state: T) {
        metaIconView?.setIconOrGone(getDrawableByResId(state.metaIconSrc, state.metaIconTint))
    }

    protected open fun setTitleTextViewState(state: T) {
        titleTextView?.apply {
            if (setVisibleOrGone(state.titleText != null)) {
                text = state.titleText
                state.titleTextColor?.let { setTextColor(it.getColor(context)) }
            }
        }
    }

    protected open fun setDividerViewState(state: T) {
        dividerView?.apply {
            setVisibleOrInvisible(state.enableDivider)
            state.dividerTint?.let { setBackgroundColor(it.getColor(context)) }
        }
    }

    private fun setIsLoadingState(state: Boolean) {
        progressBarView?.apply { setVisibleOrGone(state) }
        setMetaIconVisibility()
    }

    private fun setMetaIconVisibility() {
        if (state.metaIconSrc == null) metaIconView?.visibility = View.GONE
        else metaIconView?.visibility = View.VISIBLE
    }

    protected abstract fun getTitleTextViewId(): Int?

    protected abstract fun getDividerTextViewId(): Int?

    protected abstract fun getIconViewId(): Int?

    protected abstract fun getMetaIconViewId(): Int?

    protected abstract fun getProgressBarId(): Int?

    private fun setDrawableTint(drawable: Drawable, tint: Int?): Drawable {
        return with(tint) {
            if (this != null)
                DrawableCompat.setTint(drawable, getContext().getColor(this))
            drawable
        }
    }

    private fun getDrawableByResId(resId: Int?, tintId: Int?): Drawable? {
        return resId?.let {
            getContext().getDrawable(it)?.also {
                setDrawableTint(it, tintId)
            }
        } ?: run {
            null
        }
    }

    protected fun getContext() = view.context

    open class ViewState {
        var titleText: String? = null
        var titleTextColor: Int? = null
        var enableDivider: Boolean = true
        var dividerTint: Int? = null
        var iconSrc: Int? = null
        var iconTint: Int? = null
        var metaIconSrc: Int? = null
        var metaIconTint: Int? = null
        var isLoading: Boolean = false
        var backgroundTint: Int? = null
    }

}