package com.tele.forum.taptap.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.TopAndBottomAnimationPresenter;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.adapter.HomeFragmentAdapter;
import com.tele.forum.taptap.view.fragment.FindFragment;
import com.tele.forum.taptap.view.fragment.ForumFragment;
import com.tele.forum.taptap.view.fragment.MyGameFragment;
import com.tele.forum.taptap.view.fragment.RankFragment;
import com.tele.forum.taptap.view.fragment.RecommendFragment;

public class MainActivity extends BaseTransTitleActivity {
    private ViewPager vp_main;
    private Fragment[] mFragments;
    private RelativeLayout rl_main_top;
    private LinearLayout rl_main_bottom;
    TopAndBottomAnimationPresenter mTopAndBottomAnimationPresenter;
    private final int STATUE_HIDE = 0;
    private final int STATUE_SHOW = 1;
    private int mCurrentTopAndBottomShowStatus = STATUE_SHOW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        rl_main_top = (RelativeLayout) findViewById(R.id.rl_main_top);
        rl_main_bottom = (LinearLayout) findViewById(R.id.rl_main_bottom);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
    }

    private void initData() {
        RecommendFragment mRecommendFragment;
        FindFragment mFindFragment;
        ForumFragment mForumFragment;
        MyGameFragment mMyGameFragment;
        RankFragment mRankFragment;

        mRecommendFragment = new RecommendFragment();
        mRankFragment = new RankFragment();
        mFindFragment = new FindFragment();
        mForumFragment = new ForumFragment();
        mMyGameFragment = new MyGameFragment();

        mFragments = new Fragment[]{mRecommendFragment, mRankFragment, mFindFragment, mForumFragment, mMyGameFragment};
        FragmentManager fm = getSupportFragmentManager();
        HomeFragmentAdapter mHomeFragmentAdapter = new HomeFragmentAdapter(fm, mFragments);
        vp_main.setAdapter(mHomeFragmentAdapter);

        mTopAndBottomAnimationPresenter = new TopAndBottomAnimationPresenter(this, mUpAndDowmListener);
    }

    /**
     * 显示头部和底部
     */
    public void showTopAndBottom() {
        if (mTopAndBottomAnimationPresenter == null) return;
        if (mCurrentTopAndBottomShowStatus == STATUE_SHOW) return;
        Loger.d("---showTopAndBottom");
        mCurrentTopAndBottomShowStatus = STATUE_SHOW;
        mTopAndBottomAnimationPresenter.startTopDownAnim(rl_main_top);
        mTopAndBottomAnimationPresenter.startBottomUpAnim(rl_main_bottom);
    }

    /**
     * 隐藏头部和底部
     */
    public void dismissTopAndBottom() {
        if (mTopAndBottomAnimationPresenter == null) return;
        if (mCurrentTopAndBottomShowStatus == STATUE_HIDE) return;
        Loger.d("---dismissTopAndBottom");
        mCurrentTopAndBottomShowStatus = STATUE_HIDE;
        mTopAndBottomAnimationPresenter.startTopUpAnim(rl_main_top);
        mTopAndBottomAnimationPresenter.startBottomDownAnim(rl_main_bottom);
    }

    /**
     * 顶部底部动画结束的监听
     */
    TopAndBottomAnimationPresenter.UpAndDownListener mUpAndDowmListener = new TopAndBottomAnimationPresenter.UpAndDownListener() {

        @Override
        public void OnAnchorInfoUpOut() {

        }

        @Override
        public void OnAnchorInfoDownIn() {

        }
    };

}
