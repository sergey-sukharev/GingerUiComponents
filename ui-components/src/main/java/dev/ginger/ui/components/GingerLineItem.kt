package dev.ginger.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import dev.ginger.ui.R
import dev.ginger.ui.components.factory.GingerLineItemViewFactory
import dev.ginger.ui.components.holders.*
import dev.ginger.ui.components.utils.dpToPx
import java.lang.Exception

class GingerLineItem : ConstraintLayout, View.OnClickListener {

    private var viewFactory: GingerLineItemViewFactory

    constructor(context: Context) : this(context, null)

    private lateinit var startIconHolder: StartIconHolder
    private lateinit var actionIconHolder: ActionIconHolder
    private lateinit var dividerHolder: DividerHolder


    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        viewFactory = GingerLineItemViewFactory(
            LayoutInflater.from(context)
                .inflate(R.layout.ginger_base_line_item, this)
        )
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.GingerLineItem)

        val startIconHolder = StartIconHolder(
            viewFactory.startIcon!!,
            getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_startIcon)
        ).apply {
            tint = getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_startIconTint)
            type = attrs.getInt(R.styleable.GingerLineItem_imageType, 1)
        }

        val actionIconHolder = ActionIconHolder(
            viewFactory.endIcon!!,
            getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_actionIcon)
        ).apply {
            tint = getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_actionIconTint)
            type = 1
        }

        val textContentHolder = ItemContentHolder(
            viewFactory.containerView!!,
            viewFactory.titleView!!, viewFactory.subtitleView!!, startIconHolder.type
        ).apply {
            setTitleText(getStringOrNullAttr(attrs, R.styleable.GingerLineItem_titleText))
            setSubtitleText(getStringOrNullAttr(attrs, R.styleable.GingerLineItem_subtitleText))
        }

        val dividerHolder = DividerHolder(
            viewFactory.dividerView!!,
            DividerType.getByValue(attrs.getInt(R.styleable.GingerLineItem_dividerType, 0)),
            viewFactory.containerView!!
        )

        viewFactory.containerView?.minimumHeight = dpToPx(context, 48)

        attrs.recycle()
    }

    fun setTitleText(value: String?) {
        viewFactory.titleView?.text = value
    }

    fun setSubtitleText(value: String?) {
        viewFactory.subtitleView?.apply {
            value?.also {
                text = it
                visibility = View.VISIBLE
            } ?: run {
                visibility = View.GONE
            }
        }
    }

    fun setTitleStyle(style: Int) {
        TODO("Not yet implemented")
    }

    fun setSubtitleStyle(style: Int) {
        TODO("Not yet implemented")
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    protected fun getStringOrNullAttr(attrs: TypedArray, attrId: Int): String? = try {
        attrs.getStringOrThrow(attrId)
    } catch (e: Exception) {
        null
    }

    protected fun getIntOrNullAttr(attrs: TypedArray, attrId: Int): Int? = try {
        attrs.getIntOrThrow(attrId)
    } catch (e: Exception) {
        null
    }

    protected fun getResourceOrNullAttr(attrs: TypedArray, attrId: Int): Int? = try {
        attrs.getResourceIdOrThrow(attrId)
    } catch (e: Exception) {
        null
    }

}