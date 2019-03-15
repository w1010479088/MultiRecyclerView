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

    private static final List<HolderModel> maps = new ArrayList<>();
    private static int index;

    private HolderMap() {
    }

    static {
        maps.add(newModel(Types.TEXT, TextHolder.class));
        maps.add(newModel(Types.IMAGE, ImageHolder.class));
    }

    private static HolderModel newModel(String showType, Class clazz) {
        return new HolderModel(showType, index++, clazz);
    }

    public static List<HolderModel> maps() {
        return maps;
    }
}
