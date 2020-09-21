package dev.ginger.ui.dialogs.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import dev.ginger.ui.R
import java.util.*
import kotlin.NoSuchElementException
import kotlin.concurrent.schedule

class ProgressDialog(private val builder: Builder) : AbstractDialog(builder) {

    private val delayTimer = Timer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
        setMessage(view)
    }

    private fun setMessage(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_progress_text)?.apply {
            builder.message?.let {
                text = it
            } ?: run { visibility = View.GONE }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        builder.delay?.let { delay ->
            delayTimer.schedule(delay) {
                super.show(manager, tag)
            }
        } ?: run {
            super.show(manager, tag)
        }
    }

    override fun dismiss() {
        delayTimer.cancel()
        if (lifecycle.currentState == Lifecycle.State.RESUMED)
            super.dismiss()
    }

    private fun setTitleView(view: View) {
        view.findViewById<TextView?>(R.id.ginger_dialog_title_text)?.apply {
            builder.title?.let {
                text = it
            } ?: run { visibility = View.GONE }
        } ?: throw NoSuchElementException()
    }

    class Builder : AbstractBuilder() {
        var title: String? = null
        var message: String? = null
        var delay: Long? = null

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

}