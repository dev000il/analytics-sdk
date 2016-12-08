package so.sao.analytics.sdk.exclude;

import java.util.Random;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.common.model.event.Activity;
import so.sao.analytics.sdk.kafka.logger.ActivityLogger;
import so.sao.analytics.sdk.common.model.property.ActionResult;
import so.sao.analytics.sdk.common.model.property.AdditionalMetadata;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.common.model.property.InternalTracing;
import so.sao.analytics.sdk.common.model.property.ProductInfo;
import so.sao.analytics.sdk.common.model.property.PromotionInfo;
import so.sao.analytics.sdk.common.model.property.TagInfo;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;

/**
 * Test large data test for activity
 *
 * @author senhui.li
 */
@SuppressWarnings("unused")
public class LargeActivitiesData {

    private ActivityLogger log100000 = new ActivityLogger();
    private ActivityLogger log1Million = new ActivityLogger();
    private ActivityLogger log1Billion = new ActivityLogger();

    @BeforeTest
    public void init() throws Exception {
        String topic = "activites";
        String dbPath100000 = "activities_100000.db";
        String dbPath1Million = "activities_million.db";
        String dbPath1Billion = "activities_billion.db";

        String localPath = System.getProperty("user.dir");

        /*
         * log100000.init(localPath, dbPath100000, topic);
         * log1Million.init(localPath, dbPath1Million, topic);
         * log1Billion.init(localPath, dbPath1Billion, topic);
         */
    }

    @Test
    public void test100000() throws Exception {
        long t1 = System.currentTimeMillis();
        produceFullActity(100000, log100000);
        long t2 = System.currentTimeMillis();
        System.out.println("100000 spent time: " + (t2 - t1));
        // log100000.close();
    }

    @Test(invocationCount = 10, dependsOnMethods = "test100000")
    public void test1Million() throws Exception {
        long t1 = System.currentTimeMillis();
        produceFullActity(100000, log1Million);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "1 million spent time: " + (t2 - t1));
    }

    @Test(dependsOnMethods = "test1Million", invocationCount = 100)
    public void test1Billion() throws Exception {
        long t1 = System.currentTimeMillis();
        produceFullActity(1000000, log1Billion);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "1 billion spent time: " + (t2 - t1));
    }

    private void produceFullActity(long length, ActivityLogger activityLog) {

        Random ran = new Random();

        while (--length >= 0) {
            System.out.println(length);
            Activity event = new Activity();
            event.setActivityType(5);
            event.setSharingId(ran.nextInt(1000));
            event.setSharingTarget(3);

            Common common = event.createOrGetCommon();
            common.setCompanyId(ran.nextInt(100) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + length);

            ExternalTracing et = event.createOrGetExternalTracing();
            et.setOriginIp("192.168.8.222");
            et.setGeoJson("132.12556,35.24565646");
            et.setUserAgent("mozilla/5.0 Linux");

            UserIdentifier ui = event.createOrGetUserIdentifier();
            ui.setWechatUserId("Ikdsoijeiwoejfoifjewofwf");
            ui.setTaobaoUserId("154865");
            ui.setUserId("3382");
            ui.setPhoneNumber("13826686225");

            AdditionalMetadata am = event.createOrGetAddMetadata();
            am.setIdentifyPublicLabel("VIP Card");
            am.setAdvisorPhoneNum("1385556588");
            am.setRecommenderPhoneNum("13825568552");
            am.setCompanyNewUser(true);

            ProductInfo pi = event.createOrGetProductInfo();
            pi.setProductId(1546);
            pi.setSpecId("20610201");

            TagInfo ti = event.createOrGetTagInfo();
            ti.setTagInfo("", "12053", "123456656", 35, "20160220", 1256522);

            PromotionInfo pri = event.createOrGetPromotionInfo();
            pri.setPromotionInfo(34, true);

            ActionResult ar = event.createOrGetActionResult();
            ar.setActionResult(1, "OK!");

            ActivityLogger.log(event);
        }
    }
}
