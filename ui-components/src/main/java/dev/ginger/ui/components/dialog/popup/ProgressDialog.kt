package dev.ginger.ui.components.dialog.popup

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import dev.ginger.ui.R
import dev.ginger.ui.components.dialog.ProgressDialogTimerTask
import java.util.*
import kotlin.NoSuchElementException
import kotlin.concurrent.schedule

class ProgressDialog(private val builder: Builder) : AbstractDialog(builder) {

    private val delayTimer = Timer()
    private var delayTimerTask: TimerTask? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleView(view)
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
        if (isVisible)
            super.dismiss()
        delayTimer.cancel()
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

    override fun onPositiveButtonClick() {

    }

}