package dev.ginger.ui.dialogs.fullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import dev.ginger.ui.R
import java.util.*

class FragmentPopupDialog(): DialogFragment() {

    private var builder: Builder? = null

    constructor(builder: Builder): this() {
        this.builder = builder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.GingerTheme_FullScreenDialog)

        dialog?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            window?.setLayout(width, height)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ginger_fullscreen_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        builder?.fragment?.let {fragment ->
            childFragmentManager.addOnBackStackChangedListener {
                if (childFragmentManager.backStackEntryCount == 0)
                    dismiss()
            }
            childFragmentManager.beginTransaction()
                .replace(R.id.ginger_fullscreen_dialog_content, fragment)
                .addToBackStack(UUID.randomUUID().toString())
                .commit()
        }
    }

    class Builder {
        var fragment: Fragment? = null
        fun build() = FragmentPopupDialog(this)
    }
}