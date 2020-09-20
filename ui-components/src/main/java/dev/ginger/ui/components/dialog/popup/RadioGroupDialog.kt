package dev.ginger.ui.components.dialog.popup

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.toPx


class RadioGroupDialog(private val builder: Builder) : AbstractPopupDialog(builder) {


    private val itemsMap = mutableMapOf<Int, String>()

    private var checkedId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.titleText?.let {
                text = it
            } ?: run { visibility = View.GONE }
        } ?: throw NoSuchElementException()
    }

    override fun addContainerView(container: View) {
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_content)?.apply {
            val group = RadioGroup(requireContext())
            group.orientation = RadioGroup.VERTICAL
            group.layoutParams = LinearLayout
                .LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(8.toPx(), 0, 8.toPx(), 0) }

            builder.items.forEach { model ->
                group.addView(createRadioButton(model))
            }

            group.setOnCheckedChangeListener { _, pos ->
                checkedId = itemsMap[pos]
                builder.positiveButtonText ?: run {
                    builder.listener?.onChecked(this@RadioGroupDialog, checkedId!!)
                    dismiss()
                }
            }
            addView(group)
        }
    }

    private fun createRadioButton(model: Item): RadioButton {
        return RadioButton(requireContext()).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout
                .LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            text = model.text
            builder.checkItem?.let {
                if (model.id == builder.checkItem) isChecked = true
            }
            setPadding(16.toPx(), 16.toPx(), 16.toPx(), 16.toPx())
            itemsMap[id] = model.id
        }
    }

    class Builder : AbstractPopupDialog.Builder() {
        val items = mutableListOf<Item>()
        var checkItem: String? = null
        var listener: RadioDialogListener? = null
        override fun build(): RadioGroupDialog = RadioGroupDialog(this)
    }

    override fun onPositiveButtonClick() {
        builder.listener?.onChecked(this@RadioGroupDialog, checkedId!!)
    }

    data class Item(
        val id: String,
        val text: String
    )
}