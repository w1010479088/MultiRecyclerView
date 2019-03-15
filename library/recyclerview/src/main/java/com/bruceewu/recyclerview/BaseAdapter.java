package com.bruceewu.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseHolder<IDisplayItem>> {
    private List<IDisplayItem> mData;
    private IAdapterHelper mAdapterHelper;
    private OnItemClickListener<IDisplayItem> mListener;

    public void setData(IAdapterHelper helper, List<IDisplayItem> data, OnItemClickListener<IDisplayItem> listener) {
        if (helper == null) {
            throw new RuntimeException("BaseAdapter.setData中的helper不能为空!");
        }
        if (data == null) {
            throw new RuntimeException("BaseAdapter.setData中的data不能为空!");
        }

        this.mData = data;
        this.mAdapterHelper = helper;
        this.mListener = listener;

    }

    @NonNull
    @Override
    public BaseHolder<IDisplayItem> onCreateViewHolder(@NonNull ViewGroup viewGroup, int showType) {
        BaseHolder<IDisplayItem> newHolder = mAdapterHelper.newHolder(viewGroup, showType);
        return newHolder == null ? new ErrorHolder(getView(viewGroup, new ErrorHolder(new TextView(viewGroup.getContext())))) : newHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<IDisplayItem> holder, int pos) {
        holder.renderView(getDisplayItem(pos), pos, mListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterHelper.parseViewType(getDisplayItem(position));
    }

    private IDisplayItem getDisplayItem(int pos) {
        return mData == null ? null : mData.get(pos);
    }

    private View getView(ViewGroup viewGroup, BaseHolder holder) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(holder.getLayoutId(), viewGroup, false);
    }
}
