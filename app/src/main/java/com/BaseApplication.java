package com;

import android.app.Application;
import android.os.Handler;

/**
 * Created by pangff on 16/3/26.
 * Description BaseApplication
 */
public class BaseApplication extends Application {
    public  static  BaseApplication mBaseApplication;
    public static Handler mHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        mHandler = new Handler();
    }
}
