package dev.ginger.app.examples

import android.view.View
import dev.ginger.app.R
import dev.ginger.ui.components.text.TwoLineViewWrapper

class SimpleTextViewWrapper(view: View) : TwoLineViewWrapper(view) {

    override fun getSubtitleViewId(): Int? = R.id.textView2

    override fun getTitleTextViewId(): Int? = R.id.textView

    override fun getDividerTextViewId(): Int? = R.id.view

    override fun getIconViewId(): Int? = R.id.imageView

    override fun getMetaIconViewId(): Int? = R.id.imageView2

    override fun getProgressBarId(): Int? = null
}