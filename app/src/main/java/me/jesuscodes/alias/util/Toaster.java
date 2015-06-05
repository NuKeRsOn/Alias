package me.jesuscodes.alias.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by alex
 */
public class Toaster {

    private static Context sContext;

    public static void init(Context context) {

        sContext = context;
    }

    public static void say(String words) {

        Toast.makeText(sContext, words, Toast.LENGTH_SHORT).show();
    }

    public static void say(int resid) {
        Toast.makeText(sContext, resid, Toast.LENGTH_SHORT).show();
    }
}
