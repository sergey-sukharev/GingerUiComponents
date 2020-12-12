package dev.ginger.app.examples

import android.view.View
import dev.ginger.app.R
import dev.ginger.ui.components.text.TwoLineViewWrapper

class Example1TextViewWrapper(view: View) : TwoLineViewWrapper(view) {

    override fun getSubtitleViewId(): Int? = R.id.subtitle

    override fun getTitleTextViewId(): Int? = R.id.title

    override fun getDividerTextViewId(): Int? = R.id.divider

    override fun getIconViewId(): Int? = R.id.icon

    override fun getMetaIconViewId(): Int? = R.id.icon_meta

    override fun getProgressBarId(): Int? = null
}