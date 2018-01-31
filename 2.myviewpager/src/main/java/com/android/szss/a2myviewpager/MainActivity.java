package com.android.szss.a2myviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.myviewpager)
    MyViewPager mMyViewPager;

    private int[] imageIds = {R.drawable.launch_page1, R.drawable.launch_page2, R.drawable.launch_page3, R.drawable.launch_page4, R.drawable.launch_page5, R.drawable.launch_page6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            mMyViewPager.addView(imageView);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.item_test, null);
        mMyViewPager.addView(view, 2);

    }
}
