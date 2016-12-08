package so.sao.analytics.sdk.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Get some system info about running machine
 *
 * @author senhui.li
 */
public class SysInfoUtil {

    public static String getHostNameForWindow() {
        return System.getenv("COMPUTERNAME");
    }

    public static String getHostNameForLinux() {
        try {
            return (InetAddress.getLocalHost()).getHostName();
        } catch (UnknownHostException uhe) {
            // host = "hostname: hostname"
            String host = uhe.getMessage();
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }

    public static String getHostName() {
        String hostName = getHostNameForWindow();
        if (hostName == null) {
            hostName = getHostNameForLinux();
        }

        return hostName;
    }
}
