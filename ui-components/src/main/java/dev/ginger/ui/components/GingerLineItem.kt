package dev.ginger.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import dev.ginger.ui.R
import dev.ginger.ui.components.factory.GingerLineItemViewFactory
import dev.ginger.ui.components.holders.*
import dev.ginger.ui.components.holders.divider.DividerHolder
import dev.ginger.ui.components.holders.divider.DividerType
import dev.ginger.ui.components.holders.icon.ActionIconHolder
import dev.ginger.ui.components.holders.icon.ActionIconType
import dev.ginger.ui.components.holders.icon.StartIconHolder
import dev.ginger.ui.components.holders.text.SubtitleHolder
import dev.ginger.ui.components.holders.text.SubtitleLineType
import dev.ginger.ui.components.holders.text.TitleHolder
import java.lang.Exception

class GingerLineItem : ConstraintLayout, View.OnClickListener {

    private var viewFactory: GingerLineItemViewFactory

    constructor(context: Context) : this(context, null)

    private val startIconHolder: StartIconHolder
    private val dividerHolder: DividerHolder
    private val textContentHolder: TextContentHolder
    private lateinit var actionIconHolder: ActionIconHolder

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.GingerLineItem)

        val resourceLayout = R.layout.ginger_base_line_item

        viewFactory = GingerLineItemViewFactory(
            LayoutInflater.from(context).inflate(resourceLayout, this),
            GingerActionType.getByValue(
                attrs.getInt(R.styleable.GingerLineItem_actionType, GingerActionType.ICON.ordinal)
            )
        )

        startIconHolder = StartIconHolder(
            viewFactory.startIcon!!,
            getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_startIcon)
        ).apply {
            tint = getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_startIconTint)
            type = attrs.getInt(R.styleable.GingerLineItem_imageType, ActionIconType.ICON.ordinal)
        }

        textContentHolder = TextContentHolder(
            viewFactory.containerView!!,
            TitleHolder(viewFactory.titleView!!),
            SubtitleHolder(
                viewFactory.subtitleView!!,
                lineType = SubtitleLineType.getByValue(
                    attrs.getInt(R.styleable.GingerLineItem_lineType,
                        SubtitleLineType.ONE_LINE.ordinal)
                )
            ),
            startIconHolder.type
        ).apply {
            setTitleText(getStringOrNullAttr(attrs, R.styleable.GingerLineItem_titleText))
            setSubtitleText(getStringOrNullAttr(attrs, R.styleable.GingerLineItem_subtitleText))
        }

        dividerHolder = DividerHolder(
            viewFactory.dividerView!!,
            DividerType.getByValue(attrs.getInt(R.styleable.GingerLineItem_dividerType,
            DividerType.NONE.ordinal)),
            viewFactory.containerView!!
        )

        (viewFactory.actionView as? ImageView)?.let {
            actionIconHolder = ActionIconHolder(it,
                getResourceOrNullAttr(attrs, R.styleable.GingerLineItem_actionIcon))
        }

        attrs.recycle()
    }

    private fun getResourceLayout(attrId: Int): Int {
        return when (GingerActionType.getByValue(attrId)) {
            GingerActionType.CHECKBOX -> R.layout.ginger_view_action_checkbox
            GingerActionType.SWITCH -> R.layout.ginger_view_action_switcher
            else -> R.layout.ginger_view_action_icon
        }
    }

    fun getActionView(): Any? = viewFactory.actionView

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