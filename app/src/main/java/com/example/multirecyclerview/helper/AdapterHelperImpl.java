package com.example.multirecyclerview.helper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bruceewu.recyclerview.BaseHolder;
import com.bruceewu.recyclerview.IAdapterHelper;
import com.bruceewu.recyclerview.IDisplayItem;
import com.bruceewu.recyclerview.IHolderHelper;

import java.lang.reflect.Constructor;

public class AdapterHelperImpl implements IAdapterHelper {

    @Override
    public int parseViewType(IDisplayItem displayItem) {
        return getHolderHelper().getViewType(displayItem.showType());
    }

    @Override
    public BaseHolder<IDisplayItem> newHolder(@NonNull ViewGroup viewGroup, int viewType) {
        IHolderHelper helper = getHolderHelper();
        String showType = helper.getShowType(viewType);
        Class clazz = helper.getClazz(showType);
        return newHolder(showType, clazz, viewGroup);
    }

    private BaseHolder newHolder(String showType, Class clazz, ViewGroup viewGroup) {
        BaseHolder<IDisplayItem> holder = null;
        try {
            Constructor constructor = clazz.getDeclaredConstructor(View.class);
            int layoutId = getLayoutId(viewGroup.getContext(), showType, constructor);
            holder = (BaseHolder<IDisplayItem>) constructor.newInstance(getView(viewGroup, layoutId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }

    private View getView(ViewGroup viewGroup, @LayoutRes int layoutId) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
    }

    private IHolderHelper getHolderHelper() {
        return HolderHelper.getInstance();
    }

    private int getLayoutId(Context context, String showType, Constructor constructor) {
        IHolderHelper holderHelper = getHolderHelper();
        View fakeView = holderHelper.getFakeView(context);
        int layoutId = holderHelper.getLayoutId(showType);
        try {
            if (layoutId == IHolderHelper.ERROR_LAYOUT_ID) {
                BaseHolder<IDisplayItem> tempHolder = (BaseHolder<IDisplayItem>) constructor.newInstance(fakeView);
                layoutId = tempHolder.getLayoutId();
                holderHelper.putLayoutId(showType, layoutId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return layoutId;
    }
}
