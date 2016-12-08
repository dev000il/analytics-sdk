package so.sao.analytics.sdk.worker.producers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import so.sao.analytics.sdk.common.model.event.MgmtAction;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;
import so.sao.analytics.sdk.kafka.logger.MgmtActionLogger;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

import java.util.Random;

/**
 * Management action kafkaProducer test
 *
 * @author senhui.li
 */
public class MgmtActionProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startMgmtActionProducer();
        mgmtActionLogger = MgmtActionLogger.get();
    }

    @AfterMethod
    public void clearMgmtActionMsg() throws Exception {
        mgmtActionLogger.clear();
    }

    @Test
    public void testMgmtActionLog() throws Exception {
        producerMgmtAction(ran.nextInt(30)+1);
    }

    @Test(invocationCount = 5, dependsOnMethods = "testMgmtActionLog")
    public void testConcurrence() throws Exception {
        producerMgmtAction(100);
    }

    private void producerMgmtAction(int len) {
        while (--len >= 0) {
            MgmtAction event = MgmtAction.createInstance();

            Common common = event.createOrGetCommon();
            common.setCompanyId(new Random().nextInt(20) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");

            UserIdentifier ui = event.createOrGetUserIdentifier();
            ui.setUserId(Integer.toString(new Random().nextInt(200) + 1));

            event.setAction("test");
            event.setMessage("Produce message " + len + " times.");
            MgmtActionLogger.log(event);
        }
    }
}