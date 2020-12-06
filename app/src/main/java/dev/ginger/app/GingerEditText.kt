package dev.ginger.app

import android.view.View
import dev.ginger.ui.components.text.AbstractLineEditTextView

class GingerEditText(id: String, view: View, state: ViewState) :
    AbstractLineEditTextView(id, view, state) {

    override fun getTitleViewResId(): Int? = R.id.textView

    override fun getTextViewResId(): Int? = R.id.textView2

    override fun getDividerViewResId(): Int? = R.id.view

    override fun getMetaIconResId(): Int? = R.id.imageView2

    override fun getIconResId(): Int? = R.id.imageView

    override fun getProgressViewResId(): Int? = R.id.progressBar

}