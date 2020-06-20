package com.dolphpire.instamanage.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("AppCompatCustomView")
public class DolphPireIC extends CircleImageView
{

    public DolphPireIC(Context context) {
        super(context);
    }

    public DolphPireIC(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DolphPireIC(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

}