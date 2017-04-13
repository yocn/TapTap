package com.tele.forum.taptap.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Yocn on 17.1.7.
 */

public class TApplication extends Application {
    public static String tag = "taptap";

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    /**
     * 初始化图片缓存
     */
    private void initImageLoader() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(getImageDownloadConfig());
    }

    private ImageLoaderConfiguration getImageDownloadConfig() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)   //设置图片不缓存于内存中
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)    //设置图片的质量
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)    //设置图片的缩放类型，该方法可以有效减少内存的占用
                .build();

        ImageLoaderConfiguration mInnerImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(4).denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(defaultOptions).diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(30 * 1024 * 1024).diskCacheFileCount(100)
//                .diskCache(new UnlimitedDiskCache(new File(mNewCachePicDir))).memoryCache(new WeakMemoryCache())
                // 缓存到文件的最大数据
                .imageDownloader(DefaultConfigurationFactory.createImageDownloader(this))
                .memoryCache(new WeakMemoryCache()).memoryCacheSize(2 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        return mInnerImageLoaderConfiguration;
    }
}
