package me.jesuscodes.alias;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;

import me.jesuscodes.alias.util.ColorUtil;
import me.jesuscodes.alias.util.Dimen;
import me.jesuscodes.alias.util.Prefs;
import me.jesuscodes.alias.util.Toaster;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasApplication extends Application {

    private static AliasApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        if (!BuildConfig.DEBUG) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.newTracker(me.jesuscodes.alias.R.xml.global_tracker);
        }

        Dimen.init(this);
        ColorUtil.init(this);
        Prefs.init(this);
        Toaster.init(this);
    }

    public static AliasApplication app(){
        return application;
    }
}
