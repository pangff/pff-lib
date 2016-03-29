package com.pffair.android_libs.rich_text.util;

import com.pffair.android_libs.rich_text.parse.AtUserParse;
import com.pffair.android_libs.rich_text.parse.EmojiParse;
import com.pffair.android_libs.rich_text.parse.FontParse;
import com.pffair.android_libs.rich_text.parse.LbsParse;
import com.pffair.android_libs.rich_text.parse.LinkParse;
import com.pffair.android_libs.rich_text.parse.MatchParse;
import com.pffair.android_libs.rich_text.parse.StockParse;
import com.pffair.android_libs.rich_text.parse.TopicParse;
import com.pffair.android_libs.rich_text.parse.UserParse;

import android.text.SpannableStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pangff on 16/3/25.
 * Description TextParseUtil
 */
public class TextParseUtil {

    /**
     * html分离正则
     */
    public final static String TAGS = "[^<>]+|<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    /**
     * html正则
     */
    public final static String HTML_TAG = "<(?:.|\\s)*?>";

    /**
     * 话题正则
     */
    public final static String TOPIC_TAGS = "[^#]+|#[^#]{21,}|#[^#]{1,20}#";

    public final static String TOPIC_TAG = "#[^#]{1,20}#";

    private static final String FLAG_NEW_LINE_R_N = "\r\n";

    private static final String FLAG_NEW_LINE_R = "\r";

    static final String FLAG_NEW_LINE = "\n";

    public final static Pattern htmlPattern = Pattern.compile(TAGS);

    public final static Pattern topicPattern = Pattern.compile(TOPIC_TAGS);

    public static final String TAG_STOCK = "stock";
    public static final String TAG_USER = "user";
    public static final String TAG_AT_USER = "atuser";
    public static final String TAG_A = "a";
    public static final String TAG_MATCH = "match";
    public static final String TAG_FONT = "font";
    public static final String TAG_LBS = "lbs";
    public static final String TAG_TOPIC = "topic";


    public static SpannableStringBuilder parseText(String originRichText, int contentLineHeight,
            SpanClickListener spanClickListener) throws RuntimeException{
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        originRichText = originRichText.replaceAll(FLAG_NEW_LINE_R_N, FLAG_NEW_LINE);
        originRichText = originRichText.replaceAll(FLAG_NEW_LINE_R, FLAG_NEW_LINE);
        Matcher m = htmlPattern.matcher(originRichText);
        while (m.find()) {
            String r = m.group();
            if (r.matches(HTML_TAG)) {
                if(r.startsWith("<"+TAG_STOCK+" ")){
                    stringBuilder.append(StockParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_USER+" ")){
                    stringBuilder.append(UserParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_AT_USER+" ")){
                    stringBuilder.append(AtUserParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_A+" ")){
                    stringBuilder.append(LinkParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_MATCH+" ")){
                    stringBuilder.append(MatchParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_FONT+" ")){
                    stringBuilder.append(FontParse.parse(r,spanClickListener));
                }
                if(r.startsWith("<"+TAG_LBS+" ")){
                    stringBuilder.append(LbsParse.parse(r,spanClickListener));
                }
            } else {
                Matcher matcher = topicPattern.matcher(r);
                while (matcher.find()) {
                    String rInner = matcher.group();
                    if (rInner.matches(TOPIC_TAG)) {
                        stringBuilder.append(TopicParse.parse(rInner,spanClickListener));
                    }else{
                        stringBuilder.append(EmojiParse.parse(rInner,contentLineHeight));
                    }
                }
            }
        }
        return stringBuilder;
    }


    /**
     * 获取标签内属性
     */
    public static String getAttr(String source, String element, String attr) {
        String reg = elementAttrCache.getRegex(element, attr);
        Matcher m = Pattern.compile(reg, Pattern.DOTALL).matcher(source);
        while (m.find()) {
            if (m.groupCount() >= 3 && m.group(3) != null) {
                return StringEscapeUtils.unescapeHtml4(m.group(3));
            } else if (m.groupCount() >= 5 && m.group(5) != null) {
                return StringEscapeUtils.unescapeHtml4(m.group(5));
            }
        }
        return "";
    }


    static ElementAttrCache elementAttrCache = new ElementAttrCache();

    public static class ElementAttrCache {

        Map<MultiKey, String> elementAttrMap = new HashMap<MultiKey, String>();

        List<MultiKey> tmpKeys = new ArrayList<MultiKey>();

        private synchronized MultiKey obtainKey(String element, String attr) {
            if (tmpKeys.isEmpty()) {
                return new MultiKey(element, attr);
            } else {
                MultiKey key = tmpKeys.remove(tmpKeys.size() - 1);
                key.reset(element, attr);
                return key;
            }
        }

        private synchronized void recycle(MultiKey key) {
            tmpKeys.add(key);
        }

        public synchronized String getRegex(String element, String attr) {
            MultiKey key = obtainKey(element, attr);
            String reg = elementAttrMap.get(key);
            if (reg == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("<").append(element).append("[^<>]*?\\s+").append(attr)
                        .append("=((\"([^\"]*?)\")|('([^']*?)')).*?>");
                reg = sb.toString();
                elementAttrMap.put(key, reg);
            } else {
                recycle(key);
            }

            return reg;
        }
    }
}
