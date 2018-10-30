package com.xioazhu.rpccommon.Utils;/**
 * @Auther: Administrator
 * @Date: 2018/10/29 18:57
 * @Description:
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *@ClassName TimeUtils
 *@Description TODO
 *@Author zhusm
 *@Date 2018/10/29 18:57    
 *@Version 1.0
 */
public class TimeUtils {
    /**
     * 把字符串转化成日期型。
     *
     * @param name
     *            字符串
     * @param df
     *            日期格式
     * @return Date 转化后的日期
     */
    public static Date getDate(String name, String df, Date defaultValue) {
        if (name == null) {
            return defaultValue;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(df);

        try {
            return formatter.parse(name);
        } catch (ParseException e) {
        }

        return defaultValue;
    }

    /**
     * 把字符串转化成日期型。 缺省为当前系统时间。
     *
     * @param name
     *            字符串
     */
    public static Date getDate(String name) {
        return getDate(name, null);
    }

    /**
     * 把字符串转化成日期型。 缺省为当前系统时间。
     *
     * @param name
     *            字符串
     */
    public static Date getDateTime(String name) {
        return getDateTime(name, null);
    }

    /**
     * 把字符串转化成日期型。
     *
     * @param name
     *            字符串
     * @param defaultValue
     *            日期格式
     * @return Date 转化后的日期
     */
    public static Date getDate(String name, Date defaultValue) {
        return getDate(name, "yyyy-MM-dd", defaultValue);
    }

    /**
     * 把字符串转化成日期型。
     *
     * @param name
     *            字符串
     * @param defaultValue
     *            日期格式
     * @return Date 转化后的日期
     */
    public static Date getDateTime(String name, Date defaultValue) {
        return getDate(name, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    public static Date mysqlDate2Date(int seconds) {
        long l = (long) seconds * 1000;
        Date date = new Date(l);
        return date;
    }

    public static long date2MysqlDate(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 返回两个日期的时间差，返回的时间差格式可以是: Calendar.YEAR, Calendar.MONTH, Calendar.DATE
     * 注意：该功能采用递归的方法，效率还有待解决，如果两个时间之差较大，而要求返回的时间格式又很小，这时效率就很差
     * 但此方法在要求精度较高的情况下比较有效，如求月份差的时候就比较准确，考虑到了各种情况．如闰月，闰年
     *
     * @param earlyDate
     * @param lateDate
     * @param returnTimeFormat
     * @return time
     */
    public static int getBetweenTime(Calendar earlyDate, Calendar lateDate, int returnTimeFormat) {
        earlyDate = (Calendar) earlyDate.clone();
        lateDate = (Calendar) lateDate.clone();
        int time = 0;
        while (earlyDate.before(lateDate)) {
            earlyDate.add(returnTimeFormat, 1);
            time++;
        }
        return time;
    }

    public static int getBetweenYear(Date lastDate) {
        int between = Integer.MIN_VALUE;
        if (lastDate != null) {
            Calendar cnow = Calendar.getInstance();
            Calendar clast = Calendar.getInstance();
            clast.setTime(lastDate);
            between = getBetweenTime(clast, cnow, Calendar.YEAR);
        }
        return between;
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd
     *            起始日期，格式yyyy-MM-dd
     * @param ed
     *            终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getBetweenDay(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date.valueOf(sd)).getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd
     *            起始日期，格式yyyy-MM-dd
     * @param ed
     *            终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getBetweenDay(Date sd, Date ed) {
        return (ed.getTime() - sd.getTime()) / (3600 * 24 * 1000);
    }

    public static int getBetweenDay(Date lastDate) {
        int between = Integer.MIN_VALUE;
        if (lastDate != null) {
            Calendar cnow = Calendar.getInstance();
            Calendar clast = Calendar.getInstance();
            clast.setTime(lastDate);
            between = getBetweenTime(clast, cnow, Calendar.DATE);
        }
        return between;
    }

    /**
     * 时间比对
     * @param s1
     * @param s2
     * @return
     */
    public static int dateCompare(String s1, String s2) {
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(s1));
            c2.setTime(df.parse(s2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        return c1.compareTo(c2);
		/*  if(result==0)
		      System.out.println("c1相等c2");
		  else if(result<0)
		      System.out.println("c1小于c2");
		  else
		      System.out.println("c1大于c2");*/
    }

    public static String dateFormate(Date date, String formate) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formate);
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public static Date dateFormate(String date, String formate) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formate);
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


    public static Date getTime(String name, Date defaultValue) {
        return getDate(name, "HH:mm:ss", defaultValue);
    }

    public static Date getTime(String name) {
        return getTime(name, null);
    }

    /**
     * 比较日期值对应的时间与字符串对应的时间值的大小(只比较HH:mm:ss部分)。
     * @param dateTime    日期值
     * @param timeString  时间字符串
     * @return <br>
     *          dateTime的时间早于 timeString的时间则返回 -1;<br>
     *          dateTime的时间晚于 timeString的时间则返回 1;<br>
     *          dateTime的时间等于 timeString的时间则返回 0;
     */
    public static int dateTimeDiff(Date dateTime, String timeString) {
        Calendar dTime = Calendar.getInstance();
        Calendar sTime = Calendar.getInstance();
        dTime.setTime(dateTime);
        sTime.setTime(getTime(timeString));
        sTime.set(Calendar.YEAR, dTime.get(Calendar.YEAR));
        sTime.set(Calendar.MONTH, dTime.get(Calendar.MONTH));
        sTime.set(Calendar.DATE, dTime.get(Calendar.DATE));
        if (dTime.before(sTime)) {
            return -1;
        }
        if (dTime.after(sTime)) {
            return 1;
        }
        return 0;
    }

    public static Date rollDate(Date dateTime, int field, int amount) {
        if (dateTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(dateTime);
            c.add(field, amount);
            return c.getTime();
        }
        return null;
    }

    /**
     * 功能：计算日期
     * 作者：chendw
     * 时间：2010-3-4 下午07:47:31
     * 版本：
     * 参数： 日期基值  date
     * 		  计算类型  type 如 Calendar.YEAR、Calendar.MONTH
     * 		  计算偏值  num
     * @return
     */
    public static Date caclDate(Date date, int type, int num) {
        Calendar C = Calendar.getInstance();
        C.setTime(date);
        C.add(type, num);
        return C.getTime();
    }

    public static String dateTostring(Date date, String typ) {
        String sdate = (new SimpleDateFormat(typ)).format(date);
        return sdate;
    }

    public static String[] splitDataKey(String datakey) {
        String[] datakeyArry = new String[3];

        //String newStr = str.replaceFirst("^0*", "");System.out.println(newStr);
        datakeyArry[0] = datakey.substring(2, 18).replaceAll("^(0+)", "");
        datakeyArry[1] = datakey.substring(18, 34);
        datakeyArry[2] = datakey.substring(34, 54).replaceAll("^(0+)", "");
        if (datakeyArry[2].length() > 15) {
            datakeyArry[2] = datakeyArry[2].substring(0, 15);
        }
        return datakeyArry;
    }

    /**
     * 获取UTC时间
     * @param date
     * @return
     */
    public static Long getUTCTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal.getTimeInMillis();
    }

    /**
     * 获取UTC时间
     * @param date
     * @return
     */
    public static Date getUTCDate(Date date) {
        Long utcTS = getUTCTime(date);
        if (utcTS == null) {
            return null;
        }
        return new Date(utcTS);
    }

    public static Calendar getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar;
    }

    public static Date getMonthBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static int getWeekOfDateInt(Date dt){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        if (w == 0) {
            w = 7;
        }

        return w;
    }

    public static String getWeekOfDateText(Date dt){
        String[] weekDays = { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
        return weekDays[getWeekOfDateInt(dt)-1];
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String add_Day(Date date,int ai){
        Calendar   calendar   =   Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+ai);
        date=calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        return time;
    }

    public static int calDiffMonth(String startDate,String endDate){
        int result=0;
        try {
            SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear=getYear(start);
            int startMonth=getMonth(start);
            int startDay=getDay(start);
            int endYear=getYear(end);
            int endMonth=getMonth(end);
            int endDay=getDay(end);
            if (startDay>endDay){ //1月17  大于 2月28
                if (endDay==getDaysOfMonth(getYear(new Date()),2)){   //也满足一月
                    result=(endYear-startYear)*12+endMonth-startMonth;
                }else{
                    result=(endYear-startYear)*12+endMonth-startMonth-1;
                }
            }else{
                result=(endYear-startYear)*12+endMonth-startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static Date add_MINUTE(Date date,int ai){
        Calendar   calendar   =   Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+ai);
        date=calendar.getTime();
        return date;
    }

    /**
     * 判断日期大小
     * @param date1
     * @param date2
     * @return
     */
    public static boolean CompareToDate(Date date1,Date date2){
        boolean flag = false;
        if(date1.getTime() >= date2.getTime()){
            flag = true;
        }
        return flag;
    }

    /**
     * 获取之后+n日期
     * @param date
     * @param ai
     * @return
     */
    public static Date Add_Day(Date date,int ai){
        Calendar   calendar   =   Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+ai);
        date=calendar.getTime();
        return date;
    }


    /**
     * 字符串时间转成long
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = formatter.parse(strTime);
        if (date == null) {
            return 0;
        } else {
            long currentTime = date.getTime(); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * 获取今天开始时间long
     * @return
     */
    public static Long todayStartLong(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int today = instance.get(Calendar.DATE);
        String time = new String(year + "-" + month + "-" + today);
        String startTime = time+" 00:00:00";
        try {
            return stringToLong(startTime, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取今天截至时间long
     * @return
     */
    public static Long todayEndLong(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int today = instance.get(Calendar.DATE);
        String time = new String(year + "-" + month + "-" + today);
        String startTime = time+" 23:59:59";
        try {
            return stringToLong(startTime, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
