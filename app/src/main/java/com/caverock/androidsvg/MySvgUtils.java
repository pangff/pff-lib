package com.caverock.androidsvg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by pangff on 16/3/21.
 * Description MySvgUtils
 */
public class MySvgUtils {
    private static final String LOG_TAG = "SVGUtils";
    private final LinkedHashMap<Integer,SvgPath> mPaths = new LinkedHashMap<>();
    private final Paint mSourcePaint;
    private SVG mSvg;

    public MySvgUtils(Paint sourcePaint) {
        this.mSourcePaint = sourcePaint;
    }

    public void load(Context context, int svgResource) {
        if(this.mSvg == null) {
            try {
                this.mSvg = SVG.getFromResource(context, svgResource);
                this.mSvg.setDocumentPreserveAspectRatio(PreserveAspectRatio.UNSCALED);
            } catch (SVGParseException var4) {
                Log.e("SVGUtils", "Could not load specified SVG resource", var4);
            }
        }
    }

    public void drawSvgAfter(Canvas canvas, int width, int height) {
        float strokeWidth = this.mSourcePaint.getStrokeWidth();
        this.rescaleCanvas(width, height, strokeWidth, canvas);
    }

    public LinkedHashMap<Integer,SvgPath> getPathsForViewport(final int width, final int height) {
        if(mPaths!=null){
            mPaths.clear();
        }
        /** 要求svg中的标签元素按顺序 Ellipse、Circle、Path 并且不能穿插 **/
        final List<SVG.SvgObject> svgObjectList = new ArrayList<>();
        final List<SVG.SvgObject> svgObjectListEllipse = mSvg.getElementsByTagName(SVG.Ellipse.class);
        final List<SVG.SvgObject> svgObjectListCircle = mSvg.getElementsByTagName(SVG.Circle.class);
        final List<SVG.SvgObject> svgObjectListPath = mSvg.getElementsByTagName(SVG.Path.class);
        final int sizeEllipse = svgObjectListEllipse.size();
        final int sizeCircle = svgObjectListCircle.size();
        final int sizePath = svgObjectListPath.size();
        svgObjectList.addAll(svgObjectListEllipse);
        svgObjectList.addAll(svgObjectListCircle);
        svgObjectList.addAll(svgObjectListPath);
        final float strokeWidth = this.mSourcePaint.getStrokeWidth();
        Canvas canvas = new Canvas() {
            private final Matrix mMatrix = new Matrix();

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }

            public synchronized void drawPath(Path path, Paint paint) {
                Path dst = new Path();
                this.getMatrix(this.mMatrix);
                path.transform(this.mMatrix, dst);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(strokeWidth);
                if(svgObjectList.size()>MySvgUtils.this.mPaths.size()){
                    SVG.SvgObject svgObject = svgObjectList.get(MySvgUtils.this.mPaths.size());
                    String id = ((SVG.SvgElementBase)svgObject).id;
                    if(!MySvgUtils.this.mPaths.containsKey(path.hashCode())){
                        int type = 0;
                        if(sizeEllipse>MySvgUtils.this.mPaths.size()){
                            type = SvgPath.TYPE_ELLIPSE;
                        }else if(sizeEllipse+sizeCircle>MySvgUtils.this.mPaths.size()){
                            type = SvgPath.TYPE_CIRCLE;
                        }else if(sizeEllipse+sizeCircle+sizePath>MySvgUtils.this.mPaths.size()){
                            type = SvgPath.TYPE_PATH;
                        }
                        MySvgUtils.this.mPaths.put(path.hashCode(),new MySvgUtils.SvgPath(type,id,dst, paint));
                    }
                }
            }
        };
        this.rescaleCanvas(width, height, strokeWidth, canvas);
        return this.mPaths;
    }

    private  int mViewBoxDx = 0;
    private  int mViewBoxDy = 0;
    private  float mScale = 1;

    public void setViewBoxDx(int viewBoxDx){
        this.mViewBoxDx = viewBoxDx;
    }

    public void setViewBoxDy(int mViewBoxDy){
        this.mViewBoxDy = mViewBoxDy;
    }

    public void setViewBoxScale(float scale){
        this.mScale = scale;
    }

    private void rescaleCanvas(int width, int height, float strokeWidth, Canvas canvas) {
        if(this.mSvg != null) {
            RectF viewBox = this.mSvg.getDocumentViewBox();
            float scale = Math.min((float)width / (viewBox.width() + strokeWidth), (float)height / (viewBox.height() + strokeWidth));
            canvas.translate(((float)width - viewBox.width() * scale) / 2.0F, ((float)height - viewBox.height() * scale) / 2.0F);
            canvas.scale(scale*mScale, scale*mScale);
            canvas.translate(-mViewBoxDx, -mViewBoxDy);
            this.mSvg.renderToCanvas(canvas);
        }
    }

    public interface AnimationStepListener {
        void onAnimationStep();
    }

    public static class SvgPath {
        public static final int TYPE_ELLIPSE = 1;
        public static final int TYPE_CIRCLE = 2;
        public static final int TYPE_PATH = 3;

        private static final Region REGION = new Region();
        private static final Region MAX_CLIP = new Region(Integer.MIN_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE);
        final Path path;
        final Paint paint;
        float length;
        String id;
        int type;
        MySvgUtils.AnimationStepListener animationStepListener;
        final Rect bounds;
        final PathMeasure measure;

        SvgPath(int type,String id,Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
            this.id = id;
            this.type = type;
            this.measure = new PathMeasure(path, false);
            this.length = this.measure.getLength();
            REGION.setPath(path, MAX_CLIP);
            this.bounds = REGION.getBounds();
        }

        public void setAnimationStepListener(MySvgUtils.AnimationStepListener animationStepListener) {
            this.animationStepListener = animationStepListener;
        }

        public void setLength(float length) {
            this.path.reset();
            this.measure.getSegment(0.0F, length, this.path, true);
            this.path.rLineTo(0.0F, 0.0F);
            if(this.animationStepListener != null) {
                this.animationStepListener.onAnimationStep();
            }

        }

        public float getLength() {
            return this.length;
        }
    }
}
