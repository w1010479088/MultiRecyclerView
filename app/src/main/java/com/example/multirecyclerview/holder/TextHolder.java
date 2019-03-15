package com.example.multirecyclerview.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bruceewu.recyclerview.BaseHolder;
import com.bruceewu.recyclerview.IDisplayItem;
import com.bruceewu.recyclerview.OnItemClickListener;
import com.example.multirecyclerview.R;

public class TextHolder extends BaseHolder<IDisplayItem> {

    public TextHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.holder_text;
    }

    @Override
    public void renderView(IDisplayItem data, int pos, OnItemClickListener listener) {
        TextView textView = itemView.findViewById(R.id.content);
        textView.setText(data.showData());
    }
}
