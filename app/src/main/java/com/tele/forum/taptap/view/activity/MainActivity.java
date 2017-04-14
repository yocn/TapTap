package com.tele.forum.taptap.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.TopAndBottomAnimationPresenter;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.adapter.HomeFragmentAdapter;
import com.tele.forum.taptap.view.adapter.HomeSettingAdapter;
import com.tele.forum.taptap.view.fragment.FindFragment;
import com.tele.forum.taptap.view.fragment.ForumFragment;
import com.tele.forum.taptap.view.fragment.MyGameFragment;
import com.tele.forum.taptap.view.fragment.RankFragment;
import com.tele.forum.taptap.view.fragment.RecommendFragment;
import com.tele.forum.taptap.view.service.FloatService;

public class MainActivity extends BaseTransTitleActivity implements View.OnClickListener {
    private ViewPager vp_main;
    private Fragment[] mFragments;
    private RelativeLayout rl_main_top;
    private RelativeLayout rl_top;
    private LinearLayout rl_main_bottom;
    private LinearLayout ll_drawe;
    TopAndBottomAnimationPresenter mTopAndBottomAnimationPresenter;
    private final int STATUE_HIDE = 0;
    private final int STATUE_SHOW = 1;
    private int mCurrentTopAndBottomShowStatus = STATUE_SHOW;
    RecommendFragment mRecommendFragment;
    FindFragment mFindFragment;
    ForumFragment mForumFragment;
    MyGameFragment mMyGameFragment;
    RankFragment mRankFragment;
    LinearLayout ll_0;
    LinearLayout ll_1;
    LinearLayout ll_2;
    LinearLayout ll_3;
    LinearLayout ll_4;
    ImageView civ_head;
    DrawerLayout drawer_layout;
    ListView lv_left_menu;
    String[] mSettings = {"悬浮窗", "设置"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        civ_head = (ImageView) findViewById(R.id.civ_head);
        rl_main_top = (RelativeLayout) findViewById(R.id.rl_main_top);
        rl_top = (RelativeLayout) findViewById(R.id.rl_top);
        rl_main_bottom = (LinearLayout) findViewById(R.id.rl_main_bottom);
        ll_drawe = (LinearLayout) findViewById(R.id.ll_drawe);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        ll_0 = (LinearLayout) findViewById(R.id.ll_0);
        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        ll_2 = (LinearLayout) findViewById(R.id.ll_2);
        ll_3 = (LinearLayout) findViewById(R.id.ll_3);
        ll_4 = (LinearLayout) findViewById(R.id.ll_4);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lv_left_menu = (ListView) findViewById(R.id.lv_left_menu);
    }

    private void initData() {
        ll_0.setOnClickListener(this);
        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);
        ll_3.setOnClickListener(this);
        ll_4.setOnClickListener(this);
        rl_top.setOnClickListener(this);
        civ_head.setOnClickListener(this);
        mRecommendFragment = new RecommendFragment();
        mRankFragment = new RankFragment();
        mFindFragment = new FindFragment();
        mForumFragment = new ForumFragment();
        mMyGameFragment = new MyGameFragment();
        mFragments = new Fragment[]{mRecommendFragment, mRankFragment, mFindFragment, mForumFragment, mMyGameFragment};
        FragmentManager fm = getSupportFragmentManager();
        HomeFragmentAdapter mHomeFragmentAdapter = new HomeFragmentAdapter(fm, mFragments);
        vp_main.setAdapter(mHomeFragmentAdapter);
        HomeSettingAdapter mHomeSettingAdapter = new HomeSettingAdapter(mSettings, this);
        lv_left_menu.setAdapter(mHomeSettingAdapter);
        mTopAndBottomAnimationPresenter = new TopAndBottomAnimationPresenter(this, mUpAndDowmListener);
    }

    /**
     * 显示头部和底部
     */
    public void showTopAndBottom() {
        if (mTopAndBottomAnimationPresenter == null) return;
        if (mCurrentTopAndBottomShowStatus == STATUE_SHOW) return;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_0:
                vp_main.setCurrentItem(0);
                break;
            case R.id.ll_1:
                vp_main.setCurrentItem(1);
                break;
            case R.id.ll_2:
                vp_main.setCurrentItem(2);
                break;
            case R.id.ll_3:
                vp_main.setCurrentItem(3);
                break;
            case R.id.ll_4:
                vp_main.setCurrentItem(4);
                break;
            case R.id.civ_head:
                drawer_layout.openDrawer(ll_drawe);
                break;
            case R.id.rl_top:
                Loger.d("rl_top");
                break;
        }
    }
}
