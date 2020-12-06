package dev.ginger.app

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ginger.ui.components.text.AbstractLineEditTextView
import dev.ginger.ui.components.text.AbstractLineTextView
import dev.ginger.ui.components.text.OnViewClickListener
import kotlinx.android.synthetic.main.fragment_empty.*
import java.lang.Thread.sleep

open class TestFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewText = LayoutInflater.from(requireContext()).inflate(R.layout.template_ginger_line_text, null)
        val viewEditText = LayoutInflater.from(requireContext()).inflate(R.layout.template_edit_item, null)

        val state = AbstractLineTextView.ViewState().apply {
            this.enableDivider = true
            title = "My title"
            value = "Surprise"
            hint = "Введите ваш пароль"
            icon = R.drawable.ic_check_24
            iconTint = R.color.colorAccent
            this.isRequired = true
        }

        val editState = AbstractLineEditTextView.ViewState().apply {
            this.enableDivider = true
            title = "My title"
            hint = "Введите ваш пароль"
            this.isRequired = true
        }

        val cusView = GingerLineTextVIew("A", viewText, state)
        val editView = GingerEditText("B", viewEditText, editState)

        cusView.setOnViewClickListener(object :
            OnViewClickListener<AbstractLineTextView.ViewState> {
            override fun onClick(viewId: String, state: AbstractLineTextView.ViewState) {
                println()
            }
        })

        view_container.addView(viewText)
        view_container.addView(viewEditText)

        Thread {
            requireActivity().runOnUiThread {
                editState.title = null
                editState.value = "Opasno"
                editState.isLoading = true
                editView.updateState(editState)
            }
            sleep(2000)
            requireActivity().runOnUiThread {
                state.title = "Title"
                state.value = null
                cusView.updateState(state)
            }

            sleep(5000)
            requireActivity().runOnUiThread {
                state.title = null
                state.value = "Opasno"
                cusView.updateState(state)
            }

            requireActivity().runOnUiThread {
                editState.title = null
                editState.value = "Opasno"
                editState.isLoading = false
                editState.inputType = InputType.TYPE_CLASS_NUMBER
                editView.updateState(editState)
            }
        }.start()

    }

}