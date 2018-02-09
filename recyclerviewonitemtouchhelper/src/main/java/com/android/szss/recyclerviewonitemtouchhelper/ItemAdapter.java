package com.android.szss.recyclerviewonitemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @Description:
 * @author： 鼠茂斯
 * @date： 2018/2/9
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements ItemTouchHelperAdapterCallback {


    private ArrayList<String> mDatas = new ArrayList<>();

    public ItemAdapter() {

        for (int i = 0; i < 100; i++) {
            mDatas.add("RecyclerViewOnItemTouchHelper" + i);
        }

    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ItemViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //让数据集合中的两个数据进行位置交换
        Collections.swap(mDatas, fromPosition, toPosition);
        //同时还要刷新RecyclerView
        //		notifyDataSetChanged();
        notifyItemMoved(fromPosition, toPosition);

        return false;
    }

    @Override
    public void onItemSwiped(int adapterPosition) {
        //1.删除数据集合里面的position位置的数据
        mDatas.remove(adapterPosition);
        //2.刷新adapter
        notifyItemRemoved(adapterPosition);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

}
