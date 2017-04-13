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
    private final int time = 2500;

    public AlphaChangeAnimationPresenter() {
    }

    public void startDismiss(View view, Animator.AnimatorListener listener) {
        ObjectAnimator animatorAlphaDismiss = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ObjectAnimator animatorAlphaScaleXBigger = ObjectAnimator.ofFloat(view, "scaleX", 1, 2);
        ObjectAnimator animatorAlphaScaleYBigger = ObjectAnimator.ofFloat(view, "scaleY", 1, 2);
        AnimatorSet AnimSetSmaller = new AnimatorSet();
        AnimSetSmaller.addListener(listener);
        AnimSetSmaller.playTogether(animatorAlphaScaleXBigger, animatorAlphaScaleYBigger, animatorAlphaDismiss);
        AnimSetSmaller.setDuration(time).start();
    }
}
