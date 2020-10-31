package dev.ginger.ui.components.holders

/**
 * Action type of GingerLineItem component
 * ICON - small icon (24dp * 24dp)
 * CHECKBOX - checkbox view
 * SWITCH - switch view
 * @property value - action type
 */
enum class GingerActionType(private val value: Int) {
    ICON(0), CHECKBOX(1), SWITCH(2);

    companion object {
        fun getByValue(value: Int): GingerActionType {
            return values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}