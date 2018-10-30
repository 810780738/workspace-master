package com.xiaozhu.rpm_server;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2018/10/24 15:59
 * @Description:
 */
public class TimeTest {
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


    @Test
    public void test() throws ParseException {
//        long testLong = stringToLong("2018-10-24 17:54:46", "yyyy-MM-dd HH:mm:ss");
//        System.out.println("test:"+testLong);
//        Calendar instance = Calendar.getInstance();
//        int year = instance.get(Calendar.YEAR);
//        int month = instance.get(Calendar.MONTH)+1;
//        int today = instance.get(Calendar.DATE);
//        System.out.println(year + "____" + month + "____" + today);
//
//        String time = new String(year + "-" + month + "-" + today);
//        String startTime = time+" 00:00:00";
//        String endTime = time+" 23:59:59";
//        System.out.println(time);
//
//        long startLong = stringToLong(startTime, "yyyy-MM-dd HH:mm:ss");
//        long endLong = stringToLong(endTime, "yyyy-MM-dd HH:mm:ss");
//        System.out.println("start:"+startLong);
//        System.out.println("end:"+endLong);
//        if (testLong > startLong & testLong < endLong) System.out.println("在访问内");
//        else System.out.println("不再。。。");
//        System.out.println();
        for (int i = 0; i < 100 ; i++) {
            if (i == 20) break;
            System.out.println(i);
        }
    }
}
