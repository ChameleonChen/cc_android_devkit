package com.cc.android.devkit.com.cc.android.devkit.cclog;

import android.util.Log;

import java.util.Locale;

/**
 * Created by ChameleonChen on 2015/8/31.
 */
public class LogUtil {
    /** this is the tag of the log message, you can reset the value by using {@link #setTag(String)}*/
    public static String TAG = "LOG_UTIL";

    /** if it's true, meas that the info message in level high than VERBOSE should be logged. */
    public static boolean DEBUG = Log.isLoggable(TAG, Log.VERBOSE);

    public static void setTag(String tag) {
        TAG = tag;
        DEBUG = Log.isLoggable(TAG, Log.VERBOSE);
    }

    public static void d(String format, Object ...args) {
        if (DEBUG)
            Log.d(TAG, buildMessage(format, args));
    }

    public static void v(String format, Object ...args) {
        if (DEBUG)
            Log.v(TAG, buildMessage(format, args));
    }

    public static void i(String format, Object ...args) {
        if (DEBUG)
            Log.i(TAG, buildMessage(format, args));
    }

    public static void e(String format, Object ...args) {
        if (DEBUG)
            Log.e(TAG, buildMessage(format, args));
    }

    public static void e(Throwable throwable, String format, Object ...args) {
        if (DEBUG)
            Log.e(TAG, buildMessage(format, args), throwable);
    }

    public static void wtf(String format, Object ...args) {
        if (DEBUG)
            Log.wtf(TAG, buildMessage(format, args));
    }

    public static void wtf(Throwable throwable, String format, Object ...args) {
        if (DEBUG)
            Log.wtf(TAG, buildMessage(format, args), throwable);
    }

    private static String  buildMessage(String format, Object ...args) {
        // get the message that the user want to log.
        String msg = (args == null) ? format : String.format(Locale.US, format);

        // get the information about the caller method which calling LogUtil 's log method;
        String caller = "<unknown>";
        StackTraceElement[] traces = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < traces.length; ) {
            Class<?> clazz = traces[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                String callingClass = traces[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + traces[i].getMethodName();
                break;
            }
        }

        return String.format(Locale.US, "[%d] %s : %s",
                Thread.currentThread().getId(), caller, msg);
    }
}
