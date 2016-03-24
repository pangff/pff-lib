package com.pffair.android_libs.base;

import com.pffair.android_libs.BaseActivity;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by pangff on 16/3/1.
 * Description TODO
 */
public abstract class PageViewHolder {

    protected BaseActivity mBaseActivity;

    public PageViewHolder(Context context){
        mBaseActivity = (BaseActivity) context;
    }

    public void attach(ViewGroup view){
        view.removeAllViews();
        mBaseActivity.getLayoutInflater().inflate(getLayout(),view,true);
        onBindView(view);
        onAttach(view);
    }

    public abstract void onBindView(ViewGroup view);

    public abstract void onAttach(ViewGroup view);

    public abstract int getLayout();
}
