package com.pffair.android_libs.diff;

import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;

import android.content.Context;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/1.
 * Description pageDiff
 */
public class DiffUseViewHolder extends PageViewHolder {
    public DiffUseViewHolder(Context context){
        super(context);
    }

    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup viewGroup) {

    }

    @Override
    public int getLayout() {
        return R.layout.page_diff;
    }
}
