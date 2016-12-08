package so.sao.analytics.sdk.worker.producers;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import so.sao.analytics.sdk.kafka.logger.ActivityLogger;
import so.sao.analytics.sdk.kafka.logger.TransactionLogger;
import so.sao.analytics.sdk.worker.utils.EventBuilder;

import static org.testng.Assert.assertNotNull;

/**
 * Send all kinds of event together
 *
 * @author senhui.li
 */
@Test
public class IntegrationProducerTest extends ProducersTestSupport {

    @BeforeSuite
    public void init() throws Exception {
        init.startAppSystemProducer();
        init.startTransactionProducer();
        init.startActivityProducer();
    }

    @Test(threadPoolSize = 5, invocationCount = 4, timeOut = 20000)
    public void testSendActivity() throws Exception {
        EventBuilder.produceUserScan(10);
        EventBuilder.produceEnterLottery(10);
        EventBuilder.produceClaimMonetary(10);
        EventBuilder.produceClaimPhysical(10);

        assertNotNull(ActivityLogger.get().size());
    }

    @Test(threadPoolSize = 5, invocationCount = 4, timeOut = 20000)
    public void testSendTransaction() throws Exception {
        EventBuilder.producePointLottery(10);
        EventBuilder.produceClaimPhysicalRwd(10);

        assertNotNull(TransactionLogger.get().size());
    }
}
