package dev.ginger.ui.components.text

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import dev.ginger.ui.R

abstract class AbstractLineTextView<T : TextView, E: AbstractLineTextView.ViewState>(val id: String,
                                                                                     val view: View,
                                               protected var state: E = ViewState() as E) {

    protected var titleTextView: TextView? = null

    protected var valueTextView: T? = null

    protected var dividerView: View? = null

    protected var iconView: ImageView? = null

    protected var metaIconView: ImageView? = null

    protected var onClickListener: OnViewClickListener<ViewState>? = null

    init {
        initViews()
        updateState(state)
    }

    protected open fun initViews() {
        valueTextView = getTextViewResId()?.let { view.findViewById<T>(it) }
        titleTextView = getTitleViewResId()?.let { view.findViewById(it) }
        iconView = getIconResId()?.let { view.findViewById(it) }
        metaIconView = getMetaIconResId()?.let { view.findViewById(it) }
        dividerView = getDividerViewResId()?.let { view.findViewById(it) }
    }

    protected abstract fun getTitleViewResId(): Int?

    abstract fun getTextViewResId(): Int?

    abstract fun getDividerViewResId(): Int?

    abstract fun getMetaIconResId(): Int?

    abstract fun getIconResId(): Int?

    protected fun getHintTextTint(): Int? = R.color.ripple_click_efect

    fun updateState(state: ViewState) {
        this.state = state as E
        renderDividerState(state.enableDivider)
        renderTitleState(state)
        renderTextState(state)
        renderIconState(iconView, getDrawableByResId(state.icon), state.iconTint)
        renderIconState(metaIconView, getDrawableByResId(state.metaIcon), state.metaIconTint)
        checkOnRequired()
    }

    private fun getDrawableByResId(resId: Int?): Drawable? {
        return resId?.let {
            view.context.getDrawable(it)
        } ?: run {
            null
        }
    }

    protected fun checkOnRequired() {
        if (state.isRequired && state.value.isNullOrEmpty()) {
            doOnRequiredTrue()
        } else doOnRequiredFalse()
    }

    protected open fun doOnRequiredTrue() {
        renderIconState(metaIconView, getDrawableByResId(getIconWarningResId()), getIconWarningTint())
    }

    protected open fun doOnRequiredFalse() {
        renderIconState(metaIconView, getDrawableByResId(state.metaIcon), state.metaIconTint)
    }

    protected open fun renderDividerState(isEnabled: Boolean) {
        if (isEnabled) dividerView?.visibility = View.VISIBLE
        else dividerView?.visibility = View.INVISIBLE
    }

    protected open fun renderTitleState(state: ViewState) {
        state.title?.let {text ->
            titleTextView?.apply {
                setText(text)
                visibility = View.VISIBLE
            }
        } ?: run { titleTextView?.visibility = View.GONE }
    }

    protected open fun renderTextState(state: ViewState) {
        (valueTextView as TextView).setOnClickListener {
            onClickListener?.onClick(id, state)
        }

        state.value?.let {
            (valueTextView as TextView).apply {
                text = it
//                setTextColor(context.getColor(R.color.colorPrimary))
            }

        } ?: run {
            (valueTextView as TextView).apply {
                text = state.hint
                getHintTextTint()?.let { setTextColor(context.getColor(it)) }

            }
        }

    }

    private fun renderIconState(imageView: ImageView?, iconDrawable: Drawable?, iconTint: Int?) {
        iconDrawable?.let { drawable ->
            imageView?.visibility = View.VISIBLE
            imageView?.setImageDrawable(setDrawableTint(drawable, iconTint))
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

    private fun getContext() = view.context

    protected open fun getIconWarningResId(): Int = R.drawable.ic_warning

    protected open fun getIconErrorResId(): Int = R.drawable.ic_warning

    protected open fun getIconErrorTint(): Int = R.color.ginger_icon_error

    protected open fun getIconWarningTint(): Int = R.color.ginger_icon_warning

    fun setOnViewClickListener(listener: OnViewClickListener<ViewState>?) {
        this.onClickListener = listener
    }

    open class ViewState {
        var title: String? = null
        var value: String? = null
        var hint: String? = null
        var enableDivider: Boolean = true
        var icon: Int? = null
        var iconTint: Int? = null
        var metaIcon: Int? = null
        var metaIconTint: Int? = null
        var isRequired: Boolean = false
    }
}