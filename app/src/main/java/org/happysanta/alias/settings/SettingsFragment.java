package org.happysanta.alias.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.happysanta.alias.R;

/**
 * Created by Jesus Christ. Amen.
 */
public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        Button rulesButton = (Button) rootView.findViewById(R.id.rules);
        Button aboutButton = (Button) rootView.findViewById(R.id.about);





        return rootView;

    }
}
