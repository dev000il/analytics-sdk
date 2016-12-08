package so.sao.analytics.sdk.worker.producers;

import org.testng.annotations.AfterSuite;

import so.sao.analytics.sdk.kafka.logger.*;
import so.sao.analytics.sdk.work.ProducerInitialization;
import so.sao.analytics.sdk.work.kafka.KafkaProducerInitialization;
import so.sao.analytics.sdk.work.kinesis.kcl.KinesisKCLProducerInitialization;
import so.sao.analytics.sdk.work.kinesis.kpl.KinesisKPLProducerInitialization;

import java.util.Random;

/**
 * Initialize all producer test
 *
 * @author senhui.li
 */
public class ProducersTestSupport {

    protected ProducerInitialization init;

    protected ActivityLogger activityLogger;
    protected AppSystemLogger appSystemLogger;
    protected MgmtActionLogger mgmtActionLogger;
    protected TransactionLogger transactionLogger;
    protected ThirdpartyLogger thirdpartyLogger;
    protected UserBasicInfoLogger userBasicInfoLogger;
    protected OpenAPILogLogger openAPILogLogger;

    protected Random ran = new Random();

    public void init(int producerEnv) throws Exception {
        switch (producerEnv) {
            case 2:
                init = KinesisKCLProducerInitialization.create(Boolean.FALSE);
                break;
            case 3:
                init = KinesisKPLProducerInitialization.create(Boolean.FALSE);
                break;
            default:
                init = KafkaProducerInitialization.create(Boolean.FALSE);
                break;
        }
    }

    @AfterSuite
    public void destroy() throws Exception {

        // close thread
        if (init != null) {
            init.close();
        }
    }

}
