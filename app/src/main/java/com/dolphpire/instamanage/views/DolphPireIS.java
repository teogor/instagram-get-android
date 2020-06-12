package com.dolphpire.instamanage.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class DolphPireIS extends ImageView {

    public DolphPireIS(Context context) {
        super(context);
    }

    public DolphPireIS(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DolphPireIS(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}