package ca.allanwang.swiperecyclerview.library.logging;

import android.util.Log;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class RLog {

    private static final String TAG = "SRVLog";

    public static void v(String message, Object... o) {
        Log.v(TAG, String.format(message, o));
    }

    public static void d(String message, Object... o) {
        Log.d(TAG, String.format(message, o));
    }

    public static void i(String message, Object... o) {
        Log.i(TAG, String.format(message, o));
    }

    public static void w(String message, Object... o) {
        Log.w(TAG, String.format(message, o));
    }

    public static void e(String message, Object... o) {
        Log.e(TAG, String.format(message, o));
    }

    public static void a(String message, Object... o) {
        Log.wtf(TAG, String.format(message, o));
    }
}
