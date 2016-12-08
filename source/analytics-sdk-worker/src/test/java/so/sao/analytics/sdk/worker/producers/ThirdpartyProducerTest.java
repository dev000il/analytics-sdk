package so.sao.analytics.sdk.worker.producers;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.common.model.event.Thirdparty;
import so.sao.analytics.sdk.common.model.property.ActionResult;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;
import so.sao.analytics.sdk.kafka.logger.ThirdpartyLogger;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

/**
 * Third party kafkaProducer test
 *
 * @author senhui.li
 */
public class ThirdpartyProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startThirdPartyProducer();
        thirdpartyLogger = ThirdpartyLogger.get();
    }

    @AfterMethod
    public void clearThirdpartyMsg() throws Exception {
        thirdpartyLogger.clear();
    }

    @Test
    public void testThroughput() throws Exception {
        produceThroughputClaim(ran.nextInt(30)+1);
    }


    private void produceThroughputClaim(int len) {
        while (--len >= 0) {
            Thirdparty event = Thirdparty.createMDBandwidthTopUp();

            Common common = event.createOrGetCommon();
            common.setCompanyId(new Random().nextInt(20) + 1);

            ActionResult ar = event.createOrGetActionResult();
            ar.setActionResult(1, "Successfully.");

            UserIdentifier ui = event.createOrGetUserIdentifier();
            ui.setUserId("1204565");
            ui.setPhoneNumber("13586654221");

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");

            event.setOperatorId(Integer.toString(new Random().nextInt(4) + 1));
            event.setPacketSize(100);
            ThirdpartyLogger.log(event);
        }
    }
}