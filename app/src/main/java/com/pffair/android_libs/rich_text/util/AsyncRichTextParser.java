package com.pffair.android_libs.rich_text.util;

import com.BaseApplication;

import android.text.SpannableStringBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pangff on 16/3/29.
 * Description AsyncRichTextParser
 */
public class AsyncRichTextParser {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public interface ParseListener {

        void onParseFinished(SpannableStringBuilder stringBuilder);
    }

    public static void parseText(final String originRichText, final int contentLineHeight,
            final SpanClickListener spanClickListener, final ParseListener parseListener) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final SpannableStringBuilder stringBuilder = TextParseUtil
                        .parseText(originRichText, contentLineHeight, spanClickListener);
                if (parseListener != null) {
                    BaseApplication.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            parseListener.onParseFinished(stringBuilder);
                        }
                    });
                }
            }
        });

    }
}
