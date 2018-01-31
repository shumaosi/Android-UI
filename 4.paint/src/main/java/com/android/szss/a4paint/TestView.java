package com.android.szss.a4paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/13
 */

public class TestView extends View {

    private Paint mPaint = new Paint();

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        //开启抗锯齿
        mPaint.setAntiAlias(true);
        //设置背景颜色
        canvas.drawColor(Color.RED);
        //画圆
//        canvas.drawCircle(200, 200, 100, mPaint);
//        //画矩形 (左上，右下两个点的坐标)
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(400,100,800,500,mPaint);
//        //画点  Paint.Cap.SQUARE(方形)  ;  Paint.Cap.ROUND(圆形)
//        mPaint.setColor(Color.GREEN);
//        mPaint.setStrokeWidth(20);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(600,600,mPaint);
//        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
//        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
//        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
//                8 /* 一共绘制 8 个数（4 个点）*/, mPaint);
//        //画线
//        mPaint.setColor(Color.BLACK);
//        canvas.drawLine(200,200,500,500,mPaint);
//        float[] points2 = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points2, mPaint);
//        //画椭圆 left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawRoundRect(100, 100, 500, 300, 50, 50, mPaint);
//        }
        //drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；
        // startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
        // sweepAngle 是弧形划过的角度；
        // useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
        mPaint.setStyle(Paint.Style.FILL); // 填充模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇形
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
        }
        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形
        }
    }
}
