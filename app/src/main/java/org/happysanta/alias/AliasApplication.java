package org.happysanta.alias;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;

import org.happysanta.alias.util.Dimen;
import org.happysanta.alias.util.Prefs;
import org.happysanta.alias.util.Toaster;
import org.happysanta.alias.util.base.ColorUtil;

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
            analytics.newTracker(R.xml.global_tracker);
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
