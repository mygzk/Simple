package com.example.simple.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by guozhk on 2017/12/13.
 */

public class DateUtil {

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd HH:mm
     **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式：yyyy-MM-dd
     **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式：HH:mm:ss
     **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /**
     * 日期格式：HH:mm
     **/
    public static final String DF_HH_MM = "HH:mm";


    /***
     * 字符串转
     * @param dateL
     * @param df
     * @return
     */
    public static String formatDateTime(long dateL, String df) {
        SimpleDateFormat sdf = new SimpleDateFormat(df);
        Date date = new Date(dateL);
        return sdf.format(date);
    }

    /**
     * 把指定格式的时间 转化为对应的时间戳
     *
     * @param time
     * @param df
     * @return
     */
    public static long dataTime(String time, String df) {
        SimpleDateFormat sdr = new SimpleDateFormat(df,
                Locale.CHINA);
        Date date;
        long l = 0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 时间戳转字符串
     *
     * @param timeStamp 时间戳
     * @return 时间格式
     */
    public static String getStrTime(String timeStamp, String df) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(df);
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));
        return timeString;
    }


    public static void main(String[] agrs){
        System.out.println(dataTime("2017-12-12 23:22",DF_YYYY_MM_DD_HH_MM));
        System.out.println(getStrTime("1513128813000",DF_YYYY_MM_DD_HH_MM));
    }
}
