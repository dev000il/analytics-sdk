package so.sao.analytics.sdk.worker.producers;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.event.OpenAPILog;
import so.sao.analytics.sdk.common.model.property.InternalTracing;
import so.sao.analytics.sdk.kafka.logger.OpenAPILogLogger;

/**
 * Open API log kafkaProducer test
 *
 * @author senhui.li
 */
public class OpenAPILogProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startOpenAPILogProducer();
        openAPILogLogger = OpenAPILogLogger.get();
    }

    @AfterMethod
    public void clearOpenAPILogMsg() throws Exception {
        openAPILogLogger.clear();
    }

    @Test
    public void testOpenAPILog() throws Exception {

        produceOpenAPILog(ran.nextInt(30)+1);
    }

    @Test(invocationCount = 5, dependsOnMethods = "testOpenAPILog")
    public void testConcurrence() throws Exception {
        produceOpenAPILog(100);
    }

    private void produceOpenAPILog(int len) {
        while (--len >= 0) {
            OpenAPILog event = OpenAPILog.createInstance();

            Common common = event.createOrGetCommon();
            common.setCompanyId(new Random().nextInt(20) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");

            event.setISV("localhost");
            event.setRequestUrl("https://www.sao.so/t/Ijfdkslo");
            event.setResponseStr("Success");
            event.setParameterStr("{company:345, productId: [67,432]}");
            event.setExceptionType("suc");
            event.setException(false);
            event.setExceptionCode("200");
            event.setExceptionMesg("None");

            OpenAPILogLogger.log(event);
        }
    }
}