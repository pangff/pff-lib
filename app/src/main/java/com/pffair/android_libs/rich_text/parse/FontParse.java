package com.pffair.android_libs.rich_text.parse;

import com.pffair.android_libs.rich_text.util.TextParseUtil;
import com.pffair.android_libs.rich_text.util.SpanClickListener;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by pangff on 16/3/28.
 * Description FontParse
 */
public class FontParse extends BaseParse{

    public static Spanned parse(String shouldParseTag,final SpanClickListener spanClickListener) {
        final String text = TextParseUtil.getAttr(shouldParseTag, "font", "text");
        final String color = TextParseUtil.getAttr(shouldParseTag, "font", "color");
        final String style = TextParseUtil.getAttr(shouldParseTag, "font", "style");
       return Html.fromHtml("<font color=\""+color+"\" style=\""+style+"\">"+text+"</font>");
    }
}
