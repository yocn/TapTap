package com.tele.forum.taptap.view.custom.swiperefreshendless;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class
            .getSimpleName();

    private boolean loading;
    int lastCompletelyVisiableItemPosition, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLinearLayoutManager;
    private boolean isHasFooter;

    public EndlessRecyclerOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        isHasFooter = true;
        loading = true;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        lastCompletelyVisiableItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
        //  System.out.println(loading + "   visibleItemCount:" + visibleItemCount + "totalItemCount:" + totalItemCount + "lastCompletelyVisiableItemPosition:" + lastCompletelyVisiableItemPosition);
        if (!loading
                && (visibleItemCount > 0)
                && (lastCompletelyVisiableItemPosition >= totalItemCount - 1)) {
            if (!isHasFooter) {
                onAddFooter();
                isHasFooter = true;
            }
            onLoadMore();
            loading = true;
        }
    }

    public void loadComplete() {
        if (visibleItemCount > 0 && visibleItemCount == totalItemCount && isHasFooter) {
            onDeleteFooter();
            isHasFooter = false;
        }
        loading = false;
    }

    public abstract void onLoadMore();

    public abstract void onDeleteFooter();

    public abstract void onAddFooter();
}