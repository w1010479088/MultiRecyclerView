package com.bruceewu.recyclerview;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

public interface IAdapterHelper {

    int parseViewType(IDisplayItem displayItem);

    BaseHolder<IDisplayItem> newHolder(@NonNull ViewGroup viewGroup, int viewType);
}
