package dev.ginger.app

import android.view.View
import android.widget.TextView
import dev.ginger.ui.components.text.AbstractLineTextView

open class GingerLineTextVIew(id: String, view: View, state: ViewState):
    AbstractLineTextView<TextView, AbstractLineTextView.ViewState>(id, view, state) {

    override fun getTitleViewResId(): Int? = R.id.textView

    override fun getTextViewResId(): Int? = R.id.textView2

    override fun getDividerViewResId(): Int? = R.id.view

    override fun getMetaIconResId(): Int? = R.id.imageView2

    override fun getIconResId(): Int? = R.id.imageView

}