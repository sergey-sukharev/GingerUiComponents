package dev.ginger.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import dev.ginger.ui.R


class GingerLineItem : LinearLayout, View.OnClickListener {

    private lateinit var startIconView: StartIconView

    constructor(context: Context) : this(context, null)

    // A margin between content container (title, subtitle) and start image. Default 32dp
    private var contentContainerMargin = 32

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.GingerLineItem)

        attrs.recycle()

        initBaseComponents()
    }

    private fun initBaseComponents() {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.ginger_base_line_item, this)

        view?.findViewById<ConstraintLayout>(R.id.container)?.setOnClickListener(this)
//        titleView = view.findViewById(R.id.strgrs_label_title)
//        subtitleView = view.findViewById(R.id.strgrs_label_subtitle)
//        dividerView = view.findViewById(R.id.divider)
//        iconStartView = view.findViewById(R.id.iconStart)
//        iconEndView = view.findViewById(R.id.iconEnd)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun getContentMargin(imageType: Int): Int {
        return when (imageType) {
            0 -> 0
            1 -> 32
            else -> 16
        }
    }

}

private class StartIconView {
    var width: Int = 24
    var height: Int = 24
    var type: Int = 0
    var drawable: Drawable? = null
        get() {
            if (field != null && tint != null)
                DrawableCompat.setTint(field!!, tint!!)
            return field
        }
    var tint: Int? = null
}

private data class TitleView(
    val text: String,
    val style: Int
)

private data class SubtitleView(
    val text: String,
    val maxLines: Int = 1,
    val style: Int? = null
)