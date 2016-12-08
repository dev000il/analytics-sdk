package so.sao.analytics.sdk.worker.utils;

import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;

import java.util.Random;

/**
 * Test support utils
 *
 * @author senhui.li
 */
public class RandDataUtils {

    public static String randomHonestId() {
        StringBuffer honestId = new StringBuffer("T");
        String basic = "_*#@!-)(abcdefghijklmnopqrstuvwxyz0123456798ABCDEFGHIJKLKMNOPQRSTUVWXYZ";
        int leng = basic.length();
        for (int i = 1; i < 16; i++) {
            honestId.append(basic.charAt(new Random().nextInt(leng)));
        }
        return honestId.toString();
    }

    public static String randomUserAgent() {

        String[] browserAgents = { "Mozilla/5.0 (compatible; U; ABrowse 0.6; Syllable) AppleWebKit/420+ (KHTML, like Gecko)",
                "Mozilla/4.0 (compatible; Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0;)",
                "Mozilla/5.0 (BeOS; U; BeOS BeBox; fr; rv:1.9) Gecko/2008052906 BonEcho/2.0",
                "Opera/9.80 (S60; SymbOS; Opera Tablet/9174; U; en) Presto/2.7.81 Version/10.5",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14",
                "mozilla/5.0 (linux; android 4.1.2; zh-cn; mi-one plus build/jzo54k)  micromessenger/5.0.1.352",
                "mozilla/5.0 (iphone; iphone os 5_1_1 like mac os x)  mobile/9b206 micromessenger/5.0.1.352" };

        return browserAgents[new Random().nextInt(browserAgents.length)];
    }

    public static void randomUser(UserIdentifier ui) {
        String[] users = { "13854452145,3271,75646548,oa_djt2pOvewFmUVNqLJKA9G_qNs",
                "13825451214,3548,45646465,oa_djt5dPvRnDyLaEZok4HNzZOfY",
                "18625421156,8541,45646565,oa_djtwIyBJAAy8v3whjVTrcopgc",
                "18524565486,7456,02154588,oa_djt84TCIxSccqQaBh0h_quPZY",
                "18965484564,8562,25145656,oa_djt0xHMm0gmDnK7ymLXlLxlCg" };

        String[] user = users[new Random().nextInt(users.length - 1)].split(",");
        ui.setPhoneNumber(user[0]);
        ui.setUserId(user[1]);
        ui.setTaobaoUserId(user[2]);
        ui.setWechatUserId(user[3]);
    }

    public static void randomExternalTrace(ExternalTracing et) {
        String[] geo = randomGeo().split(",");

        et.setOriginIp(geo[0]);
        String[] geoip = geo[1].split("-");
        et.setGeoJson("[" + geoip[0] + "," + geoip[1] + "]");
        et.setUserAgent(randomUserAgent());
    }

    public static String randomGeo() {
        String[] geos = { "202.113.216.1,117.190182-39.125596", // 天津
                "202.115.128.1,104.065735-30.659462", // 四川,成都
                "202.116.192.1,113.280637-23.125178", // 广东,广州
                "202.114.160.1,114.298572-30.584355", // 湖北,武汉
                "202.118.176.1,126.642464-45.756967", // 黑龙江,哈尔滨
                "202.121.244.1,121.472644-31.231706", // 上海
                "202.193.112.1,110.299121-25.274215" // 广西,桂林
        };

        int ran = new Random().nextInt(geos.length - 1);

        return geos[ran];
    }
}
