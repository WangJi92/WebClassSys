package com.hdu.cms.common.Utils;

/**
 * Created by JetWang on 2016/10/1.
 */
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class DateUtil {

    // log4j
    private static Logger log = Logger.getLogger(DateUtil.class);

    /**
     * @description 如果该字符串匹配yyyy-MM-dd HH:mm:ss或yyyy-MM-dd格式，
     *              则将其转换成一个Timestamp对象，否则返回null
     * @param dateString
     *            yyyy-MM-dd HH:mm:ss或yyyy-MM-dd格式字符串
     * @return Timestamp
     */
    public static Timestamp convertStringToDefaultTimestamp(String dateString) {
        try {
            if (!StringUtils.isEmpty(dateString)) {
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .parse(dateString);
                } catch (Exception e) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                }
                return new Timestamp(date.getTime());
            }
        } catch (Exception e) {
            log.error("将字符串转换成Timestamp出错：" + dateString, e);
        }
        return null;
    }

    /**
     * @description 如果该字符串匹配yyyy-MM-dd HH:mm:ss， 则将其转换成一个Timestamp对象，否则返回null
     *
     * @param dateString
     *            yyyy-MM-dd HH:mm:ss字符串
     * @return Timestamp
     */
    public static Timestamp convertStringToTimestampDefaultNull(
            String dateString) {
        Date date = convertStringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
        if (null != date) {
            return new Timestamp(date.getTime());
        } else {
            return null;
        }
    }

    /**
     * @description 如果该字符串匹配yyyy-MM-dd HH:mm:ss， 则将其转换成一个Timestamp对象，否则返回当前时间
     *
     * @param dateString
     *            yyyy-MM-dd HH:mm:ss字符串
     * @return Timestamp
     */
    public static Timestamp convertStringToTimestampDefalutNow(String dateString) {
        Date date = convertStringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
        if (null != date) {
            return new Timestamp(date.getTime());
        } else {
            return new Timestamp(System.currentTimeMillis());
        }
    }

    /**
     * @description 将字符串转换成指定格式的Date对象，如果格式错误，则返回null
     *
     * @param dateString
     *            日期格式字符串
     * @param parttern
     *            日期和时间格式的模式
     * @return Date
     */
    public static Date convertStringToDate(String dateString, String parttern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(parttern);
        Date date = null;
        try {
            if (!StringUtils.isEmpty(dateString)) {
                date = dateFormat.parse(dateString);
            }
        } catch (Exception e) {
            log.error("将字符串转换成Date出错,dateString：" + dateString + ", parttern："
                    + parttern, e);
        }
        return date;
    }

    /**
     * @description 将Date（java.sql.Timestamp extends
     *              java.util.Date）对象转换成指定格式的字符串，如果出错，则返回默认值
     *
     * @param date
     *            日期，此处参数亦可传入Timestamp对象
     * @param parttern
     *            日期和时间格式的模式
     * @param defaultValue
     *            默认值
     * @return String
     */
    public static String convertDateTimeToString(Date date, String parttern,
                                                 String defaultValue) {
        if (null != date) {
            return new SimpleDateFormat(parttern).format(date);
        } else {
            return defaultValue;
        }
    }

    /**
     * @description 将字符串转换成指定格式的Calendar对象
     *
     * @param dateString
     *            日期格式字符串
     * @param parttern
     *            日期和时间格式的模式
     * @return Calendar
     */
    public static Calendar convertStringToCalendar(String dateString,
                                                   String parttern) {
        Calendar cal = Calendar.getInstance();
        try {
            if (!StringUtils.isEmpty(dateString)) {
                Date d = new SimpleDateFormat(parttern).parse(dateString);
                cal.setTime(d);
            }
        } catch (Exception e) {
            log.error("字符串转换过程中出错,dateString：" + dateString + ", parttern："
                    + parttern, e);
        }
        return cal;
    }

    /**
     * @description 将Timestamp对象转换成yyyy-MM-dd HH:mm:ss.SSS格式的字符串，
     *              如果出错，则返回空（如果想转换成指定格式的String对象 ，请调用方法convertDateToString()）
     *
     * @param timestamp
     *            日期
     * @return String
     */
    public static String convertTimestampToString(Timestamp timestamp) {
        return convertDateTimeToString(timestamp, "yyyy-MM-dd HH:mm:ss.SSS", "");
    }
    public static String convertTimestampToStrings(Timestamp timestamp) {
        return convertDateTimeToString(timestamp, "yyyy-MM-dd HH:mm:ss", "");
    }

    /**
     * @description 在某个日期上增加一定的天数
     *
     * @param date
     *            日期
     * @param num
     *            天数
     * @return Date 增加天数后的日期
     */
    public static Date addDay(Date date, int num) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.add(Calendar.DAY_OF_MONTH, num);
        return startDate.getTime();
    }

    /**
     * @description 判断某年某月有多少天
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return int 天数
     */
    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
