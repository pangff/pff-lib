package com.pffair.android_libs.vector_path;

import com.caverock.androidsvg.MyPathView;
import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/3/10.
 * Description VectorDrawableUseViewHolder
 */
public class VectorPathViewUseViewHolder extends PageViewHolder {

    @Bind(R.id.pathView)
    MyPathView pathView;

    @Bind(R.id.sb_viewbox_width)
    SeekBar sbViewBoxWidth;

    @Bind(R.id.sb_viewbox_height)
    SeekBar sbViewBoxHeight;

    @Bind(R.id.sb_scale)
    SeekBar sbScale;

    @Bind(R.id.bt_draw_path)
    Button btDrawPath;


    public VectorPathViewUseViewHolder(Context context){
        super(context);
    }


    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup viewGroup) {
        pathView.setFill(true);

        final List<String> svgDrawList = new ArrayList<>();
        svgDrawList.add("path5235");
        svgDrawList.add("path5196");
        svgDrawList.add("path5198");
        svgDrawList.add("path5200");
        svgDrawList.add("path5202");
        svgDrawList.add("path5946");
        svgDrawList.add("path5207");
        svgDrawList.add("path5209");
        svgDrawList.add("path5211");
        svgDrawList.add("path5213");
        svgDrawList.add("path5215");
        svgDrawList.add("path5217");
        svgDrawList.add("path5219");
        svgDrawList.add("path5221");
        svgDrawList.add("path5223");
        svgDrawList.add("path5225");
        svgDrawList.add("path5227");
        svgDrawList.add("path5229");
        svgDrawList.add("path5231");
        svgDrawList.add("path5233");

        svgDrawList.add("path3041-8-1-6-3-9-0-5-8-35");
        svgDrawList.add("path3041-8-1-6-3-9-0-5-8-3");
        svgDrawList.add("path3041-8-1-6-3-9-0-5-8");
        svgDrawList.add("path3041-8-1-6-3-9-0-4-3");
        svgDrawList.add("path3041-8-1-6-3-9-0-5-0");
        svgDrawList.add("path3041-8-1-6-3-9-0-4-1");
        svgDrawList.add("path3041-8-1-6-3-9-0-5");
        svgDrawList.add("path3041-8-1-6-3-9-0-4");
        svgDrawList.add("path3415");
        svgDrawList.add("path3041-8-64-1-5");
        svgDrawList.add("path3041-8-1-6-3-9-0-7");
        svgDrawList.add("path3041-8-1-6-3-9-0-0");
        svgDrawList.add("path3041-8-1-6-3-9-0-2");
        svgDrawList.add("path3041-8-1-6-3-9-0");
        svgDrawList.add("path3041-8-1-6-3-9-7");
        svgDrawList.add("path3041-8-1-6-3-9-8");
        svgDrawList.add("path3041-8-1-6-3-3");
        svgDrawList.add("path3041-8-1-6-3-9");
        svgDrawList.add("path3041-8-1-6-3-2");
        svgDrawList.add("path3041-8-1-6-3-5");


        pathView.setSvgLoadedListener(new MyPathView.SvgLoadedListener() {
            @Override
            public void onSuccess() {
                pathView.post(new Runnable() {
                    @Override
                    public void run() {
                        pathView.setPercentage(1);
                        pathView.invalidate();
                    }
                });
            }
        });

        btDrawPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.setSvgDrawPaths(svgDrawList);
                pathView.requestLayout();
            }
        });


        sbViewBoxWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pathView.mySvgUtils.setViewBoxDx(100*progress);
                pathView.setViewBoxDx(100*progress);
                pathView.refresh();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbViewBoxHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pathView.mySvgUtils.setViewBoxDy(100*progress);
                pathView.setViewBoxDy(100*progress);
                pathView.refresh();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pathView.mySvgUtils.setViewBoxScale(2*progress/100f);
                pathView.setViewBoxScale(2*progress/100f);
                pathView.refresh();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void doAnimation(){
        pathView.getPathAnimator()
                .delay(100)
                .duration(1000)
                .interpolator(new AccelerateInterpolator())
                .listenerStart(new MyPathView.AnimatorBuilder.ListenerStart() {
                    @Override
                    public void onAnimationStart() {
                        Log.e("TAG", "start");
                    }
                })
                .listenerEnd(new MyPathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        Log.e("TAG","end");
                    }
                }).start();
    }

    @Override
    public int getLayout() {
        return R.layout.page_vector_path_view;
    }
}
