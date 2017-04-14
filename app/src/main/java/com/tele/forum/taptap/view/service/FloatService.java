package com.tele.forum.taptap.view.service;

/**
 * 悬浮窗Service
 * Created by Yocn on 2017/4/14.
 */

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tele.forum.taptap.R;
import com.tele.forum.taptap.presenter.util.Loger;
import com.tele.forum.taptap.view.custom.AutoChangeImageView;

import java.util.ArrayList;
import java.util.List;

public class FloatService extends Service {

    //定义浮动窗口布局  
    RelativeLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象  
    WindowManager mWindowManager;
    ImageView mFloatView;

    @Override
    public void onCreate() {
        super.onCreate();
        Loger.d("oncreat");
        createFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Loger.d("onBind");
        return null;
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        //获取的是WindowManagerImpl.CompatModeWrapper  
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        Loger.d("mWindowManager--->" + mWindowManager);
        //设置window type  
        wmParams.type = LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明  
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）  
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶  
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity  
        wmParams.x = 0;
        wmParams.y = 0;

        //设置悬浮窗口长宽数据    
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // 设置悬浮窗口长宽数据
//        wmParams.width = 200;
//        wmParams.height = 80;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局  
        mFloatLayout = (RelativeLayout) inflater.inflate(R.layout.float_layout, null);
        //添加mFloatLayout  
        mWindowManager.addView(mFloatLayout, wmParams);
        //浮动窗口按钮  
        mFloatView = (ImageView) mFloatLayout.findViewById(R.id.iv_float);

        AutoChangeImageView mAutoChangeImageView = (AutoChangeImageView) mFloatLayout.findViewById(R.id.fl_container);
        List<String> urls = new ArrayList<>();
        urls.add("http://p3.pstatp.com/large/1bf3000427bc5bfffe2f");
        urls.add("http://p3.pstatp.com/large/1bf4000430f5a2b777ae");
        urls.add("http://p3.pstatp.com/large/1a6e001d13a8b244c8a5");
        mAutoChangeImageView.setUrls(urls);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        Loger.d("Width/2--->" + mFloatLayout.getMeasuredWidth() / 2);
        Loger.d("Height/2--->" + mFloatLayout.getMeasuredHeight() / 2);
        //设置监听浮动窗口的触摸移动
        mFloatView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth() / 2;
                Loger.d("RawX" + event.getRawX());
                Loger.d("X" + event.getX());
                //减25为状态栏的高度  
                wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight() / 2 - 25;
                Loger.d("RawY" + event.getRawY());
                Loger.d("Y" + event.getY());
                //刷新  
                mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                return false;  //此处必须返回false，否则OnClickListener获取不到监听  
            }
        });

        mFloatView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(FloatService.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatLayout != null) {
            //移除悬浮窗口  
            mWindowManager.removeView(mFloatLayout);
        }
    }

} 
