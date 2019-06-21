package com.qiqiim.webserver.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理 
 */
public class DateUtil {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PAT = "yyyyMMddHHmmss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    // 获取系统当前时间
    public static Date nowDate() {
        return new Date();
    }

    public final static String now() {
        return DateFormatUtils.format(Calendar.getInstance().getTime(), DATE_TIME_PATTERN);
    }

    public final static String nows() {
        return DateFormatUtils.format(Calendar.getInstance().getTime(), DATE_TIME_PAT);
    }

    // 格式化时间
    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, DATE_TIME_PATTERN);
    }
}
