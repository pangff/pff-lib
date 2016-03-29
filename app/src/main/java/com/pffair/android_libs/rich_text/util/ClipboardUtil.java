package com.pffair.android_libs.rich_text.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by pangff on 16/3/29.
 * Description ClipboardUtil
 */
public class ClipboardUtil {

    /**
     * 复制成功
     * @param mContext
     * @param text
     */
    public static void setCopyText(final Context mContext, String text) {
        ClipboardManager copy = (ClipboardManager) mContext.getApplicationContext()
                .getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setPrimaryClip(ClipData.newPlainText(null, text));
    }

}
