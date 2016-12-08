package so.sao.analytics.sdk.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

/**
 * Date util test
 *
 * @author senhui.li
 */
public class DateUtilTest {

    @Test
    public void testGetDefDateFormat() throws Exception {
        String date = DateUtil.getDefDateFormat(new Date());
        Calendar c = Calendar.getInstance();
        assertNotNull(date);
        assertTrue(date.indexOf(Integer.toString(c.get(Calendar.YEAR))) != -1);
    }

    @Test
    public void testGetCurrentGMT8() throws Exception {
        Date date = DateUtil.getCurrentGMT8();
        assertNotNull(date);
    }

    @Test
    public void testGetCurUTCTime() throws Exception {
        Date utcDate = DateUtil.getCurUTCTime();
        assertNotNull(utcDate);

        Calendar utcCal = Calendar.getInstance();
        utcCal.setTime(utcDate);

        Calendar gtm8Cal = Calendar.getInstance();
        gtm8Cal.setTime(DateUtil.getCurrentGMT8());

        assertTrue(utcCal.get(Calendar.HOUR_OF_DAY) < gtm8Cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(utcCal.get(Calendar.HOUR_OF_DAY) + 8, gtm8Cal.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testGetDateWithTimeZone() throws Exception {
        Date date = DateUtil.getDateWithTimeZone("GMT-8");
        System.out.println(DateUtil.getDefDateFormat(date));
        System.out.println(DateUtil.getDefDateFormat(DateUtil.getCurrentGMT8()));
        assertNotNull(date);
        assertTrue(new Date().getTime() > date.getTime());
    }
}