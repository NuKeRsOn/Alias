package org.happysanta.alias;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasApplication extends Application {
    private static AliasApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        if(!BuildConfig.DEBUG) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.newTracker(R.xml.global_tracker);
        }
    }
    public static AliasApplication app(){
        return application;
    }
}
