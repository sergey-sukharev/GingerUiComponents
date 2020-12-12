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

    protected open fun updateState(state: T) {
        renderTitleTextView(state)
        renderDividerView(state)
        renderIconView(state)
        renderMetaIconView(state)
        checkIsLoadingState(state.isLoading)
    }

    protected fun renderIconView(state: T) {
        iconView?.setIconOrGone(getDrawableByResId(state.iconSrc, state.iconTint))
    }

    protected fun renderMetaIconView(state: T) {
        metaIconView?.setIconOrGone(getDrawableByResId(state.metaIconSrc, state.metaIconTint))
    }

    protected fun renderTitleTextView(state: T) {
        titleTextView?.apply {
            text = state.titleText
            state.titleTextColor?.let { setTextColor(it.getColor(context)) }
        }
    }

    protected fun renderDividerView(state: T) {
        dividerView?.apply {
            setVisibleOrInvisible(state.enableDivider)
            state.dividerTint?.let { setBackgroundColor(it.getColor(context)) }
        }
    }


    private fun checkIsLoadingState(state: Boolean) {
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
    }

}