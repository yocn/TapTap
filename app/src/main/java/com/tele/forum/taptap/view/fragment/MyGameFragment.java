package com.tele.forum.taptap.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.view.custom.AutoChangeImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yocn on 17.1.7.
 */

public class MyGameFragment extends BaseFragment {
    View mView;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_game, null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        mContext = getActivity();
        AutoChangeImageView mAutoChangeImageView = (AutoChangeImageView) mView.findViewById(R.id.fl_container);
        List<String> urls = new ArrayList<>();
        urls.add("http://p3.pstatp.com/large/1bf3000427bc5bfffe2f");
        urls.add("http://p3.pstatp.com/large/1bf4000430f5a2b777ae");
        urls.add("http://p3.pstatp.com/large/1a6e001d13a8b244c8a5");
        mAutoChangeImageView.setUrls(urls);
        return mView;
    }

}
