package so.sao.analytics.sdk.worker.producers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import so.sao.analytics.sdk.common.model.event.Activity;
import so.sao.analytics.sdk.kafka.logger.ActivityLogger;
import so.sao.analytics.sdk.worker.utils.EventBuilder;

/**
 * invalid msg kafkaProducer test
 *
 * @author senhui.li
 */
public class InvalidMsgProducerTest extends ProducersTestSupport {

    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startActivityProducer();
        activityLogger = ActivityLogger.get();
    }

    @AfterMethod
    public void clearInvalidMsg() throws Exception {
        activityLogger.clear();
    }

    @Test
    public void testThroughput() throws Exception {
        produceInvalidActivityEvent(ran.nextInt(5)+1);
    }


    private void produceInvalidActivityEvent(int len) {
        while (--len >= 0) {
            Activity event = EventBuilder.scan();
            event.setCompanyId(0);

            ActivityLogger.log(event);
        }
    }
}