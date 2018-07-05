package com.duyangs.ztools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * <p>Project:ZBaseLib</p>
 * <p>Package:com.duyangs.ztools</p>
 * <p>Description:Date time tool class</p>
 * <p>Company:ximimax.cn</p>
 *
 * @author duyangs
 * @date 2018/07/05
 */
object DateTool {

    @SuppressLint("SimpleDateFormat")
    private val DATA_FORMAT_YMDHMS: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @SuppressLint("SimpleDateFormat")
    private val DATA_FORMAT_YMD: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    @SuppressLint("SimpleDateFormat")
    private val DATA_FORMAT_HMS: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")


    /**
     * Get Greenwich time (1970-present Second)
     */
    fun getGMTime1(): Long {
        DATA_FORMAT_YMDHMS.timeZone = TimeZone.getTimeZone("Etc/Greenwich")
        val format: String = DATA_FORMAT_YMDHMS.format(Date())
        val gmDate: Date = DATA_FORMAT_YMDHMS.parse(format)
        return gmDate.time / 1000
    }

    /**
     * Get Greenwich time (1970-present Millisecond)
     */
    fun getGMTime2(): Long {
        return System.currentTimeMillis()
    }

    /**
     * Convert datetime in "yyyy-MM-dd HH:mm:ss" format to a Date object
     * 将“yyyy-MM-dd HH:mm:ss”格式的日期时间转换为Date对象
     *
     * @param dateStr "yyyy-MM-dd HH:mm:ss"
     * @return Date object
     */
    fun getDateByYMDHMS(dateStr: String): Date {
        return DATA_FORMAT_YMDHMS.parse(dateStr)
    }

    /**
     * Convert datetime in "yyyy-MM-dd" format to a Date object
     * 将“yyyy-MM-dd”格式的日期时间转换为Date对象
     *
     * @param dateStr "yyyy-MM-dd"
     * @return Date object
     */
    fun getDateByYMD(dateStr: String): Date {
        return DATA_FORMAT_YMD.parse(dateStr)
    }

    /**
     * Date time converted to "yyyy-MM-dd HH:mm:ss" format
     * 日期时间转化为“yyyy-MM-dd HH:mm:ss”格式
     *
     * @param date Data Object
     * @return formatted date and time
     */
    fun getDateStringYMDHMS(date: Date): String {
        return DATA_FORMAT_YMDHMS.format(date)

    }

    /**
     * Date time converted to "yyyy-MM-dd" format
     * 日期时间转化为“yyyy-MM-dd”格式
     *
     * @param date Data Object
     * @return formatted date and time
     */
    fun getDateStringYMD(date: Date): String {
        return DATA_FORMAT_YMD.format(date)
    }

    /**
     * Date time converted to "HH:mm:ss" format
     * 日期时间转化为“HH:mm:ss”格式
     *
     * @param date Data Object
     * @return formatted date and time
     */
    fun getDateStringHMS(date: Date): String {
        return DATA_FORMAT_HMS.format(date)
    }

    /**
     * Get the formatted date and time
     * 获取格式化后的日期时间
     *
     * @param date Data Object
     * @param formatString SimpleDateFormat parameter eg:"yyyy-MM-dd HH:mm:ss"
     * @return formatted date and time
     */
    @SuppressLint("SimpleDateFormat")
    fun getDateString(date: Date, formatString: String): String {
        val format = SimpleDateFormat(formatString)
        return format.format(date)
    }

    /**
     * Get the formatted date and time
     * 获取格式化后的日期时间
     *
     * @param date Data Object
     * @param format SimpleDateFormat Object
     * @return formatted date and time
     */
    fun getDateString(date: Date, format: SimpleDateFormat): String {
        return format.format(date)
    }


    /**
     * Get the year, month, day, hour, minute, and second of the current time
     * 获取当前时间的年月日时分秒
     *
     * @return "$year 年 $month 月 $day 日 $hour 时 $minute 分 $second 秒"
     */
    fun current(): String {
        val c: Calendar = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val second = c.get(Calendar.SECOND)
        return "$year 年 $month 月 $day 日 $hour 时 $minute 分 $second 秒"
    }

