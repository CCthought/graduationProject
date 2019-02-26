package com.adai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author Adai
 * create 2019-02-25  19:53
 */
public class DateFormatUtil {

    public static String longToString(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time * 1000));
    }
}
