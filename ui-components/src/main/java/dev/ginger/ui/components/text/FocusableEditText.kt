package dev.ginger.ui.components.text

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import dev.ginger.ui.components.utils.hideSoftKeyboard

import dev.ginger.ui.handlers.DispatchOnTouchHandler

@SuppressLint("NewApi")
class FocusableEditText : androidx.appcompat.widget.AppCompatEditText,
    DispatchOnTouchHandler.OnDispatchTouchCallback {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        if (focused) DispatchOnTouchHandler.subscribeOnEvent(context, this)
        else removeCursor()

        super.onFocusChanged(focused, direction, previouslyFocusedRect)
    }

    override fun dispatchKeyEventPreIme(event: KeyEvent?): Boolean {
        event?.let { keyEvent ->
            when(keyEvent.keyCode) {
                KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_ENTER -> removeCursor()
            }
        }
        return super.dispatchKeyEventPreIme(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        enableCursor()
        return super.onTouchEvent(event)
    }

    private fun enableCursor() {
        focusable = View.FOCUSABLE
        isFocusableInTouchMode = true
    }

    fun removeCursor() {
        this.focusable = View.NOT_FOCUSABLE
        DispatchOnTouchHandler.unsubscribeOnEvent(context, this)
    }

    override fun onEditorAction(actionCode: Int) {
        if (actionCode == EditorInfo.IME_ACTION_DONE)
            removeCursor()
        super.onEditorAction(actionCode)
    }

    override fun onTouch(ev: MotionEvent) {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        if (!rect.contains(ev.rawX.toInt(), ev.rawX.toInt())) {
            hideSoftKeyboard()
            clearFocus()
        }
    }


}