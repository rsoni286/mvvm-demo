package com.delta.mvvm1.global;

public class GlobalLog {

    static boolean debug = true;

    public static void e(String message) {
        if (debug)
            android.util.Log.e("", message);
    }

    public static void e(String tag, String message) {
        if (debug)
            android.util.Log.e(tag, message);
    }

    public static void d(String message) {
        if (debug)
            android.util.Log.d("", message);
    }

    public static void d(String tag, String message) {
        if (debug)
            android.util.Log.d(tag, message);
    }

    public static void longLog(String TAG, String message) {
        int maxLogSize = 2000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            GlobalLog.d(TAG, message.substring(start, end));
        }
    }


}
