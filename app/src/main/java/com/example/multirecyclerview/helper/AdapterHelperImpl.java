package com.example.multirecyclerview.helper;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bruceewu.recyclerview.BaseHolder;
import com.bruceewu.recyclerview.IAdapterHelper;
import com.bruceewu.recyclerview.IDisplayItem;

import java.lang.reflect.Constructor;

public class AdapterHelperImpl implements IAdapterHelper {
    @Override
    public int parseViewType(IDisplayItem displayItem) {
        return HolderHelper.getInstance().getViewType(displayItem.showType());
    }

    @Override
    public BaseHolder<IDisplayItem> newHolder(@NonNull ViewGroup viewGroup, int viewType) {
        HolderHelper helper = HolderHelper.getInstance();
        String showType = helper.getShowType(viewType);
        Class clazz = helper.getClazz(showType);
        return newHolder(clazz, viewGroup);
    }

    private BaseHolder newHolder(Class<IDisplayItem> clazz, ViewGroup viewGroup) {
        BaseHolder<IDisplayItem> holder = null;
        View fakeView = new View(viewGroup.getContext());
        try {
            Constructor constructor = clazz.getDeclaredConstructor(View.class);
            BaseHolder<IDisplayItem> tempHolder = (BaseHolder<IDisplayItem>) constructor.newInstance(fakeView);
            holder = (BaseHolder<IDisplayItem>) constructor.newInstance(getView(viewGroup, tempHolder.getLayoutId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }


    private View getView(ViewGroup viewGroup, @LayoutRes int layoutId) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
    }
}
