package com.tele.forum.taptap.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.activity.MainActivity;
import com.tele.forum.taptap.view.adapter.RecommendAdapter;
import com.tele.forum.taptap.view.custom.swiperefreshendless.HeaderViewRecyclerAdapter;

/**
 * Created by Yocn on 17.1.7.
 * 推荐
 */

public class RecommendFragment extends LazyRefreshRecyclerViewFragment {
    View mView;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommend, null);
        mContext = getActivity();
        initViews(mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initViews(View mRootView) {
        initRecyclerView(mRootView);
        setLoadMoreViewText("没有更多的直播...");
        RecommendAdapter mAdapter = new RecommendAdapter(mContext);
        HeaderViewRecyclerAdapter mRecyclerViewAdapter = new HeaderViewRecyclerAdapter(mAdapter);
        mRecyclerViewAdapter.addFooterView(loadMoreView);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        recyclerView.addOnScrollListener(mScrollListener);
//        gotoHotPage = (TextView) mRootView.findViewById(R.id.fragment_attention_gotohotpage);
//        gotoHotPage.setOnClickListener(this);
    }

    @Override
    protected void onLoadMoreData() {

    }

    @Override
    public void onRefresh() {

    }

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) {
                ((MainActivity)getActivity()).dismissTopAndBottom();
            }
            if (dy < 0) {
                ((MainActivity)getActivity()).showTopAndBottom();
            }
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    };
}
