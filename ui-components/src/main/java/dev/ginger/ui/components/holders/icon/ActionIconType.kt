package dev.ginger.ui.components.holders.icon

/**
 * Type size for action icon
 * NONE - invisible state
 * ICON - (24dp * 24dp)
 * SMALL - (40dp * 40dp)
 * MIDDLE - (56dp * 56dp)
 * LARGE - (100dp * 56dp)
 * @property value - action type
 */
enum class ActionIconType(private val value: Int) {
    NONE(0), ICON(1), SMALL(2), MIDDLE(3), LARGE(4);

    companion object {
        fun getByValue(value: Int): ActionIconType {
            return values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}