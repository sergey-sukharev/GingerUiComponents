package dev.ginger.ui.components.dialog.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import dev.ginger.ui.R

class ProgressDialog(private val builder: Builder) : AbstractDialog(builder) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.title?.let {
                text = it
            } ?: run { visibility = View.INVISIBLE }
        } ?: throw NoSuchElementException()
    }

    class Builder : AbstractDialog.AbstractBuilder() {
        var title: String? = null
        var message: String? = null
        var delay: Int? = null

        override fun build(): ProgressDialog {
            return ProgressDialog(this)
        }
    }

    override fun addHeaderView(container: View) {
        val headerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_container_title, null)

        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_header)
            ?.addView(headerView)
    }

    override fun addFooterView(container: View) {

    }

    override fun addContainerView(container: View) {
        val containerView = LayoutInflater.from(requireContext())
            .inflate(R.layout.ginger_dialog_progress_layout, null)

        containerView?.findViewById<TextView?>(R.id.ginger_dialog_progress_text)?.apply {
            builder.message?.let {
                text = it
            } ?: run { visibility = View.GONE }
        }
        container.findViewById<FrameLayout?>(R.id.ginger_dialog_container_content)
            ?.addView(containerView)
    }

    override fun onPositiveButtonClick() {

    }

}