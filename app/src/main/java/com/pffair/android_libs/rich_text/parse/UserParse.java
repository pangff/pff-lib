package com.pffair.android_libs.rich_text.parse;

import com.pffair.android_libs.rich_text.util.TextParseUtil;
import com.pffair.android_libs.rich_text.util.SpanClickListener;
import com.pffair.android_libs.rich_text.util.TouchableSpan;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pangff on 16/3/28.
 * Description UserParse
 */
public class UserParse extends BaseParse {

    public static int COLOR = Color.parseColor("#01A7E5");

    public static int COLOR_PRESSED = Color.parseColor("#b10000");

    public static SpannableString parse(String shouldParseTag,final SpanClickListener spanClickListener) {
        final String uid = TextParseUtil.getAttr(shouldParseTag, "user", "uid");
        final String name = TextParseUtil.getAttr(shouldParseTag, "user", "nick");
        SpannableString spannableString = new SpannableString("@"+name);
        spannableString.setSpan(new TouchableSpan(COLOR,COLOR_PRESSED,Color.TRANSPARENT) {
            @Override
            public void onClick(View v) {
                Map<String,String> data = new HashMap();
                data.put("uid",uid);
                data.put("nick",name);
                spanClickListener.onClicked(v,TextParseUtil.TAG_USER,data);
            }

        }, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
