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
 * Created by pangff on 16/3/25.
 * Description StockParse
 */
public class StockParse extends BaseParse {

    public static int COLOR = Color.parseColor("#FDAF45");

    public static int COLOR_PRESSED = Color.parseColor("#0069a6");

    public static SpannableString parse(String shouldParseTag,final SpanClickListener spanClickListener) {
        SpannableString spannableString ;
        final String code = TextParseUtil.getAttr(shouldParseTag, "stock", "code");
        final String name = TextParseUtil.getAttr(shouldParseTag, "stock", "name");
        final String title = TextParseUtil.getAttr(shouldParseTag, "stock", "title");
        if (!TextUtils.isEmpty(title)) {
            spannableString = new SpannableString(title);
        } else {
            spannableString = new SpannableString(name + "(" + code + ")");
        }
        spannableString.setSpan(new TouchableSpan(COLOR,COLOR_PRESSED,Color.TRANSPARENT) {
            @Override
            public void onClick(View v) {
                Map<String,String> data = new HashMap();
                data.put("code",code);
                data.put("name",name);
                spanClickListener.onClicked(v,TextParseUtil.TAG_STOCK,data);
            }

        }, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
