package com.tele.forum.taptap.presenter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Yocn on 2017/4/12.
 */

public class AlphaChangeAnimationPresenter {
    ImageView mView;

    public AlphaChangeAnimationPresenter() {
    }

    public AlphaChangeAnimationPresenter(ImageView view) {
        mView = view;
        initAnimation(mView);
    }

    private void initAnimation(View view) {
        ObjectAnimator animatorAlphaShow = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        ObjectAnimator animatorAlphaDismiss = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ObjectAnimator animatorAlphaScaleXBigger = ObjectAnimator.ofFloat(view, "scaleX", 1, 2);
        ObjectAnimator animatorAlphaScaleYBigger = ObjectAnimator.ofFloat(view, "scaleY", 1, 2);
        ObjectAnimator animatorAlphaScaleXSmaller = ObjectAnimator.ofFloat(view, "scaleX", 2, 1);
        ObjectAnimator animatorAlphaScaleYSmaller = ObjectAnimator.ofFloat(view, "scaleY", 2, 1);

        AnimatorSet AnimSetBiger = new AnimatorSet();
        AnimSetBiger.playTogether(animatorAlphaScaleXBigger, animatorAlphaScaleYBigger);
        AnimSetBiger.setDuration(500);
        AnimatorSet AnimSetSmaller = new AnimatorSet();
        AnimSetSmaller.playTogether(animatorAlphaScaleXSmaller, animatorAlphaScaleYSmaller);
        AnimSetSmaller.setDuration(500);

    }

    public void startShow(View view, Animator.AnimatorListener listener) {
        ObjectAnimator animatorAlphaShow = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        ObjectAnimator animatorAlphaScaleXBigger = ObjectAnimator.ofFloat(view, "scaleX", 1, 2);
        ObjectAnimator animatorAlphaScaleYBigger = ObjectAnimator.ofFloat(view, "scaleY", 1, 2);
        AnimatorSet AnimSetBiger = new AnimatorSet();
        AnimSetBiger.addListener(listener);
        AnimSetBiger.playTogether(animatorAlphaScaleXBigger, animatorAlphaScaleYBigger, animatorAlphaShow);
        AnimSetBiger.setDuration(1000).start();
    }

    public void startDismiss(View view, Animator.AnimatorListener listener) {
        ObjectAnimator animatorAlphaDismiss = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ObjectAnimator animatorAlphaScaleXBigger = ObjectAnimator.ofFloat(view, "scaleX", 1, 2);
        ObjectAnimator animatorAlphaScaleYBigger = ObjectAnimator.ofFloat(view, "scaleY", 1, 2);
        AnimatorSet AnimSetSmaller = new AnimatorSet();
        AnimSetSmaller.addListener(listener);
        AnimSetSmaller.playTogether(animatorAlphaScaleXBigger, animatorAlphaScaleYBigger, animatorAlphaDismiss);
        AnimSetSmaller.setDuration(1000).start();
    }
}
