package me.jesuscodes.alias.util.base;

import android.content.Context;

/**
 * Created by alex
 */
public class ColorUtil {

    private static Context sContext;

    public static void init(Context c) {

        sContext = c;
    }

    public static int get(int id) {
        return sContext.getResources().getColor(id);
    }
}
