package dev.ginger.ui.components.holders.divider

enum class DividerType(private val value: Int) {
    NONE(0), FULL_BLEED(1), INSET(2), MIDDLE(3);

    companion object {
        fun getByValue(value: Int): DividerType {
            return values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}