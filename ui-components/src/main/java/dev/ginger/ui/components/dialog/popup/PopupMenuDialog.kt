package dev.ginger.ui.components.dialog.popup

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.toPx

class PopupMenuDialog(private val builder: Builder) : AbstractDialog(builder) {


    class Builder : AbstractDialog.AbstractBuilder() {
        val items = mutableListOf<Item>()
        var titleText: String? = null
        override fun build(): PopupMenuDialog {
            return PopupMenuDialog(this)
        }
    }


    override fun addHeaderView(container: View) {
        val headerC = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_container_title, null)
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_header_container)?.addView(headerC)
    }

    override fun addFooterView(container: View) {

    }

    override fun addContainerView(container: View) {
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_frame)?.let { frame ->
            val itemsContainer = LinearLayout(requireContext())
            itemsContainer.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            itemsContainer.orientation = LinearLayout.VERTICAL

            builder.items.forEach { model ->
                val itemView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.ginger_dialog_popup_item, null)

                itemView.findViewById<TextView?>(R.id.ginger_dialog_popup_text)?.apply {
                    text = model.title
                }

                itemView.findViewById<ImageView?>(R.id.ginger_dialog_popup_icon)?.apply {
                    model.icon?.let {
                        setImageDrawable(it)
                        visibility = View.VISIBLE
                    }
                }

                itemView.setOnClickListener {
                    Toast.makeText(requireContext(), "Selected ${model.id}", Toast.LENGTH_SHORT)
                        .show()
                    dismiss()
                }
                itemsContainer.addView(itemView)
            }

            frame.addView(itemsContainer)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.titleText?.let {
                text = it
            } ?: run { visibility = View.INVISIBLE }
        } ?: throw NoSuchElementException()
    }

    data class Item(
        val id: String,
        val title: String,
        val icon: Drawable? = null
    )

}