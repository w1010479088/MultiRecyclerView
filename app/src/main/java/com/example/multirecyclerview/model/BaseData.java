package com.example.multirecyclerview.model;

import com.bruceewu.recyclerview.IDisplayItem;

public class BaseData implements IDisplayItem {
    private String showType;
    private String showData;
    private String actionType;
    private String actionData;

    public BaseData(String showType, String showData, String actionType, String actionData) {
        this.showType = showType;
        this.showData = showData;
        this.actionType = actionType;
        this.actionData = actionData;
    }

    @Override
    public String showType() {
        return showType;
    }

    @Override
    public String showData() {
        return showData;
    }

    @Override
    public String actionType() {
        return actionType;
    }

    @Override
    public String actionData() {
        return actionData;
    }
}
