package com.pffair.android_libs.rich_text.util;

import com.BaseApplication;
import com.pffair.android_libs.R;

/**
 * Created by pangff on 16/3/28.
 * Description DataUtil
 */
public class DataUtil {

    public static String getData(int i) {
        String[] emoji = BaseApplication.mBaseApplication.getResources()
                .getStringArray(R.array.emoji_code);
        String data =
                i
                        + "<font color=\"#FF0000\" style=\"\" text=\"我是Font标签\" /><stock name=\"股票\" code=\"代码123456\"  />默认文本"
                        + emoji[0]
                        + "默认文本<a href=\"http://www.baidu.com\" alt=\"百度链接\" /><atuser uid=\"123456\" nick=\"用户XXX\"/> <lbs location=\"位置XXX\" /> #专题#"
                        + "<font color=\"#FF0000\" style=\"\" text=\"我是Font标签\" /><stock name=\"股票\" code=\"代码123456\"  />默认文本"
                        + emoji[0]
                        + "默认文本<a href=\"http://www.baidu.com\" alt=\"百度链接\" /><atuser uid=\"123456\" nick=\"用户XXX\"/> <lbs location=\"位置XXX\" /> #专题#"
                        + "<font color=\"#FF0000\" style=\"\" text=\"我是Font标签\" /><stock name=\"股票\" code=\"代码123456\"  />默认文本"
                        + emoji[0]
                        + "默认文本<a href=\"http://www.baidu.com\" alt=\"百度链接\" /><atuser uid=\"123456\" nick=\"用户XXX\"/> <lbs location=\"位置XXX\" /> #专题#"
                        + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0]
                        + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0] + emoji[0]
                        + emoji[0] + emoji[0] + emoji[0] + emoji[0];
        return data;
    }

    public static String getData2(int i) {
        String[] emoji = BaseApplication.mBaseApplication.getResources()
                .getStringArray(R.array.emoji_code);
        String data = "我持有“<stock name=\"基金科瑞\"  code=\"11500056\" />,<stock name=\"超大ETF\"  code=\"11510020\" />,<stock name=\"50ETF\"  code=\"11510050\" />,<stock name=\"浦发银行\"  code=\"11600000\" />,<stock name=\"白云机场\"  code=\"11600004\" />,<stock name=\"东风汽车\"  code=\"11600006\" />,<stock name=\"大同煤业\"  code=\"11601001\" />,<stock name=\"平安银行\"  code=\"21000001\" />,<stock name=\"鸿达兴业\"  code=\"21002002\" />,<stock name=\"瑞和小康\"  code=\"21150008\" />,<stock name=\"证券B\"  code=\"21150172\" />,<stock name=\"转债进取\"  code=\"21150189\" />,<stock name=\"天弘深成\"  code=\"21164205\" />,<stock name=\"神州泰岳\"  code=\"21300002\" />”\n";
        return data;
    }



}
