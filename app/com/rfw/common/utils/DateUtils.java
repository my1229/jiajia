package com.rfw.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private final static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final long SECOND_MILLIS = 1000l;

    public static final long TWO_MINUTES = 2 * 60 * 1000;

    public static final long DAY_MILLIS = 24l * 3600 * 1000;

    public static final long HALF_HOUR_MILLIS = 1800l * 1000;

    public static final long HOUR_MILLIS = 3600l * 1000;

    public static final long TWO_HOUR_MILLIS = 2l * 3600 * 1000;

    public static final long FOUR_HOUR_MILLIS = 4l * 3600 * 1000;

    public static final long HALF_DAY_HOUR_MILLIS = 12l * 3600 * 1000;

    public static final long TWO_DAY_MILLIS = DAY_MILLIS << 1;
    public static final long TIME_ZONE_DIFF = 8l * 3600L * 1000;

    public static long MAX_INTERVAL_DATA_AVAILABLE = 7 * 24 * 3600 * 1000L;
    public static long WEEK_MILLIS = 7 * 24 * 3600 * 1000L;

    public static long FIFTHEN_DAY_MILLIS = 15 * 24 * 3600 * 1000L;

    public static long DEFAULT_MILLIS_SPAN = WEEK_MILLIS;
    public static long TRIPPLE_DAY_MILLIS_SPAN = 3 * 24 * 3600 * 1000L;
    public static long TWO_WEEK_SPAN = 14 * DAY_MILLIS;
    public static long SIXTEEN_DAYS = 16 * DAY_MILLIS;
    public static long THIRTY_DAYS = 30 * DAY_MILLIS;

    public static long ONE_MONTH_MILLIS = 30 * DAY_MILLIS;

    public static long TWO_MONTH_MILLIS = 60 * DAY_MILLIS;

    public static long TEN_SECONDS_MILLIS = 10 * 1000L;
    public static long THREE_SECONDS_MILLIS = 3 * 1000L;
    public static long ONE_MINUTE_MILLIS = 60 * 1000L;

    public static long THREE_MINUTE_MILLIS = 3 * 60 * 1000L;

    public static long FIVE_MINUTE_MILLIS = 5 * 60 * 1000L;
    public static long ACOOKIE_TIME_ADJUST = 20 * 60 * 1000L;

    public static String formDateForLog(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static String formDateForYMDHMS(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static String formDateForYYYYMMDD(long time) {
        return new SimpleDateFormat("yyyyMMdd").format(time);
    }

    public static String formDateForYYYYMMDDHMS(long time) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(time);
    }

    public static String formDateForSimple(long time) {
        return new SimpleDateFormat("M月d日").format(time);
    }

    public static void main(String[] args) {
        System.out.println(parseDateStr("2015-05-12", "yyyy-MM-dd"));
    }

    public static Date parseDateStr(String dateStr, String formatStr) {
        try {
            return new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            log.warn(e.getMessage(), e);

            return null;
        }
    }

    public static long formDailyTimestamp(Date date) {
        return formDailyTimestamp(date.getTime());
    }

    public static long formCurrDate() {
        return formDailyTimestamp(System.currentTimeMillis());
    }

    public static long formYestadyMillis() {
        return formDailyTimestamp(System.currentTimeMillis() - DateUtils.DAY_MILLIS);
    }

    public static long formDailyTimestamp(long ts) {
        return (((ts + TIME_ZONE_DIFF) / DAY_MILLIS) * DAY_MILLIS) - TIME_ZONE_DIFF;
    }

    public static long formNextDate(long ts) {
        return (formDailyTimestamp(ts) + DAY_MILLIS) - 1L;
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

        if (cal.get(Calendar.HOUR_OF_DAY) < 9) {
            target = DateUtils.formCurrDate() - DateUtils.DAY_MILLIS;
        } else {
            target = DateUtils.formCurrDate();
        }

        return target;
    }

    public static long getTargetUdpMillisForTodayPreview() {
        long curr = System.currentTimeMillis();
        long target = 0L;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(curr);

        if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
            target = DateUtils.formCurrDate() - DateUtils.TWO_DAY_MILLIS;
        } else {
            target = DateUtils.formCurrDate() - DateUtils.DAY_MILLIS;
        }

        return target;
    }

    public static boolean isBeforeElevenClock() {
        return false;
    }

    public static long getLastWeekTs() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_MONTH, -1);

        return formDailyTimestamp(calendar.getTimeInMillis());
    }

    public static long getBeforeMonthTs(int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -monthNum);

        return formDailyTimestamp(calendar.getTimeInMillis());
    }

    /**
     * 获得今天的 00:00:00.0000
     * 
     * @return
     */
    public static long getTodayWithOutTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long clearTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long setBeginTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long setEndTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis();
    }

    public static long getFormatedYMD(Long millis, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, offset);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(c.getTime()));
    }

    public static long getFormatedYMDHMS(Long millis, int offsetHour, int offsetMin, int offsetSec) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.HOUR_OF_DAY, offsetHour);
        c.add(Calendar.MINUTE, offsetMin);
        c.add(Calendar.SECOND, offsetSec);
        c.set(Calendar.MILLISECOND, 0);
        return Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(c.getTime()));
    }

    public static long getFormatedYM(Long millis, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.MONTH, offset + 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return Long.valueOf(new SimpleDateFormat("yyyyMM").format(c.getTime()));
    }

    public static long getLastMillisOfDay(Long millis, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, offset);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTimeInMillis();
    }

    public static long getFirstMillisOfDay(Long millis, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, offset);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 获取beginTs和endTs间的月份差异
     * 
     * @param beginTs
     * @param endTs
     * @return
     */
    public static int getMonthBetween(Date beginTs, Date endTs) {
        Calendar beginC = Calendar.getInstance();
        beginC.setTime(beginTs);

        Calendar endC = Calendar.getInstance();
        endC.setTime(endTs);

        return (12 * endC.get(Calendar.YEAR) + endC.get(Calendar.MONTH))
                - (12 * beginC.get(Calendar.YEAR) + beginC.get(Calendar.MONTH));
    }

    /**
     * 获取时间间隔的天数
     * 
     * @param beginTs
     * @param endTs
     * @return
     */
    public static long getDayBetween(Date beginTs, Date endTs) {
        long bTs = beginTs.getTime();

        long eTs = endTs.getTime();

        // 间隔使劲按
        long intervalTs = eTs - bTs;

        return intervalTs / DAY_MILLIS;
    }

    /**
     * 获取当月的第一天:如2015-07-01 00:00:00
     * 
     * @return
     */
    public static long getCurMonthDayBegin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
}
