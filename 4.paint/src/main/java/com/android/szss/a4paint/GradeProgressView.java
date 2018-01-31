package com.android.szss.a4paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by wuwei on 2017/10/9.
 */

public class GradeProgressView extends View {

    private int mMeasureWidth;
    private int mMeasureHeight;

    //外圈到布局边上的距离
    private int mMarginWidth = 18;
    //内圈和外圈的距离
    private int mPaddingWidth = 20;
    //内圈圆点点之间的距离
    private int mPointWidth = 14;
    //圆点半径
    private int mPointRadius = 4;

    private Bitmap mPointBitmap;
    private Bitmap mArrowBitmap;

    private Paint mOutsidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mInsidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private ValueAnimator animator;
    private int mProgress = 0;

    private Rect mRect = new Rect();

    public GradeProgressView(Context context) {
        this(context, null);
    }

    public GradeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {

        mRectPaint.setColor(ContextCompat.getColor(getContext(), R.color.transparent));

        mOutsidePaint.setColor(ContextCompat.getColor(getContext(), R.color.all_white));
        mOutsidePaint.setStrokeWidth(4);
        mOutsidePaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.addCircle(0, 0, mPointRadius, Path.Direction.CCW);
        mInsidePaint.setColor(ContextCompat.getColor(getContext(), R.color.all_white));
        mInsidePaint.setStrokeCap(Paint.Cap.ROUND);
        mInsidePaint.setPathEffect(new PathDashPathEffect(path, mPointWidth, mPointWidth, PathDashPathEffect.Style.ROTATE));

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.all_white));
        mPointPaint.setStrokeWidth(20);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置发光效果
        mPointPaint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.NORMAL));

        Matrix matrix2 = new Matrix();
        matrix2.setRotate(48);
        mPointBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_growth_point);
        // 围绕原地进行旋转
        mPointBitmap = Bitmap.createBitmap(mPointBitmap, 0, 0, mPointBitmap.getWidth(), mPointBitmap.getHeight(), matrix2, false);

        Matrix matrix = new Matrix();
        matrix.setRotate(48);
        mArrowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_growth_arrow);
        // 围绕原地进行旋转
        mArrowBitmap = Bitmap.createBitmap(mArrowBitmap, 0, 0, mArrowBitmap.getWidth(), mArrowBitmap.getHeight(), matrix, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float degree = 1.8f * mProgress;

        float radius = (mMeasureWidth - mMarginWidth * 2) / 2;
        float x = radius + mMarginWidth;
        float y = radius + mMarginWidth;

        mRect.set(mMarginWidth, mMarginWidth, new Float(2 * radius + mMarginWidth).intValue(), new Float(2 * radius + mMarginWidth).intValue());

        canvas.save();
        //裁剪

        Log.i("ww", "sin25" + Math.sin(25));

        canvas.clipRect(0, 0, mMeasureWidth, mMeasureHeight / 2 + new Double(-radius * Math.sin(25)).floatValue());

        canvas.drawRect(mRect, mRectPaint);

        //画外圈
        canvas.drawCircle(x, y, radius, mOutsidePaint);

        //画内圈
        canvas.drawCircle(x, y, radius - mPaddingWidth, mInsidePaint);

        canvas.rotate(degree, x, y);

        float pointLeft = 0;
        float pointTop = radius + mMarginWidth;
        //        canvas.drawBitmap(mPointBitmap, mRect.left, y, null);

        canvas.drawPoint(mRect.left, y, mPointPaint);

        float arrowLeft = mMarginWidth + mPaddingWidth;
        float arrowTop = radius + mMarginWidth;
        canvas.drawBitmap(mArrowBitmap, mRect.left + mPaddingWidth + 10, y - 14, null);

        canvas.restore();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(mMeasureHeight, mMeasureWidth), Math.min(mMeasureHeight, mMeasureWidth));
    }

    public void setProgressWidthAnimation(int progress) {

        if (progress > 100) {
            progress = 100;
        }

        if (progress < 0) {
            progress = 0;
        }

        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }

        animator = ValueAnimator.ofInt(mProgress, progress);
        int duration = 10 * Math.abs(progress - mProgress);
        animator.setDuration(duration);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                if (mProgress != value) {
                    mProgress = value;
                    postInvalidate();
                }
            }
        });
        animator.start();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (animator != null) {
            animator.cancel();
            animator = null;
        }

    }

}
