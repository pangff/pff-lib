package com.pffair.android_libs.rich_text.parse;

import com.pffair.android_libs.rich_text.util.TextParseUtil;
import com.pffair.android_libs.rich_text.util.SpanClickListener;
import com.pffair.android_libs.rich_text.util.TouchableSpan;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pangff on 16/3/28.
 * Description LinkParse
 */
public class LinkParse extends BaseParse{

    public static int COLOR = Color.parseColor("#01A7E5");

    public static int COLOR_PRESSED = Color.parseColor("#b10000");

    public static SpannableString parse(String shouldParse,final SpanClickListener spanClickListener) {
        SpannableString spannableString = null;
        final String url = TextParseUtil.getAttr(shouldParse, "a", "href");
        final String alt = TextParseUtil.getAttr(shouldParse, "a", "alt");
        if (!TextUtils.isEmpty(alt)) {
            spannableString = new SpannableString(""+alt);
        } else {
            spannableString = new SpannableString("网页链接");
        }
        spannableString.setSpan(new TouchableSpan(COLOR,COLOR_PRESSED,Color.TRANSPARENT) {
            @Override
            public void onClick(View v) {
                Map<String,String> data = new HashMap();
                data.put("url",url);
                data.put("alt",alt);
                spanClickListener.onClicked(v,TextParseUtil.TAG_A,data);
            }

        }, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
