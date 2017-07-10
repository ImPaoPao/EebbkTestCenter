package com.eebbk.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/7/7.
 */

public class BbkCommonUtils {
    public static long getTime(String time1, String time2) {
        //两次操作差值
        long result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf.parse(time1);
            d2 = sdf.parse(time2);
            result = d1.getTime() - d2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
