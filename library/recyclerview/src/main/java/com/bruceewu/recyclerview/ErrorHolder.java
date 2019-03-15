package com.bruceewu.recyclerview;


import android.support.annotation.NonNull;
import android.view.View;

public class ErrorHolder extends BaseHolder<IDisplayItem> {

    public ErrorHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_error;
    }

    @Override
    public void renderView(IDisplayItem data, int pos, OnItemClickListener listener) {

    }
}
