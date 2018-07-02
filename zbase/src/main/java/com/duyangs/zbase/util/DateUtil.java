package com.duyangs.zbase.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description:日期时间工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/05/02 0002
 */
public class DateUtil {
    /**
     * 获取格林威治时间(1970年至今的秒数)
     */
    public static long getGMTime1() {
        date_Formater_1.setTimeZone(TimeZone.getTimeZone("Etc/Greenwich"));
        String format = date_Formater_1.format(new Date());
        Date gmDate = null;
        try {
            gmDate = date_Formater_1.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert gmDate != null;
        return gmDate.getTime() / 1000;
    }

    /**
     * 获取格林威治时间 即1970年至今的秒数
     */
    public static long getGMTime2() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取时间HH:mm:ss
     *
     * @return 当前时间
     */
    public static String getCurrentTime() {
        String time = null;
        String date = date_Formater_1.format(new Date());
        //"\\s"以空格截断
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 获取当前时间的年月日时分秒
     *
     * @return 当前时间
     */
    public static String current() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
    }

    /**
     * @return 得到昨天的日期 精确到天
     */
    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return date_Formater_2.format(calendar.getTime());
    }

    /**
     * @return 得到今天的日期 精确到天
     */
    public static String getTodayDate() {
        return date_Formater_2.format(new Date());
    }

    /**
     *
     * @return 得到明天的日期 精确到天
     */
    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return date_Formater_2.format(calendar.getTime());
    }

    /**
     * 时间转化为时间(几点)
     *
     * @param time 时间戳
     * @return 时间
     */
    public static String timeStampToTime(long time) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time * 1000);
    }

    /**
     * 将日期格式转化为时间(秒数)
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 时间戳
     */
    public static long getStringToDate(String time) {
        Date date = new Date();
        try {
            date = date_Formater_1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 将日期格式转化为时间(秒数)
     *
     * @param time yyyy-MM-dd
     * @return 时间戳
     */
    public static long getString2Date(String time) {
        Date date = new Date();
        try {
            date = date_Formater_2.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return boolean
     */
    public static boolean judgeCurrTime(String time) {
        Date date;
        try {
            date = date_Formater_1.parse(time);
            long t = date.getTime();
            long round = System.currentTimeMillis();
            return t - round > 0;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time 时间戳
     * @return boolean
     */
    public static boolean judgeCurrTime(long time) {
        long round = System.currentTimeMillis();
        return time - round > 0;
    }

    /**
     * 比较后面的时间是否大于前面的时间
     *
     * @param time1 时间 yyyy-MM-dd HH:mm:ss
     * @return Boolean
     */
    public static boolean judgeTime2Time(String time1, String time2) {
        try {
            //转化为时间
            Date date1 = date_Formater_1.parse(time1);
            Date date2 = date_Formater_1.parse(time2);
            //获取秒数作比较
            long l1 = date1.getTime() / 1000;
            long l2 = date2.getTime() / 1000;
            return l2 - l1 > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 得到日期 yyyy-MM-dd
     *
     * @param time 时间戳
     * @return 时间
     */
    public static String formatDate(long time) {
        return date_Formater_1.format(time * 1000);
    }

    /**
     * 得到时间 HH:mm:ss
     *
     * @param timeStamp 时间戳
     * @return 时间
     */
    public static String getTime(long timeStamp) {
        String time = null;
        String date = date_Formater_1.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 将一个时间转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp 时间戳
     * @return 时间
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        return time / 60 + "";
    }

    /**
     * 获得当前时间差
     *
     * @param timeStamp 时间戳
     * @return 时间
     */
    public static int nowCurrentTime(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = timeStamp - curTime;
        return (int) time;
    }


    /**
     * 获取当前的时 -->flag==true
     * 获取当前的分 -->flag==false
     *
     * @return 时间
     */
    public static String nowCurrentPoint(boolean flag) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(System.currentTimeMillis());
        String[] split = date.split(":");
        String hour;
        String minute;
        if (flag) {
            if (split.length > 1) {
                hour = split[0];
                return hour;
            }
        } else {
            if (split.length > 1) {
                minute = split[1];
                return minute;
            }
        }
        return null;
    }

    /**
     * 将标准时间格式HH:mm:ss化为当前的时间差值
     *
     * @param str 时间
     * @return 时间
     */
    public static String StandardFormatStr(String str) {
        try {
            Date d = date_Formater_1.parse(str);
            long timeStamp = d.getTime();

            long curTime = System.currentTimeMillis() / (long) 1000;
            long time = curTime - timeStamp / 1000;
            return time / 60 + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp 时间戳
     * @return 时间
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }




    private static final int WEEKDAYS = 7;
    //星期字符数组
    private static String[] WEEK = {"周日", "周一", "周二", "周三",
            "周四", "周五", "周六"};

    /**
     * 日期变量转成对应的星期字符串
     *
     * @param date 时间
     * @return 星期
     */
    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    /**
     * 日期时间格式转换
     *
     * @param typeFrom 原格式
     * @param typeTo   转为格式
     * @param value    传入的要转换的参数
     * @return 时间
     */
    public static String stringDateToStringData(String typeFrom, String typeTo,
                                                String value) {
        String re = value;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdfFrom = new SimpleDateFormat(typeFrom);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdfTo = new SimpleDateFormat(typeTo);

        try {
            re = sdfTo.format(sdfFrom.parse(re));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 得到这个月有多少天
     *
     * @param year 年
     * @param month 月
     * @return 天数
     */
    public static int getMonthLastDay(int year, int month) {
        if (month == 0) {
            return 0;
        }
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        return a.get(Calendar.DATE);
    }

    /**
     * 得到年份
     *
     * @return 年份
     */
    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "";
    }

    /**
     * 得到月份
     *
     * @return 月份
     */
    public static String getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return (c.get(Calendar.MONTH) + 1) + "";
    }

    /**
     * 获得当天的日期
     *
     * @return 日期
     */
    public static String getCurrDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH) + "";
    }

    /**
     * 得到几天/周/月/年后的日期，整数往后推,负数往前移动
     *
     * @param calendar 时间
     * @param calendarType Calendar.DATE,Calendar.WEEK_OF_YEAR,Calendar.MONTH,Calendar.
     *                     YEAR
     * @param next 推移数量
     * @return 日期
     */
    public static String getDayByDate(Calendar calendar, int calendarType, int next) {

        calendar.add(calendarType, next);
        Date date = calendar.getTime();
        return date_Formater_1.format(date);

    }

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat date_Formater_1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");


    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat date_Formater_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDate(String dateStr) {
        Date date = new Date();
        if (TextUtils.isEmpty(dateStr)) {
            return date;
        }
        try {
            date = date_Formater_1.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;

    }

    public static String getDataString_1(Date date) {
        if (date == null) {
            date = new Date();
        }
        return date_Formater_1.format(date);

    }

    public static String getDataString_2(Date date) {
        if (date == null) {
            date = new Date();
        }
        return date_Formater_2.format(date);

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDataString(Date date, String formatString) {
        SimpleDateFormat format;
        if (formatString == null) {
            format = date_Formater_1;
        } else {
            format = new SimpleDateFormat(formatString);
        }

        if (date == null){
            date = new Date();
        }

        return format.format(date);
    }

    public static String getDataString(Date date, SimpleDateFormat format){
        if (format == null){
            format = date_Formater_1;
        }

        if (date == null){
            date = new Date();
        }

        return format.format(date);
    }


}
