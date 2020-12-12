package dev.ginger.app.examples

import android.view.View
import dev.ginger.app.R
import dev.ginger.ui.components.text.EditTextViewWrapper

class Example1EditTextWrapper(view: View, state: ViewState) : EditTextViewWrapper(view, state) {

    override fun getEditTextViewId(): Int? = R.id.edit_field_input

    override fun getHelperTextViewId(): Int? = R.id.textinput_helper_text

    override fun getTitleTextViewId(): Int? = R.id.title

    override fun getDividerTextViewId(): Int? = R.id.divider

    override fun getIconViewId(): Int? = R.id.icon

    override fun getMetaIconViewId(): Int? = R.id.meta_icon

    override fun getProgressBarId(): Int? = R.id.progress

}