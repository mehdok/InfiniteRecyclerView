package com.mehdok.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author mehdok (mehdok@gmail.com) on 5/12/2017.
 */

public class ScrollSensitiveRecyclerView extends RecyclerView {
    private UiToggleListener uiToggleListener;

    public ScrollSensitiveRecyclerView(Context context) {
        super(context);
        init();
    }

    public ScrollSensitiveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollSensitiveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                onViewScrolled(recyclerView, dx, dy);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    protected void onViewScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (uiToggleListener != null) {
            if (dy > 0) {
                uiToggleListener.hideUI();
            } else {
                uiToggleListener.showUI();
            }
        }
    }

    public void setUiToggleListener(
            UiToggleListener uiToggleListener) {
        this.uiToggleListener = uiToggleListener;
    }

    public void removeUiToggleListener() {
        uiToggleListener = null;
    }
}
