package com.example.multirecyclerview;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bruceewu.recyclerview.BaseAdapter;
import com.bruceewu.recyclerview.IDisplayItem;
import com.bruceewu.recyclerview.OnItemClickListener;
import com.example.multirecyclerview.data.HolderMap;
import com.example.multirecyclerview.helper.AdapterHelperImpl;
import com.example.multirecyclerview.model.BaseData;

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
        final RecyclerView recyclerView = getView(R.id.list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (layoutManager != null) {
                    int position = layoutManager.findFirstVisibleItemPosition();
                }
            }
        });
        BaseAdapter adapter = new BaseAdapter();
        List<IDisplayItem> data = newTestData();
        adapter.setData(new AdapterHelperImpl(), data, mListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    OnItemClickListener<IDisplayItem> mListener = new OnItemClickListener<IDisplayItem>() {
        @Override
        public void onClick(IDisplayItem data, int pos) {

        }

        @Override
        public void onLongClick(IDisplayItem data, int pos) {

        }
    };

    private List<IDisplayItem> newTestData() {
        List<IDisplayItem> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new BaseData(HolderMap.Types.TEXT, "大俊子" + i, null, null));
            data.add(new BaseData(HolderMap.Types.IMAGE, "大俊子" + i, null, null));
            data.add(new BaseData(HolderMap.Types.IMAGE9, "大俊子" + i, null, null));
            data.add(new BaseData(HolderMap.Types.IMAGE10, "大俊子" + i, null, null));
        }
        return data;
    }

    private <T extends View> T getView(@IdRes int id) {
        return getWindow().findViewById(id);
    }

    private void log(String content) {
        System.out.println("--->" + content);
    }
}

