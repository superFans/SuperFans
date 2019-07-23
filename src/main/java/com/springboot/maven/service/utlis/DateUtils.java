/**
 * Copyright &copy; 2015-2020 <a href="http://app.caizhanzhushou.com/">JeePlus</a> All rights reserved.
 */
package com.springboot.maven.service.utlis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author fs
 * @version 2017-7-25
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMddHHmmss"};

    private static final String[] ZHOUNAME = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static String getDateTimeS() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date());
    }

    public static String getDateTimeQM() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getDateTimeM() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分").format(new Date());
    }

    public static String getDateTimeMK() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
    }

    public static String getDateTimeT() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getDateTimeQ() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    public static String getDateS() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getDateT() {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    }
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyyMMddHHmmss）
     */
    public static String getDateTimeStr() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 得到当前年份字符串
     *
     * @return
     */
    public static String getYTimeStr() {
        return formatDate(new Date(), "yyyy");
    }

    public static String getMTimeStr() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static String getYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        return formatDate(date, "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    public static String getMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        return formatDate(date, "MM");
    }

    public static Date getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = date;
        try {
            _date = sdf.parse(sdf.format(date).toString());
        } catch (ParseException e) {
            _date = date;
        }
        return _date;
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    public static String getDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        return formatDate(date, "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek(Date date) {
        if (date == null) {
            date = new Date();
        }
        return formatDate(date, "E");
    }

    /**
     * 得到当前星期字符串 格式（E）周几
     */
    public static String getZhou(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return ZHOUNAME[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t =System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（年-月-日 时:分:秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeMillis);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (int) ((afterTime - beforeTime) / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取两个日期之间的分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoMinute(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return ((afterTime - beforeTime) / (1000 * 60));
    }

    /**
     * 计算两个日期之间相差的天数 字符串的日期格式yyyy-MM-dd的计算
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     * @author Liuyc June 09 2014 17:00:38
     */
    public static Integer daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24) + 1; // +1
        // 包括当天也计算在相差天数之内
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取格式为yyyy-MM-dd的日期字符串的前、后几个天 m为正，获取后m个天、m为负，获取前m个天、 m为0时，获取本天
     *
     * @param oldDate 起始日期
     * @return String格式为yyyy-MM-dd的日期字符串
     * @author Liuyc June 09 2014 17:13:38
     */
    public static String getDiffDate(String oldDate, Integer day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(oldDate);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
            oldDate = sdf.format(now.getTime());
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return oldDate;
    }

    /**
     * @return
     * @Description: <p>
     * 转为日期格式 yyyy-MM-dd HH:mm:ss
     * </p>
     * @exception/throws
     * @Author guozheng 136762904@qq.com
     * @Data 2017年3月18日 下午5:52:50
     * @History: 1.Data: Author: Modification:
     */
    public static String toDateString(String str) {
        if (!StringUtils.isAnyBlank(str)) {
            StringBuffer stringBuffer = new StringBuffer(str);
            String ret = stringBuffer.substring(0, 4) + "-" + stringBuffer.substring(4, 6) + "-"
                    + stringBuffer.substring(6, 8) + " " + stringBuffer.substring(8, 10) + ":"
                    + stringBuffer.substring(10, 12) + ":" + stringBuffer.substring(12, 14);
            return ret;
        }
        return str;
    }

    /**
     * @param date
     * @return
     * @Title: 日期格式间转换
     * @Description: <p>
     * 将一个yyyyMMddHHmmss字符串转换为'yyyy-MM-dd HH:mm:ss'格式
     * </p>
     * @exception/throws
     * @Author Liuyc 18919937910@163.com
     * @Data 2017年3月28日 下午2:20:36
     * @History: 1.Data: Author: Modification:
     */
    public static String convertSting(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df2.format(d);
    }

    /**
     * @param date
     * @return
     * @Title: 日期格式间转换一
     * @Description: <p>
     * 将一个yyyy/MM/dd HH:mm:ss字符串转换为'yyyy-MM-dd HH:mm:ss'格式
     * </p>
     * @exception/throws
     * @Author Liuyc 18919937910@163.com
     * @Data 2017年3月28日 下午2:20:36
     * @History: 1.Data: Author: Modification:
     */
    public static String convertStingi2m(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df2.format(d);
    }

    /**
     * @throws
     * @Title: getOneTime
     * @Description: 获取当前日期i天后的日期
     * @param:
     * @return: String
     * @author: liubo
     * @date: 2017年12月22日 上午10:40:01
     */
    public static String getOneTime(int i) {
        String datestr = getDate();
        return getDiffDate(datestr, i);
    }

    /**
     * @param beginDate
     * @param endDate
     * @throws
     * @Title: randomDate
     * @Description: 获得随机日期
     * @param:
     * @param:
     * @return: Date
     * @author: liubo
     * @date: 2018年1月11日 下午3:12:15
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }

            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @throws
     * @Title:
     * @Description: 判断两个日期之间是否大于day天
     * @param: beginData
     * @param: endDate
     * @param: day
     * @return: boolean
     * @author: liubo
     * @date: 2018年1月12日 上午11:22:32
     */
    public static boolean judgeTowDays(Date beginData, Date endDate, double day) {
        double d = getDistanceOfTwoDate(beginData, endDate);
        if (d >= day) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前日期是否在某个区间
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 商户是否可以交易
     *
     * @param nowTime
     * @return
     */
    public static boolean isTransaction(Date nowTime) {
        try {
            String format = "yyyy-MM-dd HH:mm:ss";
            Date startTime = new SimpleDateFormat(format).parse("2016-05-10 00:00:00");
            Date endTime = new SimpleDateFormat(format).parse("2017-08-20 23:59:59");
            return isEffectiveDate(nowTime, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    //当前时间前一天的前三个月时间
    public static String getBeforeDayMonthStart() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        Date dBefore = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -3);
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String defaultStartDate = sdf.format(dBefore);
        return defaultStartDate;
    }

    //当前时间前一天的前三个月时间,格式yyyymmdd
    public static String getBeforeDay3MonthStart() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        Date dBefore = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -3);
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String defaultStartDate = sdf.format(dBefore);
        return defaultStartDate;
    }

    //当前时间前一天时间,格式yyyymmdd
    public static String getBeforeDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String defaultStartDate = sdf.format(date);
        return defaultStartDate;
    }

    //当前时间前一天的前7天时间,格式yyyymmdd
    public static String getBeforeDay1WeekStart() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        Date dBefore = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String defaultStartDate = sdf.format(dBefore);
        return defaultStartDate;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        // String date1 = "2017/4/17 9:33:22";
        // String date2 = DateUtils.convertStingi2m(date1);
        // Date prizetime = DateUtils.parseDate(date1);
        // SimpleDateFormat sdf = new SimpleDateFormat("E");
        // System.out.println(prizetime + "::: " + sdf.format(prizetime));
        // System.out.println(date2 + "::: " + getWeek(prizetime));
        //
        // System.out.println(DateUtils.getZhou(prizetime));
        // System.out.println(getOneTime(30));
        // for(int i=0;i<30;i++){
        // System.out.println(formatDateTime(randomDate("2017-11-01","2018-01-10")));
        // }
//		Date a = randomDate("2016-07-22", "2026-8-23");
//		System.out.println(formatDate(new Date()));
		/*String format = "yyyy-MM-dd HH:mm:ss";
		Date nowTime = new Date();
		Date startTime = new SimpleDateFormat(format).parse("2016-05-10 00:00:00");
		Date endTime = new SimpleDateFormat(format).parse("2017-08-20 23:59:59");
		System.out.println(isTransaction(a));*/

        System.out.println(getBeforeDay1WeekStart());
//		 System.out.println(getMTimeStr());


    }


    public static String firstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    public static String lastDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    public static String firstDayOfPreviousMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    public static String lastDayOfPreviousMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        String lastDay = format.format(cale.getTime());
        return lastDay;
    }

}
