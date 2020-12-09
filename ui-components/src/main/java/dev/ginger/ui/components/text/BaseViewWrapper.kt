package dev.ginger.ui.components.text

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat

abstract class BaseViewWrapper<T: BaseViewWrapper.ViewState>(val view: View, val state: T) {

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
        titleTextView?.setText(state.titleText)
        dividerView?.apply {
            if (state.enableDivider) visibility = View.VISIBLE
            else visibility = View.INVISIBLE
        }

        iconView?.apply {
            renderIconState(this, getDrawableByResId(state.iconSrc, state.iconTint))
        }

        metaIconView?.apply {
            renderIconState(this, getDrawableByResId(state.metaIconSrc, state.metaIconTint))
        }

        checkIsLoadingState(state.isLoading)
    }

    private fun checkIsLoadingState(state: Boolean) {
        if (state) {
            progressBarView?.visibility = View.VISIBLE
            metaIconView?.visibility = View.INVISIBLE
        } else {
            progressBarView?.visibility = View.INVISIBLE
            metaIconView?.visibility = View.VISIBLE
        }
    }

    protected abstract fun getTitleTextViewId(): Int?

    protected abstract fun getDividerTextViewId(): Int?

    protected abstract fun getIconViewId(): Int?

    protected abstract fun getMetaIconViewId(): Int?

    protected abstract fun getProgressBarId(): Int?

    private fun renderIconState(imageView: ImageView?, iconDrawable: Drawable?) {
        iconDrawable?.let { drawable ->
            imageView?.visibility = View.VISIBLE
            imageView?.setImageDrawable(drawable)
        } ?: run {
            imageView?.visibility = View.GONE
        }
    }

    private fun setDrawableTint(drawable: Drawable, tint: Int?): Drawable {
        tint?.let {
            DrawableCompat.setTint(drawable, getContext().getColor(it))
        }

        return drawable
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

    private fun getContext() = view.context

    open class ViewState {
        var titleText: String? = null
        var enableDivider: Boolean = true
        var iconSrc: Int? = null
        var iconTint: Int? = null
        var metaIconSrc: Int? = null
        var metaIconTint: Int? = null
        var isLoading: Boolean = false
    }

}