package com.pffair.android_libs.rich_text.parse;

import com.pffair.android_libs.rich_text.util.EmojiDrawableUtil;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by pangff on 16/3/25.
 * Description EmojiParse
 */
public class EmojiParse extends BaseParse {

    /**
     * 解析字符串，将表情和内容分离
     */
    public static SpannableString parse(String str, int contentLineHeight) {
        SpannableString spannableString = new SpannableString(str);
        int _index = 0;
        char charArray[] = str.toCharArray();
        for (char c : charArray) {
            int position = EmojiDrawableUtil.getInstance().getPositionByCode(c);
            if(position!=-1){
                Drawable drawable = EmojiDrawableUtil.getInstance().getEmojiDrawable(position);
                if (drawable != null) {
                    int drawableW =
                            drawable.getIntrinsicWidth() * contentLineHeight / drawable
                                    .getIntrinsicHeight();
                    drawable.setBounds(0, 0, drawableW, contentLineHeight);
                    ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                    spannableString.setSpan(span, _index, _index + 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            _index++;
        }
        return spannableString;
    }


}
