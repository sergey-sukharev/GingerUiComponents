package dev.ginger.ui.components.holders.text

import android.widget.TextView

class SubtitleHolder(view: TextView, style: Int? = null,
                     lineType: SubtitleLineType = SubtitleLineType.ONE_LINE ):
    BaseTextHolder(view, style) {

    init {
        println("ConstructorSubtitle")
        changeLineType(lineType)
    }

    private fun changeLineType(lineType: SubtitleLineType) {
        when(lineType) {
            SubtitleLineType.ONE_LINE -> view.setLines(1)
            SubtitleLineType.TWO_LINE -> view.setLines(2)
            SubtitleLineType.THREE_LINE -> view.setLines(3)
        }
    }

}