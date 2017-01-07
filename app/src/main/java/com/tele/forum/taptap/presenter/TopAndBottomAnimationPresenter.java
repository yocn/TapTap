package com.tele.forum.taptap.presenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;

import com.tele.forum.taptap.presenter.util.DisplayUtils;

public class TopAndBottomAnimationPresenter {
    Activity mContext;
    UpAndDownListener listener;

    public interface UpAndDownListener {
        void OnAnchorInfoUpOut();

        void OnAnchorInfoDownIn();
    }

    public TopAndBottomAnimationPresenter(Activity context, UpAndDownListener listener) {
        mContext = context;
        this.listener = listener;
    }

    /**
     * PlayChatFragment 主播详情滑上去动画
     *
     * @param rl_all
     */
    public void startUpOutTopAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorUpOutTrans = ObjectAnimator.ofFloat(rl_all, "translationY", 0,
                -1 * DisplayUtils.dip2px(mContext, 60));
        animatorUpOutTrans.setDuration(1000);
        animatorUpOutTrans.start();
        animatorUpOutTrans.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rl_all.setVisibility(View.INVISIBLE);
                if (listener != null) {
                    listener.OnAnchorInfoUpOut();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    /**
     * PlayChatFragment 主播详情滑下来动画
     *
     * @param rl_all
     */
    public void startDownInTopAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorDownInTrans = ObjectAnimator.ofFloat(rl_all, "translationY",
                -1 * DisplayUtils.dip2px(mContext, 60), 0);
        animatorDownInTrans.setDuration(1000);
        animatorDownInTrans.start();
        animatorDownInTrans.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rl_all.setVisibility(View.VISIBLE);
                if (listener != null) {
                    listener.OnAnchorInfoDownIn();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

}
