package com.mehdok.infiniterecyclerviewexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.mehdok.views.InfiniteRecyclerView;
import com.mehdok.views.InfiniteScrollListener;
import com.mehdok.views.UiToggleListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InfiniteScrollListener, UiToggleListener {
    private TestAdapter adapter;
    private InfiniteRecyclerView testRV;
    private FloatingActionButton fab;
    private ProgressBar progressBar;

    // a flag for prevent calling loadMore() when there is another loading is in progress
    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        testRV = (InfiniteRecyclerView) findViewById(R.id.recycler_view);
        testRV.setLayoutManager(new LinearLayoutManager(this));
        testRV.setInfiniteScrollListener(this);
        testRV.setUiToggleListener(this);
        adapter = new TestAdapter(getListOf10String(0));
        testRV.setAdapter(adapter);
    }

    /**
     * This is just for demonstration propose, you can implement your own function for getting more data,
     * eg calling a web service for getting more data
     *
     * @param start
     * @return
     */
    private List<String> getListOf10String(int start) {
        List<String> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            result.add("content " + start++);
        }

        return result;
    }

    @Override
    public void loadMoreContent() {
        if (loading) return;

        loading = true;
        showProgress(true);

        // create a delay just for example of working progress
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMore();
            }
        }, 1500);
    }

    private void loadMore() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addMoreItem(getListOf10String(adapter.getItemCount()));
                showProgress(false);
                loading = false;
            }
        });
    }

    @Override
    public void showUI() {
        if (fab.getVisibility() != View.VISIBLE) {
            fab.show();
        }
    }

    @Override
    public void hideUI() {
        if (fab.getVisibility() == View.VISIBLE) {
            fab.hide();
        }
    }

    private void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        testRV.removeInfiniteScrollListener();
        testRV.removeUiToggleListener();
    }
}
