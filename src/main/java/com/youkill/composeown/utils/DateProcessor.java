package com.youkill.composeown.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateProcessor {
    /**
     * @Author: youkill
     * @Description: 在时间转换上，可能不是那么直观，推出一个全局的处理器
     * @DateTime: 2023/10/14 18:50
     */
    public static Date  DateByCommon(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String formattedDateStr = sdf.format(date);
            return sdf.parse(formattedDateStr);
        } catch (ParseException e) {
            // 处理解析异常，可以选择抛出或返回 null
            return null;
        }
    }
}
