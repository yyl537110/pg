package com.slj.pg.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by yaoyl on 2018/12/27.
 */
public class DateUtils {

    public static final ZoneId ZONE = ZoneId.systemDefault();

    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DEFAULT_PARSER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DEFAULT_LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 将日期时间格式化为"yyyy-MM-dd"形式的字符串返回
     *
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime) {
        return DEFAULT_FORMATTER.format(dateTime);
    }

    /**
     * 将日期时间格式化为patten形式的字符串返回
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(dateTime);
    }

    /**
     * 将日期格式化为"yyyy-MM-dd"形式的字符串返回
     *
     * @param date
     * @return
     */
    public static String formatLocalDate(LocalDate date) {
        return DEFAULT_LOCAL_DATE_FORMATTER.format(date);
    }

    /**
     * 将字符串转换为默认格式yyyy-MM-dd HH:mm:ss的日期时间
     *
     * @param text 2017-08-03 10:15:30
     * @return
     */
    public static LocalDateTime parse(String text) {
        return LocalDateTime.parse(text, DEFAULT_PARSER);
    }

    /**
     * 将字符串转换为指定格式pattern的日期时间
     *
     * @param text
     * @param pattern
     * @return
     */
    public static LocalDateTime parse(String text, String pattern) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(text, parser);
    }

    /**
     * 将字符串转换为指定格式pattern的日期
     *
     * @param text
     * @param pattern
     * @return
     */
    public static LocalDate parseLocalDate(String text, String pattern) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(text, parser);
    }

    /**
     * 获取日期时间格式的时间戳
     *
     * @param dateTime
     * @return
     */
    public static long getMilli(LocalDateTime dateTime) {
        return dateTime.toInstant(ZONE_OFFSET).toEpochMilli();
    }

    /**
     * 将时间戳格式化为日期时间类型
     *
     * @param milli
     * @return
     */
    public static LocalDateTime toDateTime(long milli) {
        return LocalDateTime.ofEpochSecond(milli / 1000, 0, ZONE_OFFSET);
    }
}

