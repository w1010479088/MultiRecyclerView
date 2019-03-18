package com.example.multirecyclerview.holder;

import android.support.annotation.NonNull;
import android.view.View;

import com.bruceewu.recyclerview.BaseHolder;
import com.bruceewu.recyclerview.IDisplayItem;
import com.bruceewu.recyclerview.OnItemClickListener;
import com.example.multirecyclerview.R;

public class FlowHolder extends BaseHolder<IDisplayItem> {

    public FlowHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_flow;
    }

    @Override
    public void renderView(IDisplayItem data, int pos, OnItemClickListener listener) {

    }
}
