package dev.ginger.ui.components.holders.text

import android.widget.TextView

open class BaseTextHolder (
    val view: TextView,
    val color: Int?,
    val style: Int? = null
){

    init {
        println("BaseConstructorSubtitle")
        color?.let {
            view.setTextColor(view.resources.getColor(it, null))
        }
    }

}