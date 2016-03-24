package com.pffair.android_libs.glide;

import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/1.
 * Description glide使用的view holder
 */
public class GlideUseViewHolder extends PageViewHolder {

    @Bind(R.id.iv_image)
    ImageView ivImage;

    public GlideUseViewHolder(Context context){
       super(context);
    }

    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup viewGroup) {
        GlideHelper.getInstance().setNetWorkJudgeFlag(GlideHelper.ONLY_WIFI_DOWNLOAD_IMAG);
        GlideHelper.getInstance().loadImageWithPlaceHolder(mBaseActivity,ivImage,"http://img.taopic.com/uploads/allimg/120403/57997-12040319145195.jpg",R.drawable.ic_menu_camera);
    }

    @Override
    public int getLayout() {
        return R.layout.page_glide;
    }
}
