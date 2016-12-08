package so.sao.analytics.sdk.worker.producers;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import so.sao.analytics.sdk.common.model.event.UserBasicInfo;
import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.kafka.logger.UserBasicInfoLogger;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

/**
 * User basic info kafkaProducer test
 *
 * @author senhui.li
 */
public class UserBasicInfoProducerTest extends ProducersTestSupport {

    @Override
    @Parameters({ "env" })
    @BeforeSuite
    public void init(int producerEnv) throws Exception {
        super.init(producerEnv);
        init.startUserBasicInfoProducer();
        userBasicInfoLogger = UserBasicInfoLogger.get();
    }

    @AfterMethod
    private void clearUserBasicInfoMsg() throws Exception {
        userBasicInfoLogger.clear();
    }

    @Test
    public void testUserRegister() throws Exception {
        UserBasicInfo event = UserBasicInfo.createInstance();
        event.setCompanyId(10027);

        InternalTracing it = event.createOrGetInternalTracing();
        it.setServiceDeployment("JingAnPlatform");
        it.setHostInstanceVer("1.1.0");
        it.setClientId("JingAnPlatformAccount");

        ExternalTracing et = event.createOrGetExternalTracing();
        et.setOriginIp("127.0.0.1");
        et.setUserAgent("Android Chrome");

        event.setNickName("Test");
        event.setSex(1);
        event.setAge(32);
        event.setBirthdayDate(new Date());
        event.setAvatarImgUrl("kpl://www.sao.so");
        event.setCountryName("中国");
        event.setProvinceName("上海");
        event.setCityName("上海");
        event.setDetailAddr("裕通路100号");
        event.setEmailAddr("senhui.li@sao365.cn");

        UserBasicInfoLogger.log(event);

        Assert.assertNotNull(userBasicInfoLogger.size());
        Assert.assertEquals(userBasicInfoLogger.size(), 1);

        Thread.sleep(10*1000);
    }

}