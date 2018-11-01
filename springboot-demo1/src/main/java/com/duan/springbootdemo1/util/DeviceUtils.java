package com.duan.springbootdemo1.util;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * Created on 2018/10/31.
 * https://blog.csdn.net/qq_23832313/article/details/80760362
 *
 * @author DuanJiaNing
 */
public class DeviceUtils {

    public static String getDeviceType() {
        String agentString = ServletUtil.getRequest().getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agentString);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        eu.bitwalker.useragentutils.DeviceType deviceType = operatingSystem.getDeviceType();

        switch (deviceType) {
            case COMPUTER:
                return "PC";
            case TABLET: {
                if (agentString.contains("Android")) return "Android Pad";
                if (agentString.contains("iOS")) return "iPad";
                return "Unknown";
            }
            case MOBILE: {
                if (agentString.contains("Android")) return "Android";
                if (agentString.contains("iOS")) return "IOS";
                return "Unknown";
            }
            default:
                return "Unknown";
        }

    }

}
