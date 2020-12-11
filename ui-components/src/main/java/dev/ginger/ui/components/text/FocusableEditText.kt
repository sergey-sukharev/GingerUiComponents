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

    private val imeKeyCodes = listOf(KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_ENTER,
        EditorInfo.IME_ACTION_DONE)

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        if (focused) DispatchOnTouchHandler.subscribeOnEvent(context, this)
        else removeCursor()

        super.onFocusChanged(focused, direction, previouslyFocusedRect)
    }

    override fun dispatchKeyEventPreIme(event: KeyEvent?): Boolean {
        if (event != null)
            removeCursorByActionCode(event.keyCode)

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
        focusable = View.NOT_FOCUSABLE
        DispatchOnTouchHandler.unsubscribeOnEvent(context, this)
    }

    override fun onEditorAction(actionCode: Int) {
        removeCursorByActionCode(actionCode)
        super.onEditorAction(actionCode)
    }

    private fun removeCursorByActionCode(keyCode: Int) {
        if (imeKeyCodes.contains(keyCode)) removeCursor()
    }

    override fun onTouch(ev: MotionEvent) {
        Rect().apply {
            getGlobalVisibleRect(this)
            if (!contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                hideSoftKeyboard()
                clearFocus()
            }
        }
    }


}