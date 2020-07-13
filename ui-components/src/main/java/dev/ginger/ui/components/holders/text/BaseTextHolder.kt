package dev.ginger.ui.components.holders.text

import android.widget.TextView

open class BaseTextHolder (
    val view: TextView,
    val style: Int? = null
){

    init {
        println("BaseConstructorSubtitle")
    }

}