package com.example.multirecyclerview.holder;

import android.support.annotation.NonNull;
import android.view.View;

import com.bruceewu.recyclerview.BaseHolder;
import com.bruceewu.recyclerview.IDisplayItem;
import com.bruceewu.recyclerview.OnItemClickListener;
import com.example.multirecyclerview.R;

public class Image10Holder extends BaseHolder<IDisplayItem> {

    public Image10Holder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_image10;
    }

    @Override
    public void renderView(IDisplayItem data, int pos, OnItemClickListener listener) {

    }
}
