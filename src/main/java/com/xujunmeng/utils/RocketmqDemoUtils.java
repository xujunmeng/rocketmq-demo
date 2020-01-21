package com.xujunmeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author james
 * @date 2020/1/21
 */
public class RocketmqDemoUtils {

    public static String getDtStr(Date date) {
        return convertDate2String("yyyy-MM-dd HH:mm:ss:SSS", date);
    }

    public static String convertDate2String(String dateFormat, Date date) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(dateFormat);
            return e.format(date);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

}
