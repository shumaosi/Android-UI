package com.android.szss.a3touchtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/20
 */

public class MyLinearLayout extends LinearLayout {


    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int width = getWidth() / 3;
        int height = getHeight();
        int childCount = getChildCount();
        float eventX = event.getX();

        if (eventX < width) {
            getChildAt(0).dispatchTouchEvent(event);
            return true;
        } else if (eventX >= width && eventX <= 2 * width) {
            if (event.getY() <= height / 2) {
                for (int i = 0; i < childCount; i++) {
                    getChildAt(i).dispatchTouchEvent(event);
                }
            } else {
                getChildAt(1).dispatchTouchEvent(event);
            }
            return true;
        } else if (eventX > width * 2) {
            getChildAt(2).dispatchTouchEvent(event);
            return true;
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }
}
