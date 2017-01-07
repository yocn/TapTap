package com.tele.forum.taptap.presenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;

import com.tele.forum.taptap.presenter.util.DisplayUtils;

public class TopAndBottomAnimationPresenter {
    Activity mContext;
    UpAndDownListener listener;
    private long time = 500;

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
    public void startTopUpAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorUpOutTrans = ObjectAnimator.ofFloat(rl_all, "translationY", 0,
                -1 * DisplayUtils.dip2px(mContext, 60));
        animatorUpOutTrans.setDuration(time);
        animatorUpOutTrans.start();
        animatorUpOutTrans.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rl_all.setVisibility(View.VISIBLE);
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
     * 顶部滑下来动画
     *
     * @param rl_all
     */
    public void startTopDownAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorUpOutTrans = ObjectAnimator.ofFloat(rl_all, "translationY", -DisplayUtils.dip2px(mContext, 60), 0);
        animatorUpOutTrans.setDuration(time);
        animatorUpOutTrans.start();
        animatorUpOutTrans.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rl_all.setVisibility(View.VISIBLE);
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
     * 底部滑下来动画
     *
     * @param rl_all
     */
    public void startBottomDownAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorDownInTrans = ObjectAnimator.ofFloat(rl_all, "translationY", 0, DisplayUtils.dip2px(mContext, 60));
        animatorDownInTrans.setDuration(time);
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

    /**
     * 底部滑下来动画
     *
     * @param rl_all
     */
    public void startBottomUpAnim(final View rl_all) {
        rl_all.setVisibility(View.VISIBLE);
        ObjectAnimator animatorDownInTrans = ObjectAnimator.ofFloat(rl_all, "translationY", DisplayUtils.dip2px(mContext, 60), 0);
        animatorDownInTrans.setDuration(time);
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
