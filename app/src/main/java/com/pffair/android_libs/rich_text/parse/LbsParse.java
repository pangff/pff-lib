package com.pffair.android_libs.rich_text.parse;

import com.BaseApplication;
import com.pffair.android_libs.R;
import com.pffair.android_libs.rich_text.util.TextParseUtil;
import com.pffair.android_libs.rich_text.util.SpanClickListener;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by pangff on 16/3/28.
 * Description LbsParse
 */
public class LbsParse extends BaseParse{

    public static SpannableString parse(String shouldParse,final SpanClickListener spanClickListener) {
//        final String lon = TextParseUtil.getAttr(shouldParse, "lbs", "lon");
//        final String lat = TextParseUtil.getAttr(shouldParse, "lbs", "lat");
        final String location = TextParseUtil.getAttr(shouldParse, "lbs", "location");
        SpannableString spannableString = new SpannableString("我在: "+location);
        Drawable drawable = BaseApplication.mBaseApplication.getResources().getDrawable(R.drawable.position_pic);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, 3, 4,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
}
