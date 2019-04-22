package com.ningyang.os.action.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 年月日时分秒(无下划线) yyyyMMddHHmmss
     */
    public static final String dtLong = "yyyyMMddHHmmss";
    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    /**
     * 完整时间 HH:mm:ss
     */
    public static final String simpleTime = "HH:mm:ss";
    /**
     * 年月日(无下划线) yyyyMMdd
     */
    public static final String dtShort = "yyyyMMdd";
    /**
     * 年月日 yyyy-MM-dd
     */
    public static final String simpleDtShort = "yyyy-MM-dd";
    /**
     * 年月日 MM-dd
     */
    public static final String simpleMonthDtShort = "MM-dd";
    /**
     * 年份
     */
    public static final String simpleYearShort = "yyyy";
    /**
     * 月份
     */
    public static final String simpleMonthShort = "MM";
    /**
     * 年月 yyyyMM
     */
    public static final String simpleYearMonthShort = "yyyyMM";
    /**
     * yyyyMMddHHmmss
     */
    public static final String simpleTimeShort = "yyyyMMddHHmmss";


    /**
     * 日期格式
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 日期格式
     */
    private final static ThreadLocal<SimpleDateFormat> dateYearMonthFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMM");
        }
    };

    /**
     * 日期格式
     */
    private final static ThreadLocal<SimpleDateFormat> dateMonthFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd");
        }
    };

    /**
     * 时间格式
     */
    private final static ThreadLocal<SimpleDateFormat> timeFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    private final static ThreadLocal<SimpleDateFormat> dateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     *
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyy-MM-dd
     *
     * @return
     */
    public static String getSimpleDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simpleDtShort);
        return df.format(date);
    }

    /**
     * 获取系统年份 格式： yyyy
     *
     * @return
     */
    public static String getSimpleYearShort() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simpleYearShort);
        return df.format(date);
    }

    /**
     * 获取系统月份 格式： MM
     *
     * @return
     */
    public static String getSimpleMonthShort() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simpleMonthShort);
        return df.format(date);
    }

    /**
     * 获取系统年月 格式： yyyyMM
     *
     * @return
     */
    public static String getSimpleYearMonthShort() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simpleYearMonthShort);
        return df.format(date);
    }

    /**
     * 产生随机的三位数
     *
     * @return
     */
    public static String getThree() {
        Random rad = new Random();
        return rad.nextInt(1000) + "";
    }

    /**
     * 日期转换为字符串:yyyy-MM-dd
     */
    public static String dateToStr(Date date) {
        if (date != null)
            return dateFormat.get().format(date);
        return null;
    }

    /**
     * 日期转换为字符串:yyyyMM
     *
     * @param date
     * @return
     */
    public static String dateYearMonthToStr(Date date) {
        if (date != null)
            return dateYearMonthFormat.get().format(date);
        return null;
    }

    /**
     * 日期转换为字符串:MM-dd
     */
    public static String dateMonthToStr(Date date) {
        if (date != null)
            return dateMonthFormat.get().format(date);
        return null;
    }

    /**
     * 返回数据为 YYYY-MM-DD
     *
     * @param str
     * @return
     */
    public static String dateToStrToDate(String str) {
        DateFormat df = new SimpleDateFormat(simpleDtShort);
        return df.format(strToDate(str));
    }


    public static String dateToStrToTime(String str) {
        DateFormat df = new SimpleDateFormat(simpleTimeShort);
        return df.format(strToDate(str));
    }


    /**
     * 返回数据为 YYYY-MM-DD
     *
     * @param str
     * @return
     */
    public static String dateToDate(Date str) {
        DateFormat df = new SimpleDateFormat(simpleDtShort);
        return df.format(str);
    }

    /**
     * 返回数据为 HH:mm:ss
     *
     * @param str
     * @return
     */
    public static String timeToStrToDate(String str) {
        DateFormat df = new SimpleDateFormat(simpleTime);
        return df.format(strToTime(str));
    }

    /**
     * 返回数据为 HH
     *
     * @param str
     * @return
     */
    public static String hourToStrToDate(String str) {
        DateFormat df = new SimpleDateFormat("HH");
        return df.format(strToTime(str));
    }

    /**
     * 返回数据为 yyyyMM
     *
     * @param str
     * @return
     */
    public static String timeToStrToYearMonth(String str) {
        DateFormat df = new SimpleDateFormat(simpleYearMonthShort);
        return df.format(strToTime(str));
    }

    /**
     * 返回数据为 yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static String timeToStrToStr(String str) {
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(strToTime(str));
    }

    /**
     * 时间转换为字符串:yyyy-MM-dd HH:mm:ss
     */
    public static String timeToStr(Date date) {
        if (date != null)
            return timeFormat.get().format(date);
        return null;
    }

    /**
     * @param date
     * @return
     */
    public static String dateTimeToStr(Date date) {
        if (date != null)
            return dateTimeFormat.get().format(date);
        return null;
    }

    /**
     * 字符串转换为日期:yyyy-MM-dd
     */
    public static Date strToDate(String str) {
        Date date = null;
        try {
            date = dateFormat.get().parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换为日期:yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Date strToDateTime(String str) {
        Date date = null;
        try {
            date = timeFormat.get().parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换为时间:yyyy-MM-dd HH:mm:ss
     */
    public static Date strToTime(String str) {
        Date date = null;
        try {
            date = timeFormat.get().parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 周几
     *
     * @param str
     * @return
     */
    public static String getWeek(String str) {
        Date date = null;
        String week = "";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINA);
        try {
            date = dateFormat.get().parse(str);
            week = sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 获得当前月--开始日期
     *
     * @param date
     * @param flag 0：本月，1：上月
     * @return
     */
    public static String getMinMonthDate(String date, Integer flag) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            if (flag == 0) {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.MONTH, -1);
            }
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得当前月--结束日期
     *
     * @param date
     * @param flag 0：本月，1：上月
     * @return
     */
    public static String getMaxMonthDate(String date, Integer flag) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            if (flag == 0) {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.DATE, -1);
            }
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本周周一日期
     *
     * @param date
     * @param flag 0:表示本周，1:表示上周
     * @return
     */
    public static String getCurrentMonday(String date, Integer flag) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar = getWeekDay(date, calendar, flag);
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本周周日日期
     *
     * @param date
     * @param flag 0:表示本周，1:表示上周
     * @return
     */
    public static String getPreviousSunday(String date, Integer flag) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar = getWeekDay(date, calendar, flag);
            calendar.add(Calendar.DATE, 6);
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 本周开始时间-结束时间
     *
     * @param date
     * @param calendar
     * @param flag     0:表示本周，1:表示上周
     * @return
     * @throws ParseException
     */
    private static Calendar getWeekDay(String date, Calendar calendar, Integer flag) throws ParseException {
        calendar.setTime(dateFormat.get().parse(date));
        if (flag == 1) {
            calendar.add(Calendar.DATE, -7);
        }

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = calendar.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return calendar;
    }

    /**
     * 昨天
     *
     * @param date
     * @return
     */
    public static String getYesterday(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            calendar.add(Calendar.DATE, -1);
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一天的某几天
     *
     * @param date
     * @param i
     * @return
     */
    public static String getOneDayAfterDay(String date, int i) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            calendar.add(Calendar.DATE, i);
            return dateToStr(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某天的前几天
     *
     * @param type 1 格式为 MM-DD 其他为YYYY-MM-DD
     * @param i    从0开始则是当天开始
     * @param date
     * @return
     */
    public static String getBeforeDay(int type, int i, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            calendar.add(Calendar.DATE, -i);
            if (type == 1) {
                return dateMonthToStr(calendar.getTime());
            } else {
                return dateToStr(calendar.getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某天的上周同一天
     *
     * @param type 1 格式为 MM-DD 其他为YYYY-MM-DD
     * @param i    从0开始则是当天开始
     * @param date
     * @return
     */
    public static String getBeforeWeekDate(int type, int i, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            calendar.add(Calendar.WEEK_OF_YEAR, -i);
            if (type == 1) {
                return dateMonthToStr(calendar.getTime());
            } else {
                return dateToStr(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 某天的某月第一天和最后一天
     *
     * @param type      返回类型
     * @param monthType 1：第一天,0:最后一天
     * @param i
     * @param date
     * @return
     */
    public static String getBeforeMonthDate(int type, int monthType, int i, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.get().parse(date));
            if (monthType == 1) {
                calendar.set(Calendar.DAY_OF_MONTH, 1);
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            calendar.add(Calendar.MONTH, -i);
            if (type == 1) {
                return dateMonthToStr(calendar.getTime());
            } else {
                return dateToStr(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     *
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    //获取两个时间差的天数
    public static long getTimeDifference(Date d1, Date d2) {
        long diff = d1.getTime() - d2.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    //获取两个时间差的天数
    public static long getTimeDifference(String d1, String d2) {
        Date sDate = strToDate(d1);
        Date eDate = strToDate(d2);
        long diff = sDate.getTime() - eDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * @param field  日历字段 （Calendar.YEAR / Calendar.MONTH / Calendar.DATE
     *               更多参考Calendar类的常量）
     * @param amount 要添加到该字段的日期或时间的量
     * @return 指定日期
     * @Title: specifiedTime
     * @Description: (生成指定日期)
     */
    public static Date specifiedTime(int field, int amount) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(field, amount);

        return calendar.getTime();
    }

}
