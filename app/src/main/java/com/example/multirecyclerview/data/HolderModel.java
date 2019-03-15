package com.example.multirecyclerview.data;

public class HolderModel {
    private String showType;
    private int viewType;
    private Class clazz;

    public HolderModel(String showType, int viewType, Class clazz) {
        this.showType = showType;
        this.viewType = viewType;
        this.clazz = clazz;
    }

    public String showType() {
        return showType;
    }

    public int viewType() {
        return viewType;
    }

    public Class clazz() {
        return clazz;
    }
}
