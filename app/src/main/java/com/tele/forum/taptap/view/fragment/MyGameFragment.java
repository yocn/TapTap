package com.tele.forum.taptap.view.fragment;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.AlphaChangeAnimationPresenter;
import com.tele.forum.taptap.presenter.util.Loger;

import java.util.ArrayList;

import static com.tele.forum.taptap.R.id.fl_container;

/**
 * Created by Yocn on 17.1.7.
 */

public class MyGameFragment extends BaseFragment {
    View mView;
    AlphaChangeAnimationPresenter mAlphaChangeAnimationPresenter;
    Context mContext;
    ArrayList<ImageView> mImageViewContainer = new ArrayList<>();
    FrameLayout fl_container;
    private int mCurrentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_game, null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        mContext = getActivity();
        initView();
        initData();
        return mView;
    }

    private void initView() {
        fl_container = (FrameLayout) mView.findViewById(R.id.fl_container);
        ImageView imageView1 = new ImageView(mContext);
        imageView1.setImageResource(R.drawable.a);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView1.setLayoutParams(params);
        ImageView imageView2 = new ImageView(mContext);
        imageView2.setImageResource(R.drawable.b);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView2.setLayoutParams(params2);
        ImageView imageView3 = new ImageView(mContext);
        imageView3.setImageResource(R.drawable.c);
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView3.setLayoutParams(params3);
        mImageViewContainer.add(imageView1);
        mImageViewContainer.add(imageView2);
        mImageViewContainer.add(imageView3);

        fl_container.addView(imageView1);
        fl_container.addView(imageView2);
        fl_container.addView(imageView3);
        Loger.d(fl_container.getChildCount() + "");

    }

    private void initData() {
        mAlphaChangeAnimationPresenter = new AlphaChangeAnimationPresenter();
        startAnim(fl_container.getChildCount() - 1);
    }

    private void startAnim(int position) {
        mAlphaChangeAnimationPresenter.startDismiss(fl_container.getChildAt(position), listener);
    }

    int anInt = 0;
    int res = R.drawable.a;

    private void addANewImageView() {
        anInt++;
        ImageView imageView1 = new ImageView(mContext);
        switch (anInt % 3) {
            case 0:
                res = R.drawable.a;
                break;
            case 1:
                res = R.drawable.b;
                break;
            case 2:
                res = R.drawable.c;
                break;
        }
        imageView1.setTag(anInt);
        imageView1.setImageResource(res);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView1.setLayoutParams(params);
        mImageViewContainer.add(imageView1);
        fl_container.addView(imageView1, 0);
    }

    Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            addANewImageView();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            fl_container.removeViewAt(fl_container.getChildCount() - 1);
            startAnim(fl_container.getChildCount() - 1);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}
