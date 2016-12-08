package so.sao.analytics.sdk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date handle helper
 *
 * @author senhui.li
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DEF_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEF_SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getDefShortDateFormat(Date date) {
        return getDateFormat(date, DEF_SHORT_DATE_FORMAT);
    }

    public static String getDefDateFormat(Date date) {
        return getDateFormat(date, DEF_DATE_FORMAT);
    }

    public static Date getDateParse(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error("Parse date string[" + date + "] failed.", e);
            return null;
        }
    }

    public static Date getDefDateParse(String date) {
        return getDateParse(date, DEF_DATE_FORMAT);
    }

    public static Date getDefShortDateParse(String date) {
        return getDateParse(date, DEF_SHORT_DATE_FORMAT);
    }

    public static Date getCurrentGMT8() {
        return getDateWithTimeZone("GMT+8");
    }

    public static Date getCurUTCTime() {
        return getDateWithTimeZone("GMT+0");
    }

    public static Date getDateWithTimeZone(String timeZone) {
        DateFormat zoneFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        TimeZone dateTimeZone = TimeZone.getTimeZone(timeZone);
        zoneFormat.setTimeZone(dateTimeZone);

        Date localDate = new Date();
        Date zoneDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE_FORMAT);
            zoneDate = sdf.parse(zoneFormat.format(localDate));
        } catch (ParseException e) {
            logger.error("Get special date time with time zone[" + timeZone + "] failed.", e);
        }

        return zoneDate;
    }
}
