package com.tele.forum.taptap.view.custom;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.AlphaChangeAnimationPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * com.tele.forum.taptap.view.custom.AutoChangeImageView
 * 可以装多个ImageView的FrameLayout，ImageView会自动变换
 * 使用一个framelayout，新建n个ImageView，当最顶层的ImageView动画执行完后移除掉最顶层的，新建一个ImageVIew放到最底层
 * 为达到效果，应该最少使用两张图片
 * Created by Yocn on 2017/4/13.
 */

public class AutoChangeImageView extends FrameLayout {
    ArrayList<ImageView> mImageViewContainer = new ArrayList<>();
    AlphaChangeAnimationPresenter mAlphaChangeAnimationPresenter;
    Context mContext;
    private List<String> urls = new ArrayList<>();
    FrameLayout fl_all_container;
    int anInt = 0;

    public AutoChangeImageView(Context context) {
        super(context);
    }

    public AutoChangeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initData();
    }

    /**
     * 外部传入需要显示的View的链接
     *
     * @param urls 链接的地址
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
        for (String url : urls) {
            ImageView imageView1 = new ImageView(mContext);
            imageView1.setImageResource(R.drawable.a);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(url, imageView1);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView1.setLayoutParams(params);
            mImageViewContainer.add(imageView1);
            fl_all_container.addView(imageView1);
        }
        if (fl_all_container.getChildCount() > 0) {
            startAnim(fl_all_container.getChildCount() - 1);
        }
    }

    public AutoChangeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_image_auto_change, null);
        fl_all_container = (FrameLayout) mView.findViewById(R.id.fl_all_container);
        addView(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    private void initData() {
        mAlphaChangeAnimationPresenter = new AlphaChangeAnimationPresenter();
    }

    private void startAnim(int position) {
        mAlphaChangeAnimationPresenter.startDismiss(fl_all_container.getChildAt(position), listener);
    }

    private void addANewImageView() {
        anInt++;
        boolean hasOldImageView = false;
        ImageView imageView1 = null;
//        for (int i = 0; i < mImageViewContainer.size(); i++) {
//            if (mImageViewContainer.get(i).getParent() == null) {
//                imageView1 = mImageViewContainer.get(i);
//                ImageLoader.getInstance().displayImage(urls.get(anInt % urls.size()), imageView1);
//                hasOldImageView = true;
//            }
//        }

        if (!hasOldImageView) {
            imageView1 = new ImageView(mContext);
            imageView1.setTag(anInt);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.getInstance().displayImage(urls.get(anInt % urls.size()), imageView1);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView1.setLayoutParams(params);
        }
        mImageViewContainer.add(0, imageView1);
        fl_all_container.addView(imageView1, 0);//0说明是放到framelayout的最底层
    }

    Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            addANewImageView();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            fl_all_container.removeViewAt(fl_all_container.getChildCount() - 1);
            View view = mImageViewContainer.get(mImageViewContainer.size() - 1);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            startAnim(fl_all_container.getChildCount() - 1);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}
