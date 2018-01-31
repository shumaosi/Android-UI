package com.android.szss.a3touchtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/20
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int x = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(x, x);
    }
}
