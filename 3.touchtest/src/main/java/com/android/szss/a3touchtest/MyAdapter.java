package com.android.szss.a3touchtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @Description:
 * @author：鼠茂斯
 * @date：2018/1/20
 */

public class MyAdapter extends RecyclerView.Adapter {

    private int[] images = {R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image17, R.drawable.image16};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int imageRes = images[(int) (Math.random() * images.length)];
        ((ImageViewHolder) holder).mImageView.setImageResource(imageRes);
    }

    @Override
    public int getItemCount() {
        return 3000;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }

}
