package com.duan.nettydemo;

/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
public class Logger {

    public static void info(org.slf4j.Logger log, String methodName, String msg) {
        log.info(str(methodName, msg));
    }

    public static void debug(org.slf4j.Logger log, String methodName, String msg) {
        log.debug(str(methodName, msg));
    }

    public static void error(org.slf4j.Logger log, String methodName, String msg) {
        log.error(str(methodName, msg));
    }

    private static String str(String methodName, String msg) {
//        String date = new DateFormatter("yyyy-MM-dd HH:mm:ss").print(new Date(), Locale.CHINA);
        return "-------------------- #" + methodName + " | " + msg;
    }

}
