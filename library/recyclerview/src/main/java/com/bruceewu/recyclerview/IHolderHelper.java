package com.bruceewu.recyclerview;

import android.content.Context;
import android.view.View;

public interface IHolderHelper {
    int ERROR_VIEW_TYPE = -1;
    int ERROR_LAYOUT_ID = -1;

    int getViewType(String showType);

    String getShowType(int viewType);

    void putLayoutId(String showType, int layoutId);

    int getLayoutId(String showType);

    View getFakeView(Context context);

    Class getClazz(String showType);
}
