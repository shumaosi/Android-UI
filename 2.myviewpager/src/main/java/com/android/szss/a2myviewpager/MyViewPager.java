package com.android.szss.a2myviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/19
 */

public class MyViewPager extends ViewGroup {

    /*
    * 手势识别器
    * 1.定义
    * 2.实例化————将想要的方法重现
    * 3.在 onTouchEvent() 中把事件传递给手势识别器
    *
    * */
    private GestureDetector mGestureDetector;

    private ScrollTool mScrollTool;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    private void initView(Context context) {
        mScrollTool = new ScrollTool();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, getScrollY());
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });

    }

    private float startX;
    private int currentIndex;

    float downX;
    float downY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        boolean result = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float endX = event.getX();
                float endY = event.getY();

                if ((Math.abs(endX - downX) > Math.abs(endY - downY)) && (Math.abs(endX - downX) > 8)) {
                    result = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                float endX = event.getX();
                if ((startX - endX) > getWidth() / 2) {
                    currentIndex++;
                } else if ((endX - startX) > getWidth() / 2) {
                    currentIndex--;
                }
                if (currentIndex < 0) {
                    currentIndex = 0;
                }
                if (currentIndex > getChildCount() - 1) {
                    currentIndex = getChildCount() - 1;
                }

                int distanceX = currentIndex * getWidth() - getScrollX();

                mScrollTool.startScroll(getScrollX(), getScrollY(), distanceX, 0);

                //                scrollTo(currentIndex * getWidth(), 0);

                invalidate();
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {

        if (mScrollTool.updateScrollStatus()) {
            scrollTo((int) mScrollTool.getCurrentX(), 0);
            invalidate();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
