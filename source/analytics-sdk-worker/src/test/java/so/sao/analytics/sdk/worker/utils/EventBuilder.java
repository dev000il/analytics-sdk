package so.sao.analytics.sdk.worker.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import so.sao.analytics.sdk.kafka.logger.ActivityLogger;
import so.sao.analytics.sdk.common.enums.ActivityType;
import so.sao.analytics.sdk.common.model.event.Activity;
import so.sao.analytics.sdk.common.model.event.Transaction;
import so.sao.analytics.sdk.common.model.property.ActionResult;
import so.sao.analytics.sdk.common.model.property.AdditionalMetadata;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.common.model.property.GainPoints;
import so.sao.analytics.sdk.common.model.property.InternalTracing;
import so.sao.analytics.sdk.common.model.property.MonetaryRewardInfo;
import so.sao.analytics.sdk.common.model.property.PhysicalRewardInfo;
import so.sao.analytics.sdk.common.model.property.ProductInfo;
import so.sao.analytics.sdk.common.model.property.PromotionInfo;
import so.sao.analytics.sdk.common.model.property.ShopInfo;
import so.sao.analytics.sdk.common.model.property.SpentPoints;
import so.sao.analytics.sdk.common.model.property.TagInfo;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;
import so.sao.analytics.sdk.common.model.property.WinRewards;
import so.sao.analytics.sdk.kafka.logger.TransactionLogger;

/**
 * All kinds event builder
 *
 * @author senhui.li
 */
public class EventBuilder {

    public static int companyId = 35;
    public static String orderNum = "20160121182345887";
    public static long soleDistributorId = 1000001;

    public static Activity scan() {
        Activity event = Activity.createUserScan();

        Common common = event.createOrGetCommon();
        common.setCompanyId(companyId);

        InternalTracing it = event.createOrGetInternalTracing();
        it.setServiceDeployment("localRole");
        it.setClientId("local");
        it.setHostInstanceVer("1.0.1");

        ExternalTracing et = event.createOrGetExternalTracing();
        RandDataUtils.randomExternalTrace(et);

        TagInfo ti = event.createOrGetTagInfo();
        ti.setTagInfo(RandDataUtils.randomHonestId(), "20160217", "20160217", new Random().nextInt(20), orderNum,
                soleDistributorId);

        ProductInfo pi = event.createOrGetProductInfo();
        pi.setProductId(new Random().nextInt(10) + 2);
        pi.setSpecId("186" + new Random().nextInt(1000));

        UserIdentifier ui = event.createOrGetUserIdentifier();
        RandDataUtils.randomUser(ui);

        return event;
    }

    public static Activity enterLottery() {
        Activity event = scan();

        event.setActivityType(ActivityType.EnterLottery.getValue());

        Map<String, Integer> gainPoints = new HashMap<>();
        gainPoints.put("10", 20);
        gainPoints.put("12", 40);

        GainPoints gp = event.createOrGetGainPoints();
        gp.setGainPoints(10, gainPoints);

        Map<String, String> rwds = new HashMap<>();
        rwds.put("10", "20.00");

        WinRewards wr = event.createOrGetWinRewards();
        wr.setWinRewards(10, 6, null, rwds);

        return event;
    }

    public static Activity claimMonetary() {
        Activity event = scan();

        event.setActivityType(ActivityType.ClaimReward.getValue());

        UserIdentifier ui = event.createOrGetUserIdentifier();
        RandDataUtils.randomUser(ui);

        WinRewards wr = event.createOrGetWinRewards();
        wr.setRewardId(new Random().nextInt(10) + 1);
        wr.setRewardType("1");
        wr.appendRewardAdwarded("2", "100.000");
        wr.appendRewardAdwarded("5", "80.000");
        wr.setRewardAmount("100.0");

        MonetaryRewardInfo mri = event.createOrGetMonetaryRewardInfo();
        mri.setMonetaryRewardInfo("342342", "3242424", "ChinaUnion", "32422342", "323", "73456");

        ActionResult ar = event.createOrGetActionResult();
        ar.setActionResult(1, "Claim monetary reward success.");

        return event;
    }

