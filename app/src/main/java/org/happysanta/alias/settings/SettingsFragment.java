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

                new AlertDialog.Builder(getActivity())
                        .setTitle("О приложении")
                        .setView(View.inflate(getActivity(), R.layout.about, null))
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
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Play alias too!");
                intent.putExtra(Intent.EXTRA_TEXT, "Alias for your android!\nhttps://play.google.com/store/apps/details?id="+getActivity().getPackageName());
                try {
                    startActivity(Intent.createChooser(intent, "Share"));
                }catch (Exception exp){

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
                    //UtilityClass.showAlertDialog(context, ERROR, "Couldn't launch the market", null, 0);
                }
                return true;
            }
        });
    }
}
