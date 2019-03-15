package com.example.multirecyclerview.helper;

import android.util.SparseArray;

import com.example.multirecyclerview.data.HolderMap;
import com.example.multirecyclerview.data.HolderModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HolderHelper {
    private Map<String, Integer> mShowTypeToViewType = new HashMap<>();
    private Map<String, Class> mShowTypeToClazz = new HashMap<>();
    private SparseArray<String> mViewTypeToShowType = new SparseArray<>();

    public static HolderHelper getInstance() {
        return HOLDER.instance;
    }

    public int getViewType(String showType) {
        Integer viewType = mShowTypeToViewType.get(showType);
        return viewType == null ? -1 : viewType;
    }

    public Class getClazz(String showType) {
        return mShowTypeToClazz.get(showType);
    }

    public String getShowType(int viewType) {
        return mViewTypeToShowType.get(viewType);
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
