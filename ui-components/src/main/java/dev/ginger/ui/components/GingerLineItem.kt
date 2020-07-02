package dev.ginger.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.core.view.marginLeft
import dev.ginger.ui.R
import dev.ginger.ui.components.utils.dpToPx
import kotlinx.android.synthetic.main.ginger_base_line_item.view.*


class GingerLineItem : LinearLayout, View.OnClickListener {

    private lateinit var startIconView: StartIconView
    private lateinit var contentContainer: LinearLayout


    constructor(context: Context) : this(context, null)

    // A margin between content container (title, subtitle) and start image. Default 32dp
    private var contentContainerMargin = 32

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.GingerLineItem)

        val imageSrc = attrs.getDrawable(R.styleable.GingerLineItem_imageSrc)

        val imageType = attrs.getInt(R.styleable.GingerLineItem_imageType, 0)

        imageSrc?.let { src ->
            startIconView = StartIconView(context).apply {
                type = imageType
                drawable = src
            }
        } ?: kotlin.run {
            startIconView = StartIconView(context).apply {
                type = 0
            }
        }



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
        val iconStartView: ImageView? = view.findViewById(R.id.iconStart)
        val contentContainer: LinearLayout? = view.findViewById(R.id.ginger_text_content)

        val constraintSet = ConstraintSet()
        constraintSet.clone(container)
        constraintSet.clear(contentContainer!!.id, ConstraintSet.LEFT)
        constraintSet.connect(
            contentContainer.id, ConstraintSet.LEFT,
            iconStartView!!.id, ConstraintSet.RIGHT, getContentMargin(startIconView.type)
        )
        constraintSet.applyTo(container)


        startIconView.apply {
            if (iconStartView != null)
                setInImageView(iconStartView)
        }
//        iconEndView = view.findViewById(R.id.iconEnd)
    }

    override fun onClick(v: View?) {

    }

    private fun getContentMargin(imageType: Int): Int {
        return when (imageType) {
            1 -> dpToPx(context, 32)
            else -> dpToPx(context, 16)
        }
    }

}

private class StartIconView(val context: Context) {
    var type: Int = 0
    var drawable: Drawable? = null
        get() {
            if (field != null && tint != null)
                DrawableCompat.setTint(field!!, tint!!)
            return field
        }
    var tint: Int? = null

    fun setInImageView(imageView: ImageView) {
        resizeImageView(imageView)
        imageView.setImageDrawable(drawable)
    }

    private fun resizeImageView(imageView: ImageView) {
        when (type) {
            0 -> imageView.visibility = View.GONE
            1 -> {
                imageView.layoutParams.apply {
                    height = dpToPx(context, 24)
                    width = dpToPx(context, 24)
                }
            }
            4 -> {
                imageView.layoutParams.apply {
                    height = dpToPx(context, 100)
                    width = dpToPx(context, 56)
                }
            }
            else -> {
                imageView.layoutParams.apply {
                    height = dpToPx(context, 32)
                    width = dpToPx(context, 32)
                }
            }
        }
    }


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