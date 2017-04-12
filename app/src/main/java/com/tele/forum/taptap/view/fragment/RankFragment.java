package com.tele.forum.taptap.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.DisplayUtils;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.activity.MainActivity;
import com.tele.forum.taptap.view.custom.scrollview.ObservableScrollView;

/**
 * Created by Yocn on 17.1.7.
 * 排行
 */

public class RankFragment extends BaseFragment {
    View mView;
    RelativeLayout rl_top;
    RelativeLayout rl_bottom;
    ObservableScrollView sv_all;
    WebView wv_test;
    private int height = 0;
    private int mScreenHeight = 0;
    private int mStatusBarHeight = 0;
    private boolean canScroll = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_rank, null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        initView();
        initData();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        sv_all = (ObservableScrollView) mView.findViewById(R.id.sv_all);
        rl_top = (RelativeLayout) mView.findViewById(R.id.rl_top);
        rl_bottom = (RelativeLayout) mView.findViewById(R.id.rl_bottom);
        wv_test = (WebView) mView.findViewById(R.id.wv_test);
    }

    private void initData() {
        mScreenHeight = DisplayUtils.getScreenHeight(getActivity());
        mStatusBarHeight = DisplayUtils.getStatusBarHeight(getActivity());
        height = DisplayUtils.dip2px(getActivity(), 700);
        wv_test.loadUrl("http://www.baidu.com");
        wv_test.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //开始
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //结束
                super.onPageStarted(view, url, favicon);
            }
        });
        sv_all.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case KeyEvent.ACTION_UP: {
                        //按住事件发生后执行代码的区域
                        Loger.d("onTouch------up--------");
                        if (canScroll) {
                            sv_all.scrollTo(0, height);
                        }
                        break;
                    }
                    case KeyEvent.ACTION_DOWN: {
                        //松开事件发生后执行代码的区域
//                        if (canScroll) {
//                            sv_all.scrollTo(0, height);
//                        }
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
        sv_all.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, final int y, int oldx, int oldy) {
                int dy = y - oldy;
                if (dy > 0) {
                    ((MainActivity) getActivity()).dismissTopAndBottom();
                } else {
                    ((MainActivity) getActivity()).showTopAndBottom();
                }

                int[] positionWindow = new int[2];
                rl_top.getLocationInWindow(positionWindow);
                if (Math.abs(positionWindow[1] - mScreenHeight) > height) {
                    /**当前坐标大于top的高度*/
                    Loger.d("Math.abs(positionWindow[1] - mStatusBarHeight - mScreenHeight)-----     " + (Math.abs(positionWindow[1] - mScreenHeight) - height));
                    setBottomPosition((Math.abs(positionWindow[1] - mScreenHeight) - height) * 2);
                    canScroll = true;
                } else {
                    canScroll = false;
                }

            }
        });
    }

    private void setBottomPosition(int position) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        rl_bottom.measure(width, height);
        int h = rl_bottom.getMeasuredHeight();
        int w = rl_bottom.getMeasuredWidth();
        Loger.d("h----  " + h + "------w----  " + w);
        rl_bottom.layout(0, mScreenHeight - position, w, mScreenHeight - position + h);
    }


}
