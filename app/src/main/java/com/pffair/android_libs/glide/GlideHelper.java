package com.pffair.android_libs.glide;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pangff on 16/3/1.
 * Description glide helper
 */
public class GlideHelper {

    private static GlideHelper mGlideHelper;

    private int mNetWorkJudgeFlag = 0;


    public static final int ALL_NETWORK_DOWNLOAD_IMAG = 0;

    public static final int ONLY_WIFI_DOWNLOAD_IMAG = 1;

    private GlideHelper() {

    }

    public static GlideHelper getInstance() {
        if (mGlideHelper == null) {
            mGlideHelper = new GlideHelper();
        }
        return mGlideHelper;
    }

    public void setNetWorkJudgeFlag(int netWorkJudgeFlag) {
        this.mNetWorkJudgeFlag = netWorkJudgeFlag;
    }

    public void loadImageWithPlaceHolder(Context context, ImageView imageView, String url,
            Drawable placeHolder) {
        loadImageWithPlaceHolderAndTransform(context, imageView, url, placeHolder, null);
    }

    public void loadImageWithPlaceHolder(Context context, ImageView imageView, String url,
            int placeHolder) {
        loadImageWithPlaceHolderAndTransform(context, imageView, url, placeHolder, null);
    }

    public void loadImageWithTransform(Context context, ImageView imageView, String url,
            BitmapTransformation bitmapTransformation) {
        loadImageWithPlaceHolderAndTransform(context, imageView, url, null, bitmapTransformation);
    }

    public void loadImageWithPlaceHolderAndTransform(Context context, ImageView imageView,
            String url, Drawable placeHolder, BitmapTransformation bitmapTransformation) {
        switch (mNetWorkJudgeFlag) {
            case ALL_NETWORK_DOWNLOAD_IMAG:
                loadImageWithPlaceHolderAndTransformByNet(context, imageView, url, placeHolder,
                        bitmapTransformation);
                break;
            case ONLY_WIFI_DOWNLOAD_IMAG:
                if (isWifi(context)) {
                    loadImageWithPlaceHolderAndTransformByNet(context, imageView, url, placeHolder,
                            bitmapTransformation);
                } else {
                    loadImageWithPlaceHolderAndTransformInCache(context, imageView, url,
                            placeHolder, bitmapTransformation);
                }
                break;
        }
    }

    public void loadImageWithPlaceHolderAndTransform(Context context, ImageView imageView,
            String url, int placeHolder, BitmapTransformation bitmapTransformation) {
        switch (mNetWorkJudgeFlag) {
            case ALL_NETWORK_DOWNLOAD_IMAG:
                loadImageWithPlaceHolderAndTransformByNet(context, imageView, url, placeHolder,
                        bitmapTransformation);
                break;
            case ONLY_WIFI_DOWNLOAD_IMAG:
                if (isWifi(context)) {
                    loadImageWithPlaceHolderAndTransformByNet(context, imageView, url, placeHolder,
                            bitmapTransformation);
                } else {
                    loadImageWithPlaceHolderAndTransformInCache(context, imageView, url,
                            placeHolder, bitmapTransformation);
                }
                break;
        }
    }


    private void loadImageWithPlaceHolderAndTransformByNet(Context context, ImageView imageView,
            String url, Drawable placeHolder, BitmapTransformation bitmapTransformation) {
        if (bitmapTransformation != null) {
            Glide.with(context).load(url).placeholder(placeHolder)
                    .transform(bitmapTransformation).into(imageView);
        } else {
            Glide.with(context).load(url).placeholder(placeHolder).into(imageView);
        }
    }


    private void loadImageWithPlaceHolderAndTransformInCache(Context context, ImageView imageView,
            String url, Drawable placeHolder, BitmapTransformation bitmapTransformation) {
        if (bitmapTransformation != null) {
            Glide.with(context).using(cacheOnlyStreamLoader).load(url)
                    .placeholder(placeHolder)
                    .transform(bitmapTransformation).into(imageView);
        } else {
            Glide.with(context).using(cacheOnlyStreamLoader).load(url).placeholder(placeHolder)
                    .into(imageView);
        }
    }

    private void loadImageWithPlaceHolderAndTransformByNet(Context context, ImageView imageView,
            String url, int placeHolder, BitmapTransformation bitmapTransformation) {
        if (bitmapTransformation != null) {
            Glide.with(context).load(url).placeholder(placeHolder)
                    .transform(bitmapTransformation).into(imageView);
        } else {
            Glide.with(context).load(url).placeholder(placeHolder).into(imageView);
        }
    }

    private void loadImageWithPlaceHolderAndTransformInCache(Context context, ImageView imageView,
            String url, int placeHolder, BitmapTransformation bitmapTransformation) {
        if (bitmapTransformation != null) {
            Glide.with(context).using(cacheOnlyStreamLoader).load(url)
                    .placeholder(placeHolder)
                    .transform(bitmapTransformation).into(imageView);
        } else {
            Glide.with(context).using(cacheOnlyStreamLoader).load(url).placeholder(placeHolder)
                    .into(imageView);
        }
    }


    private static final StreamModelLoader<String> cacheOnlyStreamLoader = new StreamModelLoader<String>() {
        @Override
        public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                               return new DataFetcher<InputStream>() {
                        @Override
                        public InputStream loadData(Priority priority) throws Exception {
                            throw new IOException();
                        }

                        @Override
                        public void cleanup() {

                        }

                        @Override
                        public String getId() {
                            return model;
                        }

                        @Override
                        public void cancel() {

                }
            };
        }
    };


    public static boolean isWifi(Context context) {

        try {
            final ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    return false;
                }

                // NetworkInfo不为null开始判断是网络类型
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
