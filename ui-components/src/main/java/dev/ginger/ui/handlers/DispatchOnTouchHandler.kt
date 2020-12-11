package dev.ginger.ui.handlers

import android.content.Context
import android.view.MotionEvent

object DispatchOnTouchHandler {

    private val callbacks = mutableMapOf<Context, MutableList<OnDispatchTouchCallback>>()

    fun registerEvent(context: Context, ev: MotionEvent) {
        callbacks[context]?.forEach {
            it.onTouch(ev)
        }
    }

    fun subscribeOnEvent(context: Context, callback: OnDispatchTouchCallback) {
        callbacks[context]?.let { callbacks ->
            if (!callbacks.contains(callback))
                callbacks.add(callback)
        } ?: run {
            callbacks.put(context, mutableListOf(callback))
        }
    }

    fun unsubscribeOnEvent(context: Context, callback: OnDispatchTouchCallback) {
        callbacks[context]?.remove(callback)
    }

    interface OnDispatchTouchCallback {
        fun onTouch(ev: MotionEvent)
    }
}