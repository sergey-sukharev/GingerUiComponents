package dev.ginger.ui.components.holders.text

/**
 * Subtitle's line type of GingerLineItem component
 *
 * @property value - line type
 */
enum class SubtitleLineType(private val value: Int) {
    ONE_LINE(0), TWO_LINE(1), THREE_LINE(2);

    companion object {
        fun getByValue(value: Int): SubtitleLineType {
            return SubtitleLineType.values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}