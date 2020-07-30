package club.lemos.common.utils;

import club.lemos.common.constant.CommonConstant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    public static LocalDateTime millisToLocalDateTime(Long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

    public static Long localDateTimeToMills(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long localDateTimeToSeconds(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static Long safeLocalDateTimeToSeconds(LocalDateTime ldt) {
        if (Objects.isNull(ldt)) {
            return null;
        }
        return ldt.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static String millisToString(Long epochMilli) {
        return localDateTimeToString(millisToLocalDateTime(epochMilli));
    }

    public static String localDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant.PATTERN_DATETIME);
        return ldt.format(formatter);
    }

    public static String localDateToString(LocalDate ld) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant.PATTERN_DATE);
        return ld.format(formatter);
    }

    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant.PATTERN_DATE);
        return LocalDate.parse(date, formatter);
    }

    public static long localDateToMills(LocalDate ld) {
        return ld.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long localDateToSeconds(LocalDate ld) {
        return ld.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static long getCurrentSeconds() {
        return dateToSeconds(new Date());
    }

    public static long dateToSeconds(Date date) {
        return (date.getTime()) / 1000;
    }

    public static Date secondsToDate(long seconds) {
        return new Date(seconds * 1000L);
    }

}
