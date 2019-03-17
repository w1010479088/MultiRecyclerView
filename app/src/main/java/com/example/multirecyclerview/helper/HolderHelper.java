package com.example.multirecyclerview.helper;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import com.bruceewu.recyclerview.IHolderHelper;
import com.example.multirecyclerview.data.HolderMap;
import com.example.multirecyclerview.data.HolderModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HolderHelper implements IHolderHelper {
    private Map<String, Integer> mShowTypeToViewType = new HashMap<>();
    private Map<String, Integer> mShowTypeToLayoutId = new HashMap<>();
    private Map<String, Class> mShowTypeToClazz = new HashMap<>();
    private SparseArray<String> mViewTypeToShowType = new SparseArray<>();
    private WeakReference<View> mFakeView;

    static HolderHelper getInstance() {
        return HOLDER.instance;
    }

    @Override
    public int getViewType(String showType) {
        Integer viewType = mShowTypeToViewType.get(showType);
        return viewType == null ? IHolderHelper.ERROR_VIEW_TYPE : viewType;
    }

    @Override
    public Class getClazz(String showType) {
        return mShowTypeToClazz.get(showType);
    }

    @Override
    public String getShowType(int viewType) {
        return mViewTypeToShowType.get(viewType);
    }

    @Override
    public int getLayoutId(String showType) {
        Integer layoutId = mShowTypeToLayoutId.get(showType);
        return layoutId == null ? IHolderHelper.ERROR_LAYOUT_ID : layoutId;
    }

    @Override
    public void putLayoutId(String showType, int layoutId) {
        mShowTypeToLayoutId.put(showType, layoutId);
    }

    @Override
    public View getFakeView(Context context) {
        if (mFakeView == null || mFakeView.get() == null) {
            View tempView = new View(context);
            mFakeView = new WeakReference<>(tempView);
        }
        return mFakeView.get();
    }

    private HolderHelper() {
        initMaps();
    }

    private static final class HOLDER {
        private static HolderHelper instance = new HolderHelper();
    }

    private void initMaps() {
        List<HolderModel> maps = HolderMap.maps();
        for (HolderModel item : maps) {
            mShowTypeToViewType.put(item.showType(), item.viewType());
            mViewTypeToShowType.put(item.viewType(), item.showType());
            mShowTypeToClazz.put(item.showType(), item.clazz());
        }
    }
}
