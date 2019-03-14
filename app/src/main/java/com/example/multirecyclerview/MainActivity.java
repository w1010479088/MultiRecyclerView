package com.example.multirecyclerview;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configRecyclerView();
    }

    private void configRecyclerView() {
        RecyclerView recyclerView = getView(R.id.list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        NormalAdapter adapter = new NormalAdapter();
        List<DisplayItem> data = newTestData();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<DisplayItem> newTestData() {
        List<DisplayItem> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(new BaseData(DisplayItem.TYPE_TEXT, "大俊子" + i, null, null));
            data.add(new BaseData(DisplayItem.TYPE_IMAGE, "大俊子" + i, null, null));
        }
        return data;
    }

    private <T extends View> T getView(@IdRes int id) {
        return getWindow().findViewById(id);
    }
}

class NormalAdapter extends RecyclerView.Adapter<BaseHolder<DisplayItem>> {
    private List<DisplayItem> mData;
    private OnItemClickListener<DisplayItem> mListener;

    public void setData(List<DisplayItem> data) {
        this.mData = data;
    }

    public void onClickListener(OnItemClickListener<DisplayItem> listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseHolder<DisplayItem> onCreateViewHolder(@NonNull ViewGroup viewGroup, int showType) {
        BaseHolder holder;
        TextView fakeView = new TextView(viewGroup.getContext());
        switch (showType) {
            case DisplayItem.TYPE_TEXT:
                holder = new TextHolder(getView(viewGroup, new TextHolder(fakeView)));
                break;
            case DisplayItem.TYPE_IMAGE:
                holder = new ImageHolder(getView(viewGroup, new ImageHolder(fakeView)));
                break;
            default:
                holder = new ErrorHolder(getView(viewGroup, new ErrorHolder(fakeView)));
                break;
        }
        return holder;
    }

    private View getView(ViewGroup viewGroup, BaseHolder holder) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(holder.getLayoutId(), viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<DisplayItem> holder, int pos) {
        holder.renderView(getDisplayItem(pos), pos, mListener);
    }

    private DisplayItem getDisplayItem(int pos) {
        return mData == null ? null : mData.get(pos);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        DisplayItem displayItem = getDisplayItem(position);
        return displayItem == null ? -1 : displayItem.showType();
    }
}

//----------------------------我是分割线----------------------------
abstract class BaseHolder<T extends DisplayItem> extends RecyclerView.ViewHolder {

    BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract @LayoutRes
    int getLayoutId();

    abstract void renderView(T data, int pos, OnItemClickListener listener);
}


class TextHolder extends BaseHolder<DisplayItem> {

    public TextHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    int getLayoutId() {
        return R.layout.holder_text;
    }

    @Override
    void renderView(DisplayItem data, int pos, OnItemClickListener listener) {
        TextView textView = itemView.findViewById(R.id.content);
        textView.setText(data.showData());
    }
}

class ImageHolder extends BaseHolder<DisplayItem> {

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    int getLayoutId() {
        return R.layout.holder_image;
    }

    @Override
    void renderView(DisplayItem data, int pos, OnItemClickListener listener) {

    }
}

class ErrorHolder extends BaseHolder<DisplayItem> {

    ErrorHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    int getLayoutId() {
        return R.layout.holder_error;
    }

    @Override
    void renderView(DisplayItem data, int pos, OnItemClickListener listener) {

    }
}


interface DisplayItem {
    int TYPE_TEXT = 1;
    int TYPE_IMAGE = 2;

    int showType();

    String showData();

    String actionType();

    String actionData();
}

class BaseData implements DisplayItem {
    private int showType;
    private String showData;
    private String actionType;
    private String actionData;

    public BaseData() {

    }

    public BaseData(int showType, String showData, String actionType, String actionData) {
        this.showType = showType;
        this.showData = showData;
        this.actionType = actionType;
        this.actionData = actionData;
    }

    @Override
    public int showType() {
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


interface OnItemClickListener<T extends DisplayItem> {

    void onClick(T data, int pos);

    void onLongClick(T data, int pos);
}