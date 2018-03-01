package com.android.szss.a6propertyanimation;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = new File(Environment.getExternalStorageDirectory(),"tracefile");
        Log.i("ww", "oncreate: " + file.getAbsolutePath());
        Debug.startMethodTracing("/sdcard/tracefile");

        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 2f, 1f);
                valueAnimator.setRepeatCount(3);
                valueAnimator.setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        tv.setScaleX(value);
                        tv.setScaleY(value);
                    }
                });
                valueAnimator.start();
            }
        });

        iv = findViewById(R.id.imageview);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(1500);
                valueAnimator.setObjectValues(new PointF(0, 0));

                final PointF pointF = new PointF();

                //颜色估值器
                valueAnimator.setEvaluator(new ArgbEvaluator());

                //估值器
                valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
                    @Override
                    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

                        // x = vt
                        pointF.x = 100f * (fraction * 5);
                        // y = 1/2 g t*t
                        pointF.y = 0.5f * 60f * (fraction * 5) * (fraction * 5);

                        return pointF;
                    }
                });

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF1 = (PointF) animation.getAnimatedValue();
                        iv.setTranslationX(pointF1.x);
                        iv.setTranslationY(pointF1.y);
                    }
                });

                //interpolator 插值器
                valueAnimator.setInterpolator(new BounceInterpolator());

                valueAnimator.start();

                Debug.stopMethodTracing();

            }
        });

    }
}
