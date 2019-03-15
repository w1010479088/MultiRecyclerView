package com.bruceewu.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T extends IDisplayItem> extends RecyclerView.ViewHolder {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract int getLayoutId();

    public abstract void renderView(T data, int pos, OnItemClickListener listener);
}
