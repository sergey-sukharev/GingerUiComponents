package dev.ginger.ui.components.holders.text

import android.widget.TextView

class SubtitleHolder(view: TextView, color: Int?, style: Int? = null,
                     lineType: SubtitleLineType = SubtitleLineType.ONE_LINE ):
    BaseTextHolder(view, color, style) {

    init {
        println("ConstructorSubtitle")
        changeLineType(lineType)
    }

    private fun changeLineType(lineType: SubtitleLineType) {
        when(lineType) {
            SubtitleLineType.ONE_LINE -> view.maxLines = 1
            SubtitleLineType.TWO_LINE -> view.maxLines = 2
            SubtitleLineType.THREE_LINE -> view.maxLines = 3
        }
    }

}