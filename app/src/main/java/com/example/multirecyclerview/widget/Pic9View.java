package com.example.multirecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.multirecyclerview.R;

public class Pic9View extends ViewGroup {
    private static final int MAX_COUNT = 9;
    private float density;
    private int divider;
    private int width;
    private int itemWidth;

    public Pic9View(Context context) {
        this(context, null);
    }

    public Pic9View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pic9View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density = dm.density;
        width = dm.widthPixels;
        divider = dp2px(10);
        itemWidth = (width - 2 * divider) / 3;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < MAX_COUNT; i++) {
            ImageView view = new ImageView(getContext());
            view.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
            addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams params = new LayoutParams(itemWidth, itemWidth);
            child.setLayoutParams(params);
        }
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            int pointerX = l + itemWidth * (i % 3) + divider * (i % 3);
            int pointerY = t + itemWidth * (i / 3) + divider * (i / 3);
            View child = getChildAt(i);
            child.layout(pointerX, pointerY, pointerX + itemWidth, pointerY + itemWidth);
        }
    }

    private int dp2px(int dpValue) {
        return (int) (dpValue * density + 0.5f);
    }
}
