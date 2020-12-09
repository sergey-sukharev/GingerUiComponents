package dev.ginger.app.examples

import android.view.View
import dev.ginger.app.R
import dev.ginger.ui.components.text.EditTextViewWrapper

class GEditText(view: View, state: ViewState) : EditTextViewWrapper(view, state) {


    override fun getEditTextViewId(): Int? = R.id.edit_field_input

    override fun getHelperTextViewId(): Int? = R.id.textinput_helper_text

    override fun getTitleTextViewId(): Int? = R.id.title

    override fun getDividerTextViewId(): Int? = R.id.view2

    override fun getIconViewId(): Int? = R.id.imageView3

    override fun getMetaIconViewId(): Int? = R.id.imageView4

    override fun getProgressBarId(): Int? = R.id.progressBar2

}