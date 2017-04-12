package com.tele.forum.taptap.view.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.DisplayUtils;

/**
 * Created by ysx on 2016/2/16.
 * 热门界面的数据适配器
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> implements View.OnClickListener {

//    private List<NewestBean> mList;

    //    private ImageLoader loader;
    private Drawable womenDrawable;
    private Drawable menDrawable;
    private Context mContext;
    private int[] mColors = new int[]{R.color.colorAccent, R.color.colorGreen, R.color.colorPrimary, R.color.colorRed, R.color.colorBlue};
//    private OnRecyclerViewItemClickListener listener;

    public RecommendAdapter(Context mContext) {
        this.mContext = mContext;
//        this.loader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.rl_item_recommend.setBackgroundResource(mColors[position % 5]);
        holder.iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecommendWindow(holder.iv_setting);
            }
        });
//        NewestBean bean = mList.get(position);
//        holder.mTvLiveName.setText(bean.getName());
//        String url = bean.getCover();
//        if (url != null || !"".equals(url)) {
//            loader.displayImage(url, holder.mIvLivePic);
//        }
//        holder.mIvLivePic.setTag(position);
//        holder.mTvWatchNumber.setText(String.format(mContext.getResources().getString(R.string.attention_people_count), bean.getAudience()));
//        UserBean b = bean.getUser_info();
//        url = b.getAvatar();
//        if (url != null || !"".equals(url)) {
//            loader.displayImage(url, holder.mIvAuthorAvatar);
//        }
//        url = b.getGender();
//        if (url != null && "women".equals(url)) {
//            holder.mTvAuthorName.setCompoundDrawables(null, null, womenDrawable, null);
//        } else {
//            holder.mTvAuthorName.setCompoundDrawables(null, null, menDrawable, null);
//        }
//        holder.mTvAuthorName.setText(b.getNick());
//
//        holder.mIvAuthorAvatar.setTag(position);
//        holder.mTvAuthorName.setTag(position);
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_item_recommend:
                break;
        }

    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_item_recommend;
        private ImageView iv_setting;

        public ViewHolder(View itemView) {
            super(itemView);
            rl_item_recommend = (RelativeLayout) itemView.findViewById(R.id.rl_item_recommend);
            iv_setting = (ImageView) itemView.findViewById(R.id.iv_setting);
        }
    }

    PopupWindow mRecommendPopupWindow = null;

    /**
     * popupwindow
     */
    private void initRecommendWindow(View view) {
        // 得到PopupWindow的布局
        if (mRecommendPopupWindow == null) {
            View mRecommendView = LayoutInflater.from(mContext).inflate(R.layout.popup_recommend, null);
            mRecommendPopupWindow = new PopupWindow(mRecommendView, LinearLayout.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dip2px(mContext, 280));
            mRecommendPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
            mRecommendPopupWindow.setFocusable(true);
            mRecommendPopupWindow.setOutsideTouchable(true);
            ColorDrawable dw = new ColorDrawable(mContext.getResources().getColor(R.color.transparent));
            mRecommendPopupWindow.setBackgroundDrawable(dw);
            mRecommendPopupWindow.setAnimationStyle(R.style.PlayAnchDialogAnimation);
            mRecommendPopupWindow.update();
        }
        mRecommendPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

}
