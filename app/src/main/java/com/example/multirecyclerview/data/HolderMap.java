package com.example.multirecyclerview.data;

import com.example.multirecyclerview.holder.FlowHolder;
import com.example.multirecyclerview.holder.Image10Holder;
import com.example.multirecyclerview.holder.Image9Holder;
import com.example.multirecyclerview.holder.ImageHolder;
import com.example.multirecyclerview.holder.TextHolder;

import java.util.ArrayList;
import java.util.List;

public class HolderMap {

    public interface Types {
        String TEXT = "text";
        String IMAGE = "image";
        String FLOW = "flow";
        String IMAGE9 = "image9";
        String IMAGE10 = "image10";
    }

    public static List<HolderModel> maps() {
        return HOLDER.INSTANCE.maps;
    }

    private int index;
    private final List<HolderModel> maps = new ArrayList<>();

    private HolderMap() {
        maps.add(newModel(Types.TEXT, TextHolder.class));
        maps.add(newModel(Types.IMAGE, ImageHolder.class));
        maps.add(newModel(Types.FLOW, FlowHolder.class));
        maps.add(newModel(Types.IMAGE9, Image9Holder.class));
        maps.add(newModel(Types.IMAGE10, Image10Holder.class));
    }

    private static final class HOLDER {
        private static final HolderMap INSTANCE = new HolderMap();
    }

    private HolderModel newModel(String showType, Class clazz) {
        return new HolderModel(showType, index++, clazz);
    }
}
