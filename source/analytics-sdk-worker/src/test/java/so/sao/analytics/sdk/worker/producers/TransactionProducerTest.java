package so.sao.analytics.sdk.worker.producers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.kafka.logger.TransactionLogger;
import so.sao.analytics.sdk.worker.utils.EventBuilder;

import static org.testng.Assert.assertNotNull;

/**
 * Transaction kafkaProducer test
 *
 * @author senhui.li
 */
public class TransactionProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startTransactionProducer();
        transactionLogger = TransactionLogger.get();
    }

    @AfterMethod
    public void clearTransactionMsg() throws Exception {
        transactionLogger.clear();
    }

    @Test
    public void testSendTransaction() throws Exception {
        EventBuilder.producePointLottery(10000);
        EventBuilder.produceClaimPhysicalRwd(10000);

        assertNotNull(transactionLogger.size());
        Thread.sleep(100 * 1000);
    }

    @Test(priority = 1)
    public void testPointLottery() throws Exception {
        EventBuilder.producePointLottery(ran.nextInt(30)+1);
    }

    @Test(priority = 2)
    public void testCliamPointLotteryPhysicalRwd() throws Exception {
        EventBuilder.produceClaimPhysicalRwd(ran.nextInt(20)+1);
    }

}
