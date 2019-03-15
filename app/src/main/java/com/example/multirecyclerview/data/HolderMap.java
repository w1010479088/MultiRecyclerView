package com.example.multirecyclerview.data;

import com.example.multirecyclerview.holder.ImageHolder;
import com.example.multirecyclerview.holder.TextHolder;

import java.util.ArrayList;
import java.util.List;

public class HolderMap {

    public interface Types {
        String TEXT = "text";
        String IMAGE = "image";
    }

    public static List<HolderModel> maps() {
        return HOLDER.INSTANCE.maps;
    }

    private int index;
    private final List<HolderModel> maps = new ArrayList<>();

    private HolderMap() {
        maps.add(newModel(Types.TEXT, TextHolder.class));
        maps.add(newModel(Types.IMAGE, ImageHolder.class));
    }

    private static final class HOLDER {
        private static final HolderMap INSTANCE = new HolderMap();
    }

    private HolderModel newModel(String showType, Class clazz) {
        return new HolderModel(showType, index++, clazz);
    }
}
