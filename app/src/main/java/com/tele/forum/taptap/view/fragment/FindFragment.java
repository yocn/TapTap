package com.tele.forum.taptap.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.Loger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yocn on 17.1.7.
 */

public class FindFragment extends BaseFragment {
    View mView;
    TextView tv_introduce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find, null);
        mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        initView();
        initData();
        return mView;
    }

    private void initView() {
        tv_introduce = (TextView) mView.findViewById(R.id.tv_introduce);
    }

    private void initData() {
        String text = "255645222";
        String s = "2";
        String REGEX = "(" + s + ")*";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        // 若要改变颜色，则需用到SpannableString
        SpannableString sp = new SpannableString(text);
        while (matcher.find()) {
//            String group = matcher.group();
//            Loger.d("matcher.start()-----" + matcher.start());
//            Loger.d("matcher.end()-----" + matcher.end());
            sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRed)), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv_introduce.setText(sp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
