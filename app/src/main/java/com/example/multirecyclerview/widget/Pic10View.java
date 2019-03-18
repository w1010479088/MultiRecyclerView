package com.example.multirecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.multirecyclerview.R;

public class Pic10View extends ViewGroup {
    private static final int MAX_COUNT = 10;
    private static final int NORMAL_COUNT = 4;
    private int count;
    private float density;
    private int divider;
    private int screenWidth;
    private int itemWidth;
    private int itemHeight;

    public Pic10View(Context context) {
        this(context, null);
    }

    public Pic10View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pic10View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config();
    }

    private void config() {
        count = MAX_COUNT;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density = dm.density;
        screenWidth = dm.widthPixels;
        divider = dp2px(10);
        itemHeight = (int) (screenWidth * (9.0F / 16.0F) + 0.5F);
        itemWidth = (screenWidth - divider) / 2;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < MAX_COUNT; i++) {
            ImageView child = new ImageView(getContext());
            child.setScaleType(ImageView.ScaleType.CENTER_CROP);
            child.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
            addView(child);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //child
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int width = normalSize(i) ? screenWidth : itemWidth;
            LayoutParams params = new LayoutParams(width, itemHeight);
            child.setLayoutParams(params);
        }

        //parent
        int height;
        if (count > NORMAL_COUNT) {
            int moreRow = (count - (NORMAL_COUNT + 1)) / 2 + 1;
            height = (NORMAL_COUNT + moreRow) * itemHeight + (NORMAL_COUNT + moreRow - 1) * divider;
        } else {
            height = count * itemHeight + (count - 1) * divider;
        }
        setMeasuredDimension(screenWidth, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (normalSize(i)) {
                    int top = i * itemHeight + i * divider;
                    int bottom = (i + 1) * itemHeight + i * divider;
                    child.layout(0, top, screenWidth, bottom);
                } else {
                    int row = NORMAL_COUNT + (i - NORMAL_COUNT) / 2;
                    boolean leftItem = left(i);
                    int left = leftItem ? 0 : (screenWidth - itemWidth);
                    int top = row * (itemHeight + divider);
                    int right = left + itemWidth;
                    int bottom = top + itemHeight;
                    child.layout(left, top, right, bottom);
                }
            }
        }
    }

    private boolean normalSize(int pos) {
        return count <= NORMAL_COUNT || pos < NORMAL_COUNT;
    }

    private boolean left(int pos) {
        return pos % 2 == 0;
    }

    private int dp2px(int dpValue) {
        return (int) (dpValue * density + 0.5f);
    }
}
