package com.tele.forum.taptap.presenter.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Display工具类
 * <p/>
 *
 * @author zhulingjun
 */
public class DisplayUtils {
    private static volatile DisplayUtils instance = null;
    private final Context m_context;
    private final DisplayMetrics m_dm;

    private DisplayUtils(Context context) {
        m_context = context;
        m_dm = new DisplayMetrics();
        WindowManager mm = ((WindowManager) m_context.getSystemService(Context.WINDOW_SERVICE));
        mm.getDefaultDisplay().getMetrics(m_dm);
    }

    public static DisplayUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (DisplayUtils.class) {
                if (instance == null) {
                    instance = new DisplayUtils(context);
                }
            }
        }
        return instance;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

    public static int px2dp(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d / scale + 0.5f);
    }

    /**
     * <p>
     * 判断当前屏幕是否竖屏
     * <p/>
     *
     * @return
     */
    public static boolean port(Context context) {
        int screenHeight = getInstance(context).getScreenHeight();
        int screenWidth = getInstance(context).getScreenWidth();
        return screenHeight > screenWidth;
    }

    /**
     * <p>
     * 获取手机屏幕宽度
     * <p/>
     *
     * @author zhulingjun
     */
    public int getScreenWidth() {
        return m_dm.widthPixels;
    }

    /**
     * <p>
     * 获取手机屏幕高度
     * <p/>
     *
     * @author zhulingjun
     */
    public int getScreenHeight() {
        return m_dm.heightPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文对象
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 获取屏幕
     *
     * @param context 上下文对象
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * <p>
     * 设置Activity全屏
     * <p/>
     *
     * @author zhulingjun
     */
    public static void fullScreenActivity(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * <p>
     * 获取本地安装的程序列表
     * <p/>
     *
     * @param context
     * @return
     * @author zhulingjun
     */
    public static List<String> getAllApps(Context context) {
        List<String> apps = new ArrayList<String>();
        PackageManager pManager = context.getPackageManager();
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            // 判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                // dto.setVersion(pak.versionName);
                // dto.setVersioncode(pak.versionCode);
                // dto.setTitle(pManager.getApplicationLabel(pak.applicationInfo).toString());
                apps.add(pak.packageName);
            }
        }
        return apps;
    }

    /**
     * <p>
     * 返回设备名称
     * <p/>
     *
     * @param context
     * @return
     * @author zhulingjun
     */
    public static String getDeviceName(Context context) {
        try {
            return URLEncoder.encode(Build.MODEL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <p>
     * 获取应用名称
     * <p/>
     *
     * @param context
     * @return
     * @author zhulingjun
     */
    public static String getAppVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return " V " + packInfo.versionName;
    }

    public static String getAppVersionNameNoV(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return packInfo.versionName;
    }

    /**
     * <p>
     * 动态设置ListView高度
     * <p/>
     * <p>
     * 带HeadView头
     * <p/>
     *
     * @param headView
     * @param listView
     * @author zhulingjun
     */
    public static void setListViewHeightBasedOnChildren(View headView, ListView listView) {
        listView.setItemsCanFocus(true);
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null || listAdapter.isEmpty()) {
            return;
        }

        int totalHeight = 0;
        View listItem = listAdapter.getView(1, null, listView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight() * (listAdapter.getCount() - 1);

        headView.measure(0, 0);
        totalHeight = totalHeight + headView.getMeasuredHeight();

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * <p>
     * 动态计算ListView高度
     * <p/>
     * <p>
     * 设置ListViw高度
     * <p/>
     *
     * @param listView
     * @author zhulingjun
     */
    public static int setListViewHeightBasedOnChildren(ListView listView) {
        listView.setItemsCanFocus(true);
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null || listAdapter.isEmpty()) {
            return 0;
        }

        int totalHeight = 0;
        View listItem = listAdapter.getView(0, null, listView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight() * (listAdapter.getCount());

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        return params.height;
    }

    /**
     * <p>
     * 隐藏软键盘
     * <p/>
     *
     * @param context
     * @param view
     * @author zhulingjun
     */
    public static void hideIme(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * <p>
     * 调用系统安装
     * <p/>
     *
     * @author zhulingjun
     */
    public static void installBySystem(Context mContext, String apkPath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    // 转换drawable到bitmap
    public static byte[] convertDrawable(Drawable icon) {
        byte[] data = null;
        try {
            Bitmap bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(),
                    icon.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
            icon.draw(canvas);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            bitmap.recycle();
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * <p>
     * 获取内存大小
     * <p/>
     *
     * @return
     */
    public static long getmem_TOLAL() {
        long mTotal;
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // 截取字符串信息

        content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return mTotal;
    }

}
