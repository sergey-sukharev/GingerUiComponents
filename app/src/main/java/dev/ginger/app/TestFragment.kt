package dev.ginger.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ginger.app.examples.GEditText
import dev.ginger.ui.components.text.AbstractLineTextView
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
//        val viewEditText3 = LayoutInflater.from(requireContext()).inflate(R.layout.template_edit_item, null)

        val textViewWrapper1 = createTextViewWrapper("first", createTextView(),
            createTextViewState("Name", null, "Hello world"))

        val textViewWrapper2 = createTextViewWrapper("first", createTextView(),
            createTextViewState("Age", "MY", "Input your age").apply {
                isRequired = true
            })

        val textViewWrapper3 = createTextViewWrapper("first", createTextView(),
            createTextViewState(null, "2586 9665 48888", "Hello world"))

//        val wrapper = TwoLineViewWrapper(view, TwoLineViewWrapper.ViewState())
//        wrapper.updateState(TwoLineViewWrapper.ViewState().apply {
//            this.titleText = "Hello"
//            this.subtitle = "Hello"
//        })

        val gEditTextView = GEditText(createTextView2(), EditTextViewWrapper.ViewState().apply {
            titleText = "Hello"
            inputValue = "My cat"
            enableDivider = false
            hintText = "Hint is true"
            helperText = "It's your helper text"
        }).apply {
            removeCursor()
        }

        view_container.addView(textViewWrapper1.view)
        view_container.addView(textViewWrapper2.view)
        view_container.addView(textViewWrapper3.view)
        view_container.addView(gEditTextView.view)
    }

    fun createTextView() = LayoutInflater.from(requireContext()).inflate(R.layout.template_ginger_line_text, null)
    fun createTextView2() = LayoutInflater.from(requireContext()).inflate(R.layout.template_g_edit_text, null)

    fun createTextViewWrapper(id: String, view: View, viewState: AbstractLineTextView.ViewState) =
        GingerLineTextVIew(id, view, viewState)

    fun createTextViewState(title: String?, value: String?, hint: String?) = AbstractLineTextView.ViewState().apply {
        this.title = title
        this.value = value
        this.hint = hint
    }


}