package com.example.multirecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multirecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class FlowView extends ViewGroup {
    private static final int TEST_COUNT = 20;
    private float density;
    private int horizontalDivider;
    private int verticalDivider;
    private int itemHeight;
    private int screenWidth;
    private List<String> data = new ArrayList<>();

    public FlowView(Context context) {
        this(context, null);
    }

    public FlowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config();
    }

    private void config() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density = dm.density;
        screenWidth = dm.widthPixels;
        verticalDivider = dp2px(10);
        horizontalDivider = dp2px(5);
        for (int i = 0; i < TEST_COUNT / 2; i++) {
            data.add("大俊子" + i);
            data.add("我来了哈,不要急" + i);
            data.add("Start! 我来也!");
            data.add("我来了哈,不要急" + i);
            data.add("哈!");
            data.add("Game Over!");
            data.add("Start! 我来也!");
            data.add("Game Over!");
            data.add("哈!");
            data.add("我来了哈,不要急" + i);
            data.add("我来了哈,不要着急不要着急!" + i);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int paddingHorizontal = dp2px(5);
        int paddingVertical = dp2px(3);
        for (int i = 0; i < TEST_COUNT; i++) {
            TextView child = new TextView(getContext());
            child.setText(data.get(i));
            child.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            child.setBackground(getResources().getDrawable(R.drawable.btn_frame_grey_r5));
            addView(child);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //measure child
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

        //measure parent
        int row = 1;
        int widthUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == 0) {
                itemHeight = child.getMeasuredHeight();
            }
            int childWidth = child.getMeasuredWidth();
            int childWidthWithMargin = childWidth + horizontalDivider;
            int tempWidth = widthUsed + childWidth;
            if (tempWidth > screenWidth) {
                row++;
                widthUsed = childWidthWithMargin;
            } else {
                widthUsed += childWidthWithMargin;
            }
        }

        //set parent
        setMeasuredDimension(screenWidth, row * (itemHeight + verticalDivider));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int row = 1;
        int widthUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int tempUsedWidth = widthUsed + childWidth;
            if (tempUsedWidth > screenWidth) {
                row++;
                widthUsed = 0;
            }
            int left = widthUsed;
            int top = (row - 1) * (itemHeight + verticalDivider);
            int right = left + childWidth;
            int bottom = top + itemHeight;
            child.layout(left, top, right, bottom);

            widthUsed += (childWidth + horizontalDivider);
        }
    }

    private int dp2px(int dpValue) {
        return (int) (dpValue * density + 0.5f);
    }
}
