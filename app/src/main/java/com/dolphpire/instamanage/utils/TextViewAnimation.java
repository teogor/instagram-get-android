package com.dolphpire.instamanage.utils;

import android.animation.ValueAnimator;
import android.widget.TextView;

public class TextViewAnimation
{

    public static void animateTextView(int initialValue, int finalValue, final TextView textview)
    {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1500);

        valueAnimator.addUpdateListener(valueAnimator1 -> textview.setText(valueAnimator1.getAnimatedValue().toString()));
        valueAnimator.start();

    }

}
