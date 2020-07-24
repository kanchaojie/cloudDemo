package com.kcj.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    /**
     * 日期格式，年，例如：2018，2008
     */
    public static final String DATE_FORMAT_YYYY = "yyyy";

    /**
     * 日期格式，年月，例如：201812，200808
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 日期格式，年月日，例如：20180630，20080808
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式，年月日时分秒，例如：20180630121023，20080808121021
     */
    public static final String DATE_FORMAT_YYYYMMDDHHmmss = "yyyyMMddHHmmss";

    /**
     * 日期格式：2018-12-21 10:13:50 yyyy-MM-dd HH:mm:ss
     */
    public static final String STR_PARSE_YYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化Date时间
     *
     * @param time
     *            Date类型时间
     * @param timeFromat
     *            String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 判断是否为指定的日期格式
     */
    public static boolean isDate(String value) {
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        formater.setLenient(false);
        // 设置日期转化成功标识
        boolean dateflag = true;
        // 这里要捕获一下异常信息
        try {
            Date date = formater.parse(value);
        } catch (ParseException e) {
            dateflag = false;
        }
        return dateflag;
    }

    /****
     * 将时间戳转换为时间
     *
     * @param s          时间戳
     * @param dateFormat 时间格式（yyyy-MM-dd或yyyy-MM-dd hh:mm:ss）
     * @return
     */
    public static String stampToDate(String s, String dateFormat) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param
     * @param format 如：yyyy-MM-dd
     * @return
     */
    public static String dateTimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000) + "000";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *      * 标准化时间显示      * yyyy-MM-dd HH:mm:ss      * @param dateStr    
     *  * @return      
     */
    public static String formatDate1(String dateStr) {
        String[] aStrings = dateStr.split(" ");
        if (aStrings[1].equals("Jan")) {
            aStrings[1] = "01";
        }
        if (aStrings[1].equals("Feb")) {
            aStrings[1] = "02";
        }
        if (aStrings[1].equals("Mar")) {
            aStrings[1] = "03";
        }
        if (aStrings[1].equals("Apr")) {
            aStrings[1] = "04";
        }
        if (aStrings[1].equals("May")) {
            aStrings[1] = "05";
        }
        if (aStrings[1].equals("Jun")) {
            aStrings[1] = "06";
        }
        if (aStrings[1].equals("Jul")) {
            aStrings[1] = "07";
        }
        if (aStrings[1].equals("Aug")) {
            aStrings[1] = "08";
        }
        if (aStrings[1].equals("Sep")) {
            aStrings[1] = "09";
        }
        if (aStrings[1].equals("Oct")) {
            aStrings[1] = "10";
        }
        if (aStrings[1].equals("Nov")) {
            aStrings[1] = "11";
        }
        if (aStrings[1].equals("Dec")) {
            aStrings[1] = "12";
        }
        return aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
    }

    /**
     *
     * 格式化为date类型的
     *
     */
    public static Date formatDate2(String dateStr) {
        String[] aStrings = dateStr.split(" ");
        if (aStrings[1].equals("Jan")) {
            aStrings[1] = "01";
        }
        if (aStrings[1].equals("Feb")) {
            aStrings[1] = "02";
        }
        if (aStrings[1].equals("Mar")) {
            aStrings[1] = "03";
        }
        if (aStrings[1].equals("Apr")) {
            aStrings[1] = "04";
        }
        if (aStrings[1].equals("May")) {
            aStrings[1] = "05";
        }
        if (aStrings[1].equals("Jun")) {
            aStrings[1] = "06";
        }
        if (aStrings[1].equals("Jul")) {
            aStrings[1] = "07";
        }
        if (aStrings[1].equals("Aug")) {
            aStrings[1] = "08";
        }
        if (aStrings[1].equals("Sep")) {
            aStrings[1] = "09";
        }
        if (aStrings[1].equals("Oct")) {
            aStrings[1] = "10";
        }
        if (aStrings[1].equals("Nov")) {
            aStrings[1] = "11";
        }
        if (aStrings[1].equals("Dec")) {
            aStrings[1] = "12";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
        Date datetime = null;
        try {
            datetime = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }
    /**
     * 当前日期加上天数后的日期
     *
     * @param
     * @param ：天
     * @param num
     *            小时，天数等等
     * @return
     */
    public static Date plusDay2(Date now, int type, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);
        ca.add(type, num);// num为增加的天数，可以改变的
        return ca.getTime();
    }

    /**
     * 字符串转时间
     *
     * @param timeStr
     *            字符串
     * @param timeFromat
     *            String类型格式
     * @return 格式化后的Date
     * @throws ParseException
     */
    public static Date parseStrToDate(String timeStr, String timeFromat) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.parse(timeStr);
    }

    /**
     * 获取今天 开始时间
     *
     * @return String
     * @throws ParseException
     */
    public static Date getTodayStart() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        return format2.parse(format.format(new Date()) + " 00:00:00");
    }

    /**
     * 获取今天 结束时间
     *
     * @return String
     * @throws ParseException
     */
    public static Date getTodayEnd() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        return format2.parse(format.format(new Date()) + " 23:59:59");
    }

    /**
     * 获取昨天开始时间
     *
     * @return String
     * @throws ParseException
     */
    public static Date getYestodayStart() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return format2.parse(format.format(time) + " 00:00:00");
    }

    /**
     * 获取昨天结束时间
     *
     * @return String
     * @throws ParseException
     */
    public static Date getYestodayEnd() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return format2.parse(format.format(time) + " 23:59:59");
    }

    /**
     * 获取本周的第一天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfWeek(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getFirstDayWeekStart(Date date, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, 2 + day);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getFirstDayWeekEnd(Date date, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, 2 + day);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 上周第一天
     *
     * @return
     * @throws Exception
     */
    public static Date getMonday() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return format2.parse(format.format(cal.getTime()) + " 00:00:00");
    }

    /**
     * 上周最后一天
     *
     * @return
     * @throws Exception
     */
    public static Date getSunday() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return format2.parse(format.format(cal.getTime()) + " 23:59:59");
    }

    /**
     * 本月第一天；
     *
     * @return
     * @throws ParseException
     */
    public static Date getCurrentMonthFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cale.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 上月第一天
     *
     * @return
     */
    public static Date getfirstDayofMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(calendar.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 上月最后一天
     *
     * @return
     */
    public static Date getlastDayofMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(calendar.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 获取本日到目前为止时间点
     *
     * @return
     */
    public static List<String> getHours() {
        List<String> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.HOUR_OF_DAY);
        for (int j = 1; j <= i; j++) {
            String str = (j < 10) ? ("0" + j) : (j + "");
            list.add(str);
        }
        return list;
    }

    /**
     * 获取指定时间当前属于几点/周几/几号
     *
     * @param now
     *            执行时间
     * @param todayType
     *            本日、本周、本月枚举类型
     * @return
     */
    // public static String getHour(Date now, Integer todayType) {
    // Calendar ca = Calendar.getInstance();
    // ca.setTime(now);
    // switch (TimeTypeEnum.from(todayType)) {
    // case TODAY:
    // int t = ca.get(Calendar.HOUR_OF_DAY);
    // return (t < 10) ? ("0" + t) : (t + "");
    // case THIS_WEEK:
    // String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    // int w = ca.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
    // if (w < 0)
    // w = 0;
    // return weekDays[w];
    // case THIS_MONTH:
    // int m = ca.get(Calendar.DAY_OF_MONTH);
    // return (m < 10) ? ("0" + m) : (m + "");
    // case THIS_YEAR:
    // int n = ca.get(Calendar.MONTH);
    // return (n < 10) ? ("0" + n) : (n + "");
    // default:
    // break;
    // }
    // return null;
    // }

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirstDate() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取去年最后一天的日期
     *
     * @param
     *
     * @return Date
     */
    public static Date getLastYearLastDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year - 1, 11, 31, 11, 59, 59);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println("****" + getLastYearLastDate());
    }

    /**
     * 获取去年第一天日期
     *
     * @param
     *
     * @return Date
     */
    public static Date getLastYearFirstDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year - 1, 0, 1, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(calendar.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 获取当前是本年的第几个周
     *
     * @return
     */
    public static int getWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 根据当前时间是本年的第几个周获取去年第几周内日期
     *
     * @param day
     *            表示周几 1表示周日 ，2表示周一 7表示周六
     * @return
     */
    public static Date getLastYearDateOfThisWeekDate(int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int lastYear = cal.get(Calendar.YEAR) - 1;
        Calendar cal1 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.set(Calendar.YEAR, lastYear); //
        cal1.set(Calendar.WEEK_OF_YEAR, week); // 设置为year年的第week周
        cal1.set(Calendar.DAY_OF_WEEK, day); // 1表示周日，2表示周一，7表示周六
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal1.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 上一个月相同日期所在星期
     *
     * @param type
     *            type=7为星期天，type=其他数值为星期一
     * @return
     */
    public static Date getLastMonthDateOfThisWeekDate(int type) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) - 1;
        cal.set(Calendar.MONTH, month);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        if (type == 7) {
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 6);
        }
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 根据当前日期获取去年同一月份的第一天
     *
     * @return
     */
    public static Date getFirstDateOfThisMonthDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 根据当前日期获取去年同一月份的最后一天
     *
     * @return
     */
    public static Date getLastDateOfThisMonthDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        // 设置月份
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cal.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getCurrentMonthDayStart(int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1 + day);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cale.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getCurrentMonthDayEnd(int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1 + day);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cale.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    // public static Map<Integer,TimeDto> getWeekInfo(){
    // Date today = new Date();
    // Calendar c=Calendar.getInstance();
    // c.setTime(today);
    // Map<Integer,TimeDto> map=new HashMap<>();
    // int week = c.get(Calendar. DAY_OF_WEEK) - 1;
    // if (week == 0 ) {
    // week = 7 ;
    // }
    // for (int i = 0; i <week; i++) {
    // Date startTime = getFirstDayWeekStart(today,i);//开始
    // Date endTime = getFirstDayWeekEnd(today,i);//结束
    // TimeDto dto=new TimeDto();
    // dto.setStartTime(startTime);
    // dto.setEndTime(endTime);
    // map.put(i+1, dto);
    // }
    // return map;
    // }
    //
    // public static Map<Integer,TimeDto> getMonthInfo(){
    // Date today = new Date();
    // Calendar c=Calendar.getInstance();
    // c.setTime(today);
    // Map<Integer,TimeDto> map=new HashMap<>();
    // int month=c.get(Calendar.DAY_OF_MONTH);
    // for (int i = 0; i <month; i++) {
    // Date startTime = getCurrentMonthDayStart(i);//开始
    // Date endTime = getCurrentMonthDayEnd(i);//结束
    // TimeDto dto=new TimeDto();
    // dto.setStartTime(startTime);
    // dto.setEndTime(endTime);
    // map.put(i+1, dto);
    // }
    // return map;
    // }
    //
    //
    // public static Map<Integer,TimeDto> getYearInfo(){
    // Date today = new Date();
    // Calendar c=Calendar.getInstance();
    // c.setTime(today);
    // Map<Integer,TimeDto> map=new HashMap<>();
    // int month=c.get(Calendar.MONTH) + 1;
    // for (int i = 0; i <month; i++) {
    // Date startTime = getYearStart(i);//开始
    // Date endTime = getYearEnd(i);//结束
    // TimeDto dto=new TimeDto();
    // dto.setStartTime(startTime);
    // dto.setEndTime(endTime);
    // map.put(i+1, dto);
    // }
    // return map;
    // }

    public static Date getYearStart(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.set(Calendar.MONTH, month + 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date parseDate = null;
        try {
            parseDate = format2.parse(format.format(cale.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getYearEnd(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.set(Calendar.MONTH, month + 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date parseDate=null;
        try {
            parseDate = format2.parse(format.format(cale.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 两个时间相差多少小时
     *
     * @param start
     * @param end
     * @return
     */
    public static long getHour(long start, long end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        return (end - start) % nd / nh;
    }

    public static Date getLastYearDateOfDate(Date startTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return y;
    }

    public static Date getLastMonthDateOfDate(Date startTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m;
    }

    public static Date getLastTimeInterval() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar calendar1 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        Date lastBeginDate = calendar1.getTime();
        try {
            lastBeginDate = format2.parse(format.format(calendar1.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastBeginDate;
    }

    public static Date getLastTimeInterva2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat(STR_PARSE_YYYYMMDDHHmmss);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset2 = 7 - dayOfWeek;
        calendar2.add(Calendar.DATE, offset2 - 7);
        Date lastEndDate = calendar2.getTime();
        try {
            lastEndDate = format2.parse(format.format(calendar2.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastEndDate;
    }
}
