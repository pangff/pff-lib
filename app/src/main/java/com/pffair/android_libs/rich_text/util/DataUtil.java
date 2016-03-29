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
                "<font color=\"#FF0000\" style=\"\" text=\"asdfasdf\" /><stock name=\"上证指数\" code=\"123456\"  />阿电视发呆"
                        + emoji[0]
                        + "adsfadsf<a href=\"http://www.baidu.com\" /><atuser uid=\"123456\" nick=\"用户名\"/> <lbs location=\"信安大厦\" /> <match id=\"12345\" text=\"优顾杯比赛\" />#Topic#";
        return data;
    }
}
