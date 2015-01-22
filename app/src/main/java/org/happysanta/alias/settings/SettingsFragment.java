package org.happysanta.alias.settings;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;

import org.happysanta.alias.R;

/**
 * Created by Jesus Christ. Amen.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addPreferencesFromResource(R.xml.pref_general);
    }
}
