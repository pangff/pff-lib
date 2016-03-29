package com.pffair.android_libs.rich_text.util;

import com.BaseApplication;
import com.pffair.android_libs.R;

/**
 * Created by pangff on 16/3/28.
 * Description DataUtil
 */
public class DataUtil {

    public static String getData() {
        String[] emoji = BaseApplication.mBaseApplication.getResources()
                .getStringArray(R.array.emoji_code);
        String data =
                "<font color=\"#FF0000\" style=\"\" text=\"我是Font标签\" /><stock name=\"股票\" code=\"代码123456\"  />默认文本"
                        + emoji[0]
                        + "默认文本<a href=\"http://www.baidu.com\" alt=\"百度链接\" /><atuser uid=\"123456\" nick=\"用户XXX\"/> <lbs location=\"位置XXX\" /> #专题#";
        return data;
    }
}
