package org.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    // 定义日期格式
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMdd");

    public static Date add(Date date, int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDay); // 增加一天

        // 获取后一天的 Date 对象
        return calendar.getTime();
    }

}
