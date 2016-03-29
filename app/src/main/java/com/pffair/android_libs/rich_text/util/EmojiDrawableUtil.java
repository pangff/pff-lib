// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

// Decompiler options: fullnames definits braces deadcode fieldsfirst

package com.pffair.android_libs.rich_text.util;


import com.BaseApplication;
import com.pffair.android_libs.R;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EmojiDrawableUtil {

    private static EmojiDrawableUtil emojiDrawableUtil = null;

    private char code[] = null;

    private int positions[] = null;

    private char sortCodes[] = null;

    private int sortPositions[] = null;

    Context mContext;



    // 内存缓存图片
    private static Map<Integer, Drawable> activityViewCache = new LinkedHashMap<Integer, Drawable>(
            50, 0.75f, true) {

        private static final long serialVersionUID = 1L;

        @Override
        protected boolean removeEldestEntry(Entry<Integer, Drawable> eldest) {
            return true;
        }
    };

    /**
     * 单例
     */
    public static synchronized EmojiDrawableUtil getInstance() {
        if (emojiDrawableUtil == null) {
            emojiDrawableUtil = new EmojiDrawableUtil();
        }
        return emojiDrawableUtil;
    }

    /**
     * 构造方法
     */
    private EmojiDrawableUtil() {
        mContext = BaseApplication.mBaseApplication;
        String[] stringArray = mContext.getResources().getStringArray(R.array.emoji_code);
        code = new char[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            code[i] = stringArray[i].charAt(0);
        }
        positions = mContext.getResources().getIntArray(R.array.emoji_file);
        EmojiChar[] temp = new EmojiChar[Math.min(positions.length, code.length)];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new EmojiChar(code[i], positions[i]);
        }
        Arrays.sort(temp, EmojiChar.getComparator());
        sortCodes = new char[temp.length];
        sortPositions = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            sortCodes[i] = temp[i].getCode();
            sortPositions[i] = temp[i].getPosition();
        }
    }


    /**
     * 根据位置获取图片
     */
    public Drawable getEmojiDrawable(int pos) {
        if (activityViewCache.containsKey(pos)) {
            return activityViewCache.get(pos);
        }
        int j = mContext.getResources()
                .getIdentifier("emoji_" + pos, "drawable", mContext.getPackageName());
        if (j == 0) {
            return null;
        }
        BitmapDrawable drawable = (BitmapDrawable) mContext.getResources().getDrawable(j);
        activityViewCache.put(pos, drawable);
        return drawable;
    }

    /**
     * 获取位置数组
     */
    public int[] getPositions() {
        return positions;
    }

    /**
     * 根据code获取图片
     */
    public Drawable getEmojiDrawableByCode(char code) {
        return getEmojiDrawable(getPositionByCode(code));
    }

    /**
     * 根据位置获取code
     */
    public String getCodeByPosition(int i) {
        String s;
        char[] code2 = getInstance().code;
        if (i < code2.length) {
            s = String.valueOf(code2[i]);
        } else {
            s = "";
        }
        return s;
    }


    /**
     * 根据code获取位置
     */
    public int getPositionByCode(char code) {
        char[] sortCodes2 = sortCodes;
        int[] sortPositions2 = sortPositions;
        int newIndex = Arrays.binarySearch(sortCodes2, code);
        if (newIndex < 0) {
            return -1;
        } else {
            return sortPositions2[newIndex];
        }
    }

    /**
     * 根据code获取位置
     */
    public String replaceEmojiCode(String str) {
        char[] sortCodes2 = sortCodes;
        char[] strA = str.toCharArray();
        for (int i = 0; i < strA.length; i++) {
            int newIndex = Arrays.binarySearch(sortCodes2, strA[i]);
            if (newIndex >= 0) {
                strA[i] = '\n';
            }
        }
        return String.valueOf(strA);
    }

    /**
     * 获取code数组
     */
    public char[] getCodeArray() {
        return code;
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }


    static class EmojiChar implements Comparable<EmojiChar> {

        private char mCode;

        private int mPostion;


        public EmojiChar(char code, int postion) {
            mCode = code;
            mPostion = postion;
        }

        public char getCode() {
            return mCode;
        }

        public int getPosition() {
            return mPostion;
        }

        @Override
        public int compareTo(EmojiChar another) {
            return mCode - another.getCode();
        }

        public static Comparator<EmojiChar> getComparator() {
            Comparator<EmojiChar> c = new Comparator<EmojiChar>() {

                @Override
                public int compare(EmojiChar lhs, EmojiChar rhs) {
                    return lhs.getCode() - rhs.getCode();
                }

            };
            return c;
        }
    }

}
