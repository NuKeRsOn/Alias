package me.jesuscodes.alias.util;

import android.content.Context;

import me.jesuscodes.alias.R;

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

    public static int selectColor(int key) {
        switch (key) {
            case 0: return get(R.color.color_team_red);
            case 1: return get(R.color.color_team_yellow);
            case 2: return get(R.color.color_team_green);
            case 3: return get(R.color.color_team_blue);
            case 4: return get(R.color.color_team_purple);
            default: return get(R.color.color_team_red);
        }
    }
}
