package com.bruceewu.recyclerview;

public interface OnItemClickListener<T extends IDisplayItem> {

    void onClick(T data, int pos);

    void onLongClick(T data, int pos);
}
