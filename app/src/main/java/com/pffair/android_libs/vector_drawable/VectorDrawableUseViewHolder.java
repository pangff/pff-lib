package com.pffair.android_libs.vector_drawable;

import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/10.
 * Description VectorDrawableUseViewHolder
 */
public class VectorDrawableUseViewHolder extends PageViewHolder {

    @Bind(R.id.pathView)
    ImageView pathView;

    public VectorDrawableUseViewHolder(Context context){
        super(context);
    }


    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup viewGroup) {
//        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(viewGroup.getResources(),R.drawable.heart_vector,null);
//        Canvas canvas
//        vectorDrawableCompat.

        Drawable drawable = pathView.getDrawable();
        if (drawable!=null && drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.page_vector_drawable;
    }
}
