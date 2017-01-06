package com.tele.forum.taptap.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.view.custom.SystemBarTintManager;

public class BaseTransTitleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setTranslucentStatus(getLayoutInflater().inflate(layoutResID, null));
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        setTranslucentStatus(view);
        super.setContentView(view);
    }

    /**
     * 设置状态栏背景状态
     */
    public void setTranslucentStatus(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);// 状态栏无背景
        tintManager.setTintColor(getResources().getColor(R.color.colorMain));

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        //int top = DisplayUtils.getStatusBarHeight(this);
        System.out.println("---" + config.getPixelInsetTop(false));
        view.setPadding(0, config.getPixelInsetTop(false), 0, 0);
    }
}
