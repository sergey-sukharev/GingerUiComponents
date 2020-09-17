/*
 * This is the source code of Telegram for Android v. 5.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package dev.ginger.ui.components.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.ginger.ui.R;
import dev.ginger.ui.components.utils.ConverterUtilsKt;

import static android.view.View.MeasureSpec.getSize;


public class TextDetailCell extends FrameLayout {

    private TextView textView;
    private TextView valueTextView;
    private boolean needDivider;
    private boolean contentDescriptionValueFirst;

    long startTime = System.currentTimeMillis();

    public TextDetailCell(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    public TextDetailCell(Context context) {
        super(context);

        textView = new TextView(context);
        textView.setBackgroundColor(context.getColor(R.color.colorAccent));
//        textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setGravity(Gravity.LEFT);
        textView.setLines(1);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);
        addView(textView, createFrame(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                Gravity.LEFT, ConverterUtilsKt.dpToPx(context, 16), 8, 23, 0));

        valueTextView = new TextView(context);
//        valueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        valueTextView.setLines(1);
        valueTextView.setMaxLines(1);
        valueTextView.setSingleLine(true);
        valueTextView.setGravity(Gravity.LEFT);
        valueTextView.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);
        addView(valueTextView, createFrame(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.LEFT, 23, 33, 23, 0));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(MeasureSpec.makeMeasureSpec(getSize(widthMeasureSpec), MeasureSpec.EXACTLY),
//                MeasureSpec.makeMeasureSpec(ConverterUtilsKt.dpToPx(getContext(),60) + (needDivider ? 1 : 0), MeasureSpec.EXACTLY));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(
                "TEXT_VIEW",
                "TELEGA OnMeasure" + (System.currentTimeMillis() - startTime));
    }

    public void setTextAndValue(String text, String value, boolean divider) {
        textView.setText(text);
        valueTextView.setText(value);
        needDivider = divider;
        setWillNotDraw(!needDivider);
    }

    public void setTextWithEmojiAndValue(String text, CharSequence value, boolean divider) {
//        textView.setText(Emoji.replaceEmoji(text, textView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14), false));
        valueTextView.setText(value);
        needDivider = divider;
        setWillNotDraw(!divider);
    }

    public void setContentDescriptionValueFirst(boolean contentDescriptionValueFirst) {
        this.contentDescriptionValueFirst = contentDescriptionValueFirst;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        textView.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (needDivider) {
            canvas.drawLine(ConverterUtilsKt.dpToPx(getContext(),20), getMeasuredHeight() - 1, getMeasuredWidth() - 0, getMeasuredHeight() - 1, new Paint());
        }
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        final CharSequence text = textView.getText();
        final CharSequence valueText = valueTextView.getText();
        if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(valueText)) {
            info.setText((contentDescriptionValueFirst ? valueText : text) + ": " + (contentDescriptionValueFirst ? text : valueText));
        }
    }

    public FrameLayout.LayoutParams createFrame(int width, float height, int gravity, float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getSize(width), getSize((int) height), gravity);
        layoutParams.setMargins(ConverterUtilsKt.dpToPx(getContext(), (int) leftMargin), ConverterUtilsKt.dpToPx(getContext(), (int) topMargin),
                ConverterUtilsKt.dpToPx(getContext(), (int) rightMargin), ConverterUtilsKt.dpToPx(getContext(), (int) bottomMargin));
        return layoutParams;
    }
}
