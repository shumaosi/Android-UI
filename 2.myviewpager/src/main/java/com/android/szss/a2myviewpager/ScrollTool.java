package com.android.szss.a2myviewpager;

import android.os.SystemClock;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/19
 */

public class ScrollTool {

    private float currentX;
    private float startX;
    private float startY;
    private int distanceX;
    private long startTime;
    private boolean isFinish;
    private long totalTime = 500;

    public void startScroll(float startX, float startY, int distanceX, int distanceY) {
        this.startX = startX;
        this.startY = startY;
        this.distanceX = distanceX;
        this.startTime = SystemClock.uptimeMillis();
        this.isFinish = false;
    }

    public boolean updateScrollStatus() {
        if (isFinish) {
            return false;
        }

        long endTime = SystemClock.uptimeMillis();

        long passTime = endTime - startTime;

        if (passTime < totalTime) {
            float scrollDistance = passTime * distanceX / totalTime;
            currentX = startX + scrollDistance;
        } else {
            isFinish = true;
            currentX = startX + distanceX;
        }
        return true;
    }

    public float getCurrentX() {
        return currentX;
    }
}
