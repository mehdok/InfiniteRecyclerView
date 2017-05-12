package com.mehdok.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author mehdok (mehdok@gmail.com) on 5/12/2017.
 */

public class InfiniteRecyclerView extends ScrollSensitiveRecyclerView {
    private float SCROLL_AMOUNT = 3 / 4f;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private InfiniteScrollListener infiniteScrollListener;

    public InfiniteRecyclerView(Context context) {
        super(context);
    }

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getTriggerAmount(context, attrs);
    }

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getTriggerAmount(context, attrs);
    }

    private void getTriggerAmount(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfiniteRecyclerViewAttrs);

        SCROLL_AMOUNT = typedArray.getFloat(R.styleable.InfiniteRecyclerViewAttrs_triggerAmount, SCROLL_AMOUNT);

        typedArray.recycle();
    }

    @Override
    protected void onViewScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onViewScrolled(recyclerView, dx, dy);

        // if user scroll upward don't ask for more content
        if (dy < 0) return;

        visibleItemCount = getLayoutManager().getChildCount();
        totalItemCount = getLayoutManager().getItemCount();
        pastVisibleItems = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        if ((infiniteScrollListener != null) &&
                ((visibleItemCount + pastVisibleItems) >= totalItemCount * SCROLL_AMOUNT)) {
            infiniteScrollListener.loadMoreContent();
        }
    }

    public void setInfiniteScrollListener(InfiniteScrollListener infiniteScrollListener) {
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public void removeInfiniteScrollListener() {
        infiniteScrollListener = null;
    }
}
