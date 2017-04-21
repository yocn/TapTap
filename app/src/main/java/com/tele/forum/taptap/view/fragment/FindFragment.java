package com.tele.forum.taptap.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tele.forum.taptap.R;
import com.tele.forum.taptap.view.adapter.FindAdapter;
import com.tele.forum.taptap.view.custom.scrollview.ObservableHoriScrollView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yocn on 17.1.7.
 */

public class FindFragment extends BaseFragment {
    View mView;
    TextView tv_introduce;
    ListView lv_scroll;
    private String[] settings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    ObservableHoriScrollView hsv;
    LinearLayout ll_scroll;
    ObservableHoriScrollView hsv2;
    LinearLayout ll_scroll2;
    private String[] mTitles = {"全部", "王者荣耀", "穿越火线", "我的世界", "球球大作战", "天天酷跑", "特战英雄", "火影忍者"};
    View headerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find, null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        initView();
        initData();
        return mView;
    }

    private void initView() {
        tv_introduce = (TextView) mView.findViewById(R.id.tv_introduce);
        lv_scroll = (ListView) mView.findViewById(R.id.lv_scroll);
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_scroll, null);
        hsv = (ObservableHoriScrollView) headerView.findViewById(R.id.hsv);
        ll_scroll = (LinearLayout) headerView.findViewById(R.id.ll_scroll);
        hsv2 = (ObservableHoriScrollView) mView.findViewById(R.id.hsv2);
        ll_scroll2 = (LinearLayout) mView.findViewById(R.id.ll_scroll2);
    }

    private void initData() {
        FindAdapter mFindAdapter = new FindAdapter(settings, getActivity());
        lv_scroll.setAdapter(mFindAdapter);
        initScrollView();
        lv_scroll.addHeaderView(headerView);
        hsv.setScrollViewListener(new ObservableHoriScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHoriScrollView scrollView, int x, int y, int oldx, int oldy) {
                hsv2.scrollTo(x, y);
            }
        });

        hsv2.setScrollViewListener(new ObservableHoriScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHoriScrollView scrollView, int x, int y, int oldx, int oldy) {
                hsv.scrollTo(x, y);
            }
        });
        lv_scroll.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Logger.d("----" + firstVisibleItem);
                if (firstVisibleItem >= 1) {
                    hsv2.setVisibility(View.VISIBLE);
                    hsv2.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
                } else {
                    hsv2.setVisibility(View.INVISIBLE);
                    hsv2.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                }
            }
        });
        exeText();
    }


    private void initScrollView() {
        for (String s : mTitles) {
            //1.集合中每有一条元素，就new一个textView
            TextView tv = new TextView(getActivity());
            //2.把信息设置为文本框的内容
            tv.setText(s);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof TextView) {
                        String s = ((TextView) v).getText().toString();
                        com.orhanobut.logger.Logger.d(s);
                    }
                }
            });
            //3.把textView设置为线性布局的子节点
            ll_scroll.addView(tv);
        }
        for (String s : mTitles) {
            //1.集合中每有一条元素，就new一个textView
            TextView tv = new TextView(getActivity());
            //2.把信息设置为文本框的内容
            tv.setText(s);
            tv.setTextSize(20);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof TextView) {
                        String s = ((TextView) v).getText().toString();
                        com.orhanobut.logger.Logger.d(s);
                    }
                }
            });
            //3.把textView设置为线性布局的子节点
            ll_scroll2.addView(tv);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void exeText() {
        String text = "255645222";
        String s = "2";
        String REGEX = "(" + s + ")*";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        // 若要改变颜色，则需用到SpannableString
        SpannableString sp = new SpannableString(text);
        while (matcher.find()) {
            sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed)), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv_introduce.setText(sp);
    }
}
