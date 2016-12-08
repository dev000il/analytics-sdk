package so.sao.analytics.sdk.worker.producers;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.kafka.logger.ActivityLogger;
import so.sao.analytics.sdk.worker.utils.EventBuilder;

/**
 * Activity kafkaProducer test
 *
 * @author senhui.li
 */
public class ActivityProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startActivityProducer();
        activityLogger = ActivityLogger.get();
    }


    @AfterMethod
    private void clearActivityMsg() throws Exception {
        activityLogger.clear();
    }

    @Test
    public void testSendActivity() throws Exception {
        EventBuilder.produceUserScan(40);
//        EventBuilder.produceEnterLottery(4000);
//        EventBuilder.produceClaimMonetary(1000);
//        EventBuilder.produceClaimPhysical(2500);

        assertNotNull(activityLogger.size());

        Thread.sleep(60 * 1000);
    }

    @Test(priority = 1)
    public void testSendUserScanMessage() throws Exception {
        EventBuilder.produceUserScan(ran.nextInt(20) + 1);
        assertNotNull(activityLogger.size());
    }

    @Test(priority = 2)
    public void testSendEnterLotteryMessage() throws Exception {
        EventBuilder.produceEnterLottery(ran.nextInt(30)+1);
        assertNotNull(activityLogger.size());
    }

    @Test(priority = 3)
    public void testSendClaimMonetaryMessage() throws Exception {
        EventBuilder.produceClaimMonetary(ran.nextInt(10)+1);
        assertNotNull(activityLogger.size());
    }

    @Test(priority = 4)
    public void testSendClaimPhysicalMessage() throws Exception {
        EventBuilder.produceClaimPhysical(ran.nextInt(20)+1);
        assertNotNull(activityLogger.size());
    }

}