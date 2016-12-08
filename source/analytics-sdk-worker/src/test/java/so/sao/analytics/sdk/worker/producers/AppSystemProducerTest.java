package so.sao.analytics.sdk.worker.producers;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.common.model.event.AppSystem;
import so.sao.analytics.sdk.kafka.logger.AppSystemLogger;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

/**
 * AppSystemProducer Test
 *
 * @author senhui.li
 */
public class AppSystemProducerTest extends ProducersTestSupport {

    private String[] level = { "DEBUG", "WARN", "INFO", "ERROR" };

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startAppSystemProducer();
        appSystemLogger = AppSystemLogger.get();
    }

    @AfterMethod
    private void clearAppSystemMsg() throws Exception {
        appSystemLogger.clear();
    }

    @Test
    public void testApplicationSystemLog() throws Exception {
        produceAppSystem(ran.nextInt(40)+1);
    }

    private void produceAppSystem(int len) {

        while (--len >= 0) {
            AppSystem event = AppSystem.createInstance();

            event.setCompanyId(new Random().nextInt(20) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");
            event.setAppSystem(len, 35, level[new Random().nextInt(level.length)], "User had click " + len + " times.");

            AppSystemLogger.log(event);
        }
    }
}