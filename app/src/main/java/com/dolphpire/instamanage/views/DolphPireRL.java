package com.dolphpire.instamanage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class DolphPireRL extends RelativeLayout {

    public DolphPireRL(Context context) {
        super(context);
    }

    public DolphPireRL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DolphPireRL(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}