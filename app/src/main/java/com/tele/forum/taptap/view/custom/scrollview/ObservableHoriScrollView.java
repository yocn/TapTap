package com.tele.forum.taptap.view.custom.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class ObservableHoriScrollView extends HorizontalScrollView {

    private ScrollViewListener scrollViewListener = null;

    public interface ScrollViewListener {
        void onScrollChanged(ObservableHoriScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public ObservableHoriScrollView(Context context) {
        super(context);
    }

    public ObservableHoriScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableHoriScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

}
