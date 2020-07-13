package dev.ginger.ui.components.holders

enum class GingerActionType(private val value: Int) {
    ICON(0), CHECKBOX(1), SWITCH(2);

    companion object {
        fun getByValue(value: Int): GingerActionType {
            return values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}