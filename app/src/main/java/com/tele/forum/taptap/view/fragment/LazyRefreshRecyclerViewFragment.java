package com.tele.forum.taptap.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.view.custom.swiperefreshendless.EndlessRecyclerOnScrollListener;


/**
 * Created by Yocn on 2016/2/15.
 * 有上拉加载下拉刷新的RecyclerViewFragment的基类
 */
public abstract class LazyRefreshRecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected View loadMoreView;
    protected TextView tv_load_complete;
    ProgressBar load_progress_bar;
    protected EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    protected boolean isCloseLoadMore;

    protected void initRecyclerView(View mRootView) {

        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadMoreView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.view_load_more, recyclerView, false);


        load_progress_bar = (ProgressBar) loadMoreView.findViewById(R.id.load_progress_bar);
        tv_load_complete = (TextView) loadMoreView.findViewById(R.id.tv_load_complete);
        swipeRefreshLayout.setOnRefreshListener(this);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                onLoadMoreData();
            }

            @Override
            public void onDeleteFooter() {
                System.out.println("deleteFooter----------");
//                ViewGroup vg = (ViewGroup) tv_load_complete.getParent();
//                vg.setVisibility(View.GONE);
//                tv_load_complete.setVisibility(View.GONE);
//                load_progress_bar.setVisibility(View.GONE);
            }

            @Override
            public void onAddFooter() {
                System.out.println("addFooter------------------");
//                ViewGroup vg = (ViewGroup) tv_load_complete.getParent();
//                vg.setVisibility(View.VISIBLE);
//                load_progress_bar.setVisibility(View.VISIBLE);
//                tv_load_complete.setVisibility(View.INVISIBLE);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);

    }

    /**
     * load完成之后，调用
     *
     * @param loadComplete 还有没有更多数据
     */
    public void loadComplete(boolean loadComplete) {
        endlessRecyclerOnScrollListener.loadComplete();
        if (loadComplete) {
            /**加载完成*/
            tv_load_complete.setVisibility(View.VISIBLE);
            load_progress_bar.setVisibility(View.INVISIBLE);
        } else {
            tv_load_complete.setVisibility(View.INVISIBLE);
            load_progress_bar.setVisibility(View.VISIBLE);
        }
    }

    public void setLoadMoreViewText(String tips){
        tv_load_complete.setText(tips);
    }
    protected abstract void onLoadMoreData();

//    @Override
//    public void onRefresh() {
//    }
}
