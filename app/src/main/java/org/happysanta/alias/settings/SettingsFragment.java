package org.happysanta.alias.settings;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
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

        Preference aboutPreference = findPreference("about");
        aboutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                View aboutView = View.inflate(getActivity(), R.layout.about, null);
                View santa = aboutView.findViewById(R.id.santa);
                santa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/happysanta")));
                    }
                });
                new AlertDialog.Builder(getActivity())
                        .setTitle("О приложении")
                        .setView(aboutView)
                        .setPositiveButton("Ясно", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(true)
                        .show().setCanceledOnTouchOutside(true);

                return true;
            }
        });
        Preference sharePref = findPreference("share");
        sharePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                try {

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, ("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName()));
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Поиграйте в Alias на андроид");
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "Поделиться"));

                } catch (Exception exp){

                }

                return true;
            }
        });


        Preference ratePref = findPreference("rate");
        ratePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                }
                return true;
            }
        });
    }
}
