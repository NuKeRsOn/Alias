package org.happysanta.alias.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by alex
 */
public final class Prefs {

    private static final String PREF_MAIN_NAME = "AliasPreferences";
    private static final String TAG = "Prefs";

    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor sEditor;

    public static void init(Context context) {

        sPreferences        = context.getSharedPreferences(PREF_MAIN_NAME, Context.MODE_PRIVATE);
        sEditor             = sPreferences.edit();
    }

    //region Pref methods
    public static <T> void putAndCommit(String key, T value) {

        put(key, value);
        commit();
    }

    public static <T> void put(String key, T value) {

        if (value instanceof Integer) {

            sEditor.putInt(key, (Integer) value);
        }

        else if (value instanceof Long) {

            sEditor.putLong(key, (Long) value);
        }

        else if (value instanceof String) {

            sEditor.putString(key, (String) value);
        }

        else if (value instanceof Float) {

            sEditor.putFloat(key, (Float) value);
        }

        else if (value instanceof Boolean) {

            sEditor.putBoolean(key, (Boolean) value);
        }

        Log.d(TAG, "put: key(" + key + ") value(" + value + ")");

    }

    public static SharedPreferences get() {

        return sPreferences;
    }

    public static SharedPreferences get(Context c) {

        init(c);
        return sPreferences;
    }

    public static void commit() {

        sEditor.commit();
    }
}