    public static Activity claimPhysical() {
        Activity event = scan();

        event.setActivityType(ActivityType.ClaimReward.getValue());

        ShopInfo si = event.createOrGetShopInfo();
        si.setShopId(4514);

        UserIdentifier ui = event.createOrGetUserIdentifier();
        RandDataUtils.randomUser(ui);

        WinRewards wr = event.createOrGetWinRewards();
        wr.setRewardId(new Random().nextInt(20) + 10);
        wr.setRewardType("3");
        wr.appendRewardAdwarded("2", "100.000");
        wr.setRewardAmount("100.0");

        PhysicalRewardInfo pri = event.createOrGetPhysicalRewardInfo();
        pri.setPhysicalRewardInfo("China", "134324432443", "上海", "上海", "裕通路100号2305室", "441481185604258451");

        ActionResult ar = event.createOrGetActionResult();
        ar.setActionResult(1, "Claim monetary reward success.");

        return event;
    }

    public static void produceUserScan(int len) {
        while (--len >= 0) {
            Activity event = scan();

            AdditionalMetadata am = event.createOrGetAddMetadata();
            am.setIdentifyPublicLabel("vip会员");
            am.setRemark("UserScan");

            ActivityLogger.log(event);
        }
    }

    public static void produceEnterLottery(int len) {
        while (--len >= 0) {
            Activity event = enterLottery();

            AdditionalMetadata am = event.createOrGetAddMetadata();
            am.setIdentifyPublicLabel("vip");
            am.setRemark("UserEnterLottery");
            am.setContinueScanCount(10);

            PromotionInfo pri = event.createOrGetPromotionInfo();
            pri.setPromotionInfo(new Random().nextInt(10) + 1, (len % 2 == 0));

            ActivityLogger.log(event);
        }
    }

    public static void produceClaimMonetary(int len) {
        while (--len >= 0) {
            Activity event = claimMonetary();

            AdditionalMetadata am = event.createOrGetAddMetadata();
            am.setIdentifyPublicLabel("vip");
            am.setRemark("ClaimMonetary");
            am.setContinueScanCount(10);

            PromotionInfo pri = event.createOrGetPromotionInfo();
            pri.setPromotionInfo(new Random().nextInt(10) + 1, (len % 2 == 0));

            ActivityLogger.log(event);
        }
    }

    public static void produceClaimPhysical(int len) {
        while (--len >= 0) {
            Activity event = claimPhysical();

            AdditionalMetadata am = event.createOrGetAddMetadata();
            am.setIdentifyPublicLabel("vip");
            am.setRemark("ClaimPhysical");
            am.setContinueScanCount(10);

            PromotionInfo pri = event.createOrGetPromotionInfo();
            pri.setPromotionInfo(new Random().nextInt(10) + 1, (len % 2 == 0));

            ActivityLogger.log(event);
        }
    }

    public static void producePointLottery(int len) {
        while (--len >= 0) {
            Transaction event = Transaction.createPointLottery();

            Common common = event.createOrGetCommon();
            common.setCompanyId(new Random().nextInt(20) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");

            ExternalTracing et = event.createOrGetExternalTracing();
            RandDataUtils.randomExternalTrace(et);

            UserIdentifier ui = event.createOrGetUserIdentifier();
            RandDataUtils.randomUser(ui);

            event.setLotteryId(new Random().nextInt(10) + 1);
            SpentPoints sp = event.createOrGetSpentPoints();
            sp.setSpentPoints(2, 20);
            Map<String, String> rwds = new HashMap<>();
            rwds.put(Integer.toString(len + 1), "324.00");

            WinRewards wr = event.createOrGetWinRewards();
            wr.setWinRewards(len + 1, 3, null, rwds);

            TransactionLogger.log(event);
        }
    }

    public static void produceClaimPhysicalRwd(int len) {
        while (--len >= 0) {
            Transaction event = Transaction.createPointLotteryClaim();

            Common common = event.createOrGetCommon();
            common.setCompanyId(new Random().nextInt(20) + 1);

            InternalTracing it = event.createOrGetInternalTracing();
            it.setServiceDeployment("localRole" + len);
            it.setClientId("local");
            it.setHostInstanceVer("1.0.1");

            ExternalTracing et = event.createOrGetExternalTracing();
            RandDataUtils.randomExternalTrace(et);

            UserIdentifier ui = event.createOrGetUserIdentifier();
            RandDataUtils.randomUser(ui);

            event.setLotteryId(new Random().nextInt(10) + 1);

            WinRewards wr = event.createOrGetWinRewards();
            wr.setWinRewards(len + 1, 3, "324.00", null);

            PhysicalRewardInfo pri = event.createOrGetPhysicalRewardInfo();
            pri.setPhysicalRewardInfo("China", "134324432443", "上海", "上海", "裕通路100号2305室", "441481185604258451");

            ActionResult ar = event.createOrGetActionResult();
            ar.setActionResult(1, "Claim physical reward from point lottery success.");

            TransactionLogger.log(event);
        }
    }
}
