package com.android.szss.a3touchtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerView1;
    @BindView(R.id.recyclerview2)
    RecyclerView mRecyclerView2;
    @BindView(R.id.recyclerview3)
    RecyclerView mRecyclerView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.setAdapter(new MyAdapter());

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setAdapter(new MyAdapter());

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView3.setAdapter(new MyAdapter());
    }

}
