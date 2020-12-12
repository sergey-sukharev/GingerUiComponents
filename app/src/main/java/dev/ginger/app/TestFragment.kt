package dev.ginger.app

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ginger.app.examples.Example1EditTextWrapper
import dev.ginger.app.examples.Example1TextViewWrapper
import dev.ginger.ui.components.text.EditTextViewWrapper
import kotlinx.android.synthetic.main.fragment_empty.*

open class TestFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    val TAG = "COROUTINES"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewEditText1 = LayoutInflater.from(requireContext()).inflate(R.layout.template_edit_item, null)

//        val wrapper = TwoLineViewWrapper(view, TwoLineViewWrapper.ViewState())
//        wrapper.updateState(TwoLineViewWrapper.ViewState().apply {
//            this.titleText = "Hello"
//            this.subtitle = "Hello"
//        })

        val gEditTextView = Example1EditTextWrapper(createTextView2(), EditTextViewWrapper.ViewState().apply {
            titleText = "Hello"
            inputValue = "My cat"
            enableDivider = false
            hintText = "Hint is true"
            helperText = "It's your helper text"
            titleTextColor = R.color.colorPrimaryDark
            dividerTint = Color.RED
        }).apply {
            removeCursor()
        }

        val nickNameView = Example1TextViewWrapper(createTextView())

        gEditTextView.apply {
            state.helperText = "SO SO"
            state.enableDivider = true
            updateState()
        }

        nickNameView.apply {
            state.subtitleText = "Supreme"
//            state.subtitleTextColor = Color.RED
            updateState()
        }

        view_container.addView(gEditTextView.view)
        view_container.addView(nickNameView.view)
    }

    fun createTextView() = LayoutInflater.from(requireContext()).inflate(R.layout.template_ginger_line_text, null)
    fun createTextView2() = LayoutInflater.from(requireContext()).inflate(R.layout.template_g_edit_text, null)



}