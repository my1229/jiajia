package com.rfw.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat yyyymmddFormat = new SimpleDateFormat("yyyy-mm-dd");

    public static final long DAY_MILLIS = 24 * 3600L * 1000L;

    public static final long HALF_HOUR_MILLIS = 1800L * 1000L;

    public static final long HOUR_MILLIS = 3600L * 1000L;

    public static final long HOUR_MILLIS_2 = 2L * 3600 * 1000;

    public static final long HOUR_MILLIS_3 = 3L * 3600 * 1000;

    public static final long HOURS_MILLIS_12 = 12 * 3600L * 1000L;

    public static final long HOURS_18 = 18 * 3600L * 1000L;

    public static final long TWO_DAY_MILLIS = DAY_MILLIS << 1;

    public static final long TIME_ZONE_DIFF = 8 * 3600L * 1000L;

    public static long MAX_INTERVAL_DATA_AVAILABLE = 7 * 24 * 3600 * 1000L;

    public static long WEEK_MILLIS = 7 * 24 * 3600 * 1000L;

    public static long DEFAULT_MILLIS_SPAN = WEEK_MILLIS;

    public static long TRIPPLE_DAY_MILLIS_SPAN = 3 * 24 * 3600 * 1000L;

    public static long TWO_WEEK_SPAN = 14 * DAY_MILLIS;

    public static long SIXTEEN_DAYS = 16 * DAY_MILLIS;

    public static long THIRTY_DAYS = 30 * DAY_MILLIS;

    public static long TEN_SECONDS_MILLIS = 10 * 1000L;

    public static long THREE_SECONDS_MILLIS = 3 * 1000L;

    public static long ONE_MINUTE_MILLIS = 60 * 1000L;

    public static long TEN_MINUTE_MILLIS = 600 * 1000L;

    public static long ACOOKIE_TIME_ADJUST = 20 * 60 * 1000L;

    public static String formDateForLog(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static long formDailyTimestamp(Date date) {
        return formDailyTimestamp(date.getTime());
    }

    public static long formDailyTimestamp(String daily) {
        try {
            return formDailyTimestamp(new SimpleDateFormat("yyyy-MM-dd").parse(daily));
        } catch (ParseException e) {
            return 0;
        }
    }

    public static long formCurrDate() {
        return formDailyTimestamp(System.currentTimeMillis());
    }

    public static long formYestadyMillis() {
        return formDailyTimestamp(System.currentTimeMillis() - DateUtil.DAY_MILLIS);
    }

    public static int formDay(long ts) {
        return (int) ((ts + TIME_ZONE_DIFF) / DAY_MILLIS);
    }

    public static long toDayTs(int day) {
        return ((long) day) * DAY_MILLIS - TIME_ZONE_DIFF;
    }

    public static long formDailyTimestamp(long ts) {
        return ((ts + TIME_ZONE_DIFF) / DAY_MILLIS) * DAY_MILLIS - TIME_ZONE_DIFF;
    }

    public static long formNextDate(long ts) {
        return formDailyTimestamp(ts) + DAY_MILLIS - 1L;
    }

    public static int getCurrHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getHourOfDay(long day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(day));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDayOfWeek(long day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(day));
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getDayOfMonth(long day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(day));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static long getUdpMaxAvailabe(long today) {
        return today - SIXTEEN_DAYS;
    }

    public static long getTargetUdpMillisForTodayCompute() {
        long curr = System.currentTimeMillis();
        long target = 0L;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(curr);
        if (cal.get(Calendar.HOUR_OF_DAY) < 23) {
            target = DateUtil.formCurrDate() - DateUtil.DAY_MILLIS;
        } else {
            target = DateUtil.formCurrDate();
        }

        return target;
    }

    public static int TARGET_PREVIEW_HOUR = 23;

    public static long getTargetUdpMillisForTodayPreview() {
        long curr = System.currentTimeMillis();
        long target = 0L;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(curr);
        if (cal.get(Calendar.HOUR_OF_DAY) < TARGET_PREVIEW_HOUR) {
            target = DateUtil.formCurrDate() - DateUtil.TWO_DAY_MILLIS;
        } else {
            target = DateUtil.formCurrDate() - DateUtil.DAY_MILLIS;
        }

        return target;
    }

    public static boolean isBeforeElevenClock() {
        return false;
    }

    public static boolean isSameDay(long time1, long time2) {
        Calendar instance = Calendar.getInstance();

        instance.setTimeInMillis(time1);
        int day1 = instance.get(Calendar.DAY_OF_YEAR);

        instance.setTimeInMillis(time2);
        int day2 = instance.get(Calendar.DAY_OF_YEAR);

        return day1 == day2;
    }

    public static long parseYMDDay(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
    }

    public static String parseYYMMDDDay(Date d) {
        return new SimpleDateFormat("yyyyMMdd").format(d);
    }

    public static String parseYMDDay(long dateTs) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateTs));
    }

    public static String parseYMDHMSDay(long dateTs) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateTs));
    }

    public static String parseHour(Date date) {
        try {
            return new SimpleDateFormat("HH").format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateOffsetMonth(int num) {
        Calendar offset = Calendar.getInstance();
        offset.add(Calendar.MONTH, num);
        return offset.getTime();
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(parseYYMMDDDay(new Date()));
        System.out.println(parseHour(new Date(1384413000000l)));

    }

}
