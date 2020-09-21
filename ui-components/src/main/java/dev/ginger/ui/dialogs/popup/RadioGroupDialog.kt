package dev.ginger.ui.dialogs.popup

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.toPx


class RadioGroupDialog(private val builder: Builder) : AbstractPopupDialog(builder), OnStateListener {

    private val itemsMap = mutableMapOf<Int, String>()

    private var checkedId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
        checkedId = builder.checkedId
        onChangeStateListeners.add(this)
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.title?.let {
                text = it
            } ?: run { visibility = View.GONE }
        } ?: throw NoSuchElementException()
    }

    override fun addContainerView(container: View) {
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_content)?.apply {
            val scrollView = ScrollView(requireContext())

            val group = RadioGroup(requireContext())
            group.orientation = RadioGroup.VERTICAL

            group.layoutParams = LinearLayout
                .LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(8.toPx(), 0, 8.toPx(), 0) }

            var height: Int = 0

            builder.items.forEach { model ->
                group.addView(createRadioButton(model).apply {
                    height = measureMaxHeight(this)
                })
            }

            scrollView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                height)

            group.setOnCheckedChangeListener { _, pos ->
                checkedId = itemsMap[pos]
                builder.positiveButtonText ?: run {
                    builder.listener?.onChecked(this@RadioGroupDialog, checkedId!!)
                    dismiss()
                }
            }

            scrollView.isScrollbarFadingEnabled = false
            scrollView.addView(group)
            addView(scrollView)
        }
    }

    private fun measureMaxHeight(view: RadioButton): Int {
        val maxRowCount = 3
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        return if (builder.items.size < maxRowCount) {
            view.measuredHeight * builder.items.size
        } else {
            view.measuredHeight * maxRowCount
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
            builder.checkedId?.let {
                if (model.id == builder.checkedId) isChecked = true
            }
            setPadding(16.toPx(), 16.toPx(), 16.toPx(), 16.toPx())
            itemsMap[id] = model.id
        }
    }

    class Builder : AbstractPopupDialog.Builder() {
        val items = mutableListOf<Item>()
        var checkedId: String? = null
        var listener: RadioDialogListener? = null
        override fun build(): RadioGroupDialog = RadioGroupDialog(this)
    }

    data class Item(
        val id: String,
        val text: String
    )

    override fun onChangeState(dialog: DialogFragment, state: DialogState) {
        if (state == DialogState.ON_POSITIVE_CLICK)
            builder.listener?.onChecked(this@RadioGroupDialog, checkedId!!)
    }
}