    /**
     * Get yesterday's date accurate to the day
     * 得到昨天的日期 精确到天
     *
     * @return Date Object
     */
    fun getYesterdayDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return calendar.time
    }

    /**
     * Get today's date accurate to the day
     * 得到今天的日期 精确到天
     *
     * @return Date Object
     */
    fun getTodayDate(): Date {
        return Date()
    }

    /**
     * Get the date of tomorrow, accurate to the day
     * 得到明天的日期 精确到天
     *
     * @return Date Object
     */
    fun getTomorrowDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        return calendar.time
    }


    /**
     * Determine if it is greater than the current time
     * 判断是否大于当前时间
     *
     * @param timeStamp yyyy-MM-dd HH:mm:ss
     * @return boolean Is it greater than 是否大于
     */
    fun judgeCurrTime(timeStamp: String): Boolean {
        val date = DATA_FORMAT_YMDHMS.parse(timeStamp)
        val t = date.time
        return judgeCurrTime(t)
    }

    /**
     * Determine if it is greater than the current time
     * 判断是否大于当前时间
     *
     * @param timeStamp 时间戳
     * @return boolean  Is it greater than 是否大于
     */
    fun judgeCurrTime(timeStamp: Long): Boolean {
        val round = System.currentTimeMillis()
        return timeStamp - round > 0
    }

    /**
     * Convert a time into a prompt time string, like just 1 second ago
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return Time prompt 时间提示
     */
    fun convertTimeToFormat(timeStamp: String): String {
        val curTime = System.currentTimeMillis()
        val time = curTime - timeStamp.toLong()

        return when {
            time in 0..59 -> "刚刚"
            time in 60..3599 -> "${time / 60}分钟前"
            time >= 3600 && time < 3600 * 24 -> "${time / 3600}小时前"
            time >= 3600 * 24 && time < 3600 * 24 * 30 -> "${time / 3600 / 24}天前"
            time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12 -> "${time / 3600 / 24 / 30}个月前"
            time >= 3600 * 24 * 30 * 12 -> "${time / 3600 / 24 / 30 / 12}年前"
            else -> "上古时期"
        }
    }

    private const val WEEKDAYS: Int = 7
    //Weekday character array星期字符数组
    private val WEEK = arrayListOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")

    /**
     * Convert date variable to corresponding week string
     * 日期变量转成对应的星期字符串
     *
     * @param date Date Object
     * @return Weekday
     */
    fun dateToWeek(date: Date): String? {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dayIndex = calendar.get(Calendar.DAY_OF_WEEK)
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null
        }
        return WEEK[dayIndex - 1]
    }

    /**
     * Date time format conversion
     * 将原日期格式转换为目标格式
     *
     * @param typeFrom Original format
     * @param typeTo   Target format
     * @param value    Raw data
     * @return Target data
     */
    fun stringDateToStringData(typeFrom: String, typeTo: String,
                               value: String): String {
        @SuppressLint("SimpleDateFormat")
        val sdfFrom = SimpleDateFormat(typeFrom)
        @SuppressLint("SimpleDateFormat")
        val sdfTo = SimpleDateFormat(typeTo)
        return sdfTo.format(sdfFrom.parse(value))
    }

    /**
     * How many days of the month
     * 获取本月有多少天
     *
     * @param year
     * @param month
     * @return days
     */
    fun getMonthLastDay(year: Int, month: Int): Int {
        if (month == 0) {
            return 0
        }
        val a = Calendar.getInstance()
        a.set(Calendar.YEAR, year)
        a.set(Calendar.MONTH, month - 1)
        a.set(Calendar.DATE, 1)// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1)// 日期回滚一天，也就是最后一天
        return a.get(Calendar.DATE)
    }

    /**
     * Get the year
     * 获取当前年份
     *
     * @return Years
     */
    fun getCurrentYear(): String {
        return Calendar.getInstance().get(Calendar.YEAR).toString()
    }

    /**
     * Get the month
     * 获取当前月份
     *
     * @return Month
     */
    fun getCurrentMonth(): String {
        return (Calendar.getInstance().get(Calendar.MONTH) + 1).toString()
    }


    /**
     * Get the date of the day
     * 获取当前天
     *
     * @return Day
     */
    fun getCurrDay(): String {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
    }

    /**
     * Get a few days/week/month/year Later date，Integer push back,Negative move forward
     * 获取一段时间(天/周/月/年)之后的日期，正数往后，负数往前
     *
     * @param calendar time
     * @param calendarType Calendar.DATE,Calendar.WEEK_OF_YEAR,Calendar.MONTH,Calendar.
     *                     YEAR
     * @param next Number of shifts
     * @return date
     */
    fun getDayByDate(calendar: Calendar, calendarType: Int, next: Int): String {
        calendar.add(calendarType, next)
        val date = calendar.time
        return DATA_FORMAT_YMD.format(date)
    }
}
