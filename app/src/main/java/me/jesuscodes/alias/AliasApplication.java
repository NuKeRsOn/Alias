package me.jesuscodes.alias;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import me.jesuscodes.alias.util.ColorUtil;
import me.jesuscodes.alias.util.Dimen;
import me.jesuscodes.alias.util.Prefs;
import me.jesuscodes.alias.util.Toaster;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasApplication extends Application {

    private static AliasApplication sApplication;
    private static Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;

        if (!BuildConfig.DEBUG) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            sTracker = analytics.newTracker(me.jesuscodes.alias.R.xml.global_tracker);
        }

        Dimen.init(this);
        ColorUtil.init(this);
        Prefs.init(this);
        Toaster.init(this);
    }

    public static AliasApplication app(){
        return sApplication;
    }

    public static void sendEvent(String category, String action, String label, long value) {

        if (sTracker != null)
            sTracker.send(new HitBuilders.EventBuilder()
                            .setCategory(category)
                            .setAction(action)
                            .setLabel(label)
                            .setValue(value)
                            .build()
        );
    }

    public static void sendEvent(String category, String action, String label) {

        if (sTracker != null)
            sTracker.send(new HitBuilders.EventBuilder()
                            .setCategory(category)
                            .setAction(action)
                            .setLabel(label)
                            .build()
            );
    }
}
