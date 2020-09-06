package dev.ginger.ui.components.composite

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import dev.ginger.ui.R
import dev.ginger.ui.components.holders.*
import dev.ginger.ui.components.holders.divider.DividerHolder
import dev.ginger.ui.components.holders.divider.DividerType
import dev.ginger.ui.components.holders.icon.ActionIconHolder
import dev.ginger.ui.components.holders.icon.ActionIconType
import dev.ginger.ui.components.holders.icon.StartIconHolder
import dev.ginger.ui.components.holders.text.SubtitleHolder
import dev.ginger.ui.components.holders.text.SubtitleLineType
import dev.ginger.ui.components.holders.text.TitleHolder
import dev.ginger.ui.components.utils.getColorOrNullAttr
import dev.ginger.ui.components.utils.getResourceOrNullAttr
import dev.ginger.ui.components.utils.getStringOrNullAttr

class GingerGingerCompositeTextView : ConstraintLayout, View.OnClickListener {

    private var viewFactory: GingerCompositeTextViewFactory

    constructor(context: Context) : this(context, null)

    private val startIconHolder: StartIconHolder
    private val dividerHolder: DividerHolder
    private val textContentHolder: TextContentHolder
    private lateinit var actionIconHolder: ActionIconHolder

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.GingerCompositeTextView)
        val resourceLayout = R.layout.ginger_base_line_item

        viewFactory = GingerCompositeTextViewFactory(
            LayoutInflater.from(context).inflate(resourceLayout, this),
            GingerActionType.getByValue(
                attrs.getInt(R.styleable.GingerCompositeTextView_actionType, GingerActionType.ICON.ordinal)
            )
        )

        val titleTextColor: Int? = getResourceOrNullAttr(
            attrs,
            R.styleable.GingerCompositeTextView_titleTextColor
        )

        val subtitleTextColor: Int? = getResourceOrNullAttr(
            attrs,
            R.styleable.GingerCompositeTextView_subtitleTextColor
        )

        startIconHolder = StartIconHolder(
            viewFactory.startIcon!!,
            getResourceOrNullAttr(attrs, R.styleable.GingerCompositeTextView_startIcon)
        ).apply {
            tint = getColorOrNullAttr(attrs, R.styleable.GingerCompositeTextView_startIconTint)
            type =
                attrs.getInt(R.styleable.GingerCompositeTextView_startIconSize, ActionIconType.ICON.ordinal)
        }

        textContentHolder = TextContentHolder(
            viewFactory.containerView!!,
            TitleHolder(viewFactory.titleView!!, titleTextColor),
            SubtitleHolder(
                viewFactory.subtitleView!!,
                subtitleTextColor,
                lineType = SubtitleLineType.getByValue(
                    attrs.getInt(
                        R.styleable.GingerCompositeTextView_lineType,
                        SubtitleLineType.ONE_LINE.ordinal
                    )
                )
            ),
            startIconHolder.type
        ).apply {
            setTitleText(getStringOrNullAttr(attrs, R.styleable.GingerCompositeTextView_titleText))
            setSubtitleText(getStringOrNullAttr(attrs, R.styleable.GingerCompositeTextView_subtitleText))
        }

        val dividerTint = getColorOrNullAttr(attrs, R.styleable.GingerCompositeTextView_dividerTint)
            ?: ContextCompat.getColor(context, R.color.ginger_divider_tint)

        dividerHolder = DividerHolder(
            viewFactory.dividerView!!,
            DividerType.getByValue(
                attrs.getInt(
                    R.styleable.GingerCompositeTextView_dividerType,
                    DividerType.NONE.ordinal
                )
            ),
            dividerTint,
            viewFactory.containerView!!
        )

        (viewFactory.actionView as? ImageView)?.let {
            actionIconHolder = ActionIconHolder(
                it,
                getResourceOrNullAttr(attrs, R.styleable.GingerCompositeTextView_actionIcon)
            ).apply {
                tint = getColorOrNullAttr(attrs, R.styleable.GingerCompositeTextView_actionIconTint)
            }
        }

        viewFactory.containerView?.setOnClickListener(this)

        attrs.recycle()
    }

    fun getActionView(): Any? = viewFactory.actionView

    fun setTitleText(value: String?) {
        viewFactory.titleView?.text = value
    }

    fun getTitleText() = viewFactory.titleView?.text.toString()

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

    fun getSubtitleText() = viewFactory.subtitleView?.text.toString()

    private var onClickListener: OnClickListener? = null

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }

    override fun onClick(v: View?) {
        when (viewFactory.actionView) {
            is ImageView -> onClickListener?.onClick(v)
            else -> {
                (viewFactory.actionView as? CompoundButton)?.apply {
                    isChecked = !isChecked
                }
            }
        }
    }

}