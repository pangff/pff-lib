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
 * Description MatchParse
 */
public class MatchParse extends BaseParse{

    public static int COLOR = Color.parseColor("#01A7E5");

    public static int COLOR_PRESSED = Color.parseColor("#b10000");

    public static SpannableString parse(String shouldParseTag,final SpanClickListener spanClickListener) {
        final String id = TextParseUtil.getAttr(shouldParseTag, "match", "id");
        final String text = TextParseUtil.getAttr(shouldParseTag, "match", "text");
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TouchableSpan(COLOR,COLOR_PRESSED,Color.TRANSPARENT) {
            @Override
            public void onClick(View v) {
                Map<String,String> data = new HashMap();
                data.put("id",id);
                data.put("text",text);
                spanClickListener.onClicked(v,TextParseUtil.TAG_MATCH,data);
            }

        }, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
