package com.fluid.customization.utils;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class LinearLayoutManagerAccurateOffset extends LinearLayoutManager {

    private HashMap<Integer, Integer> mChildSizesMap;

    public LinearLayoutManagerAccurateOffset(Context context, int layoutType, boolean reverseLayout) {
        super(context, layoutType, reverseLayout);
        mChildSizesMap = new HashMap<>();
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            mChildSizesMap.put(getPosition(child), child.getHeight());
        }

    }

    @Override
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }

        View firstChild = getChildAt(0);
        int firstChildPosition = getPosition(firstChild);
        int scrolledY = -(int) firstChild.getY();
        for (int i = 0; i < firstChildPosition && i < mChildSizesMap.size(); i++) {
            scrolledY += mChildSizesMap.get(i);
        }

        return scrolledY;
    }
}
