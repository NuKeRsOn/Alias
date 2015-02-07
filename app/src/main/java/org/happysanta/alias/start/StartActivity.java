package org.happysanta.alias.start;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import org.happysanta.alias.R;
import org.happysanta.alias.dictionaries.DictionariesActivity;
import org.happysanta.alias.game.GameActivity;
import org.happysanta.alias.settings.SettingsActivity;


public class StartActivity extends FragmentActivity {

    private ImageView topTextView;
    private Button settingButton;
    private Button dictionaryButton;
    private Button playButton;

    private SharedPreferences pref;
    private ViewPager pager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        settingButton = (Button) findViewById(R.id.settings);
        topTextView = (ImageView) findViewById(R.id.menu_header);
        dictionaryButton = (Button) findViewById(R.id.dictionary);
        playButton = (Button) findViewById(R.id.play);
        pager = (ViewPager) findViewById(R.id.pager);

        pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if (pref.getBoolean("first_start", true)) {
            showStart();
        } else {
            showMenu();
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GameActivity.class));
            }
        });
        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DictionariesActivity.class));
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
            }
        });


    }

    private void showStart() {
        dictionaryButton.setVisibility(View.GONE);
        settingButton.setVisibility(View.GONE);
        playButton.setVisibility(View.GONE);
        pager.setAdapter(new TourAdapter(getSupportFragmentManager()));
    }

    protected void showMenu() {
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putBoolean("first_start", false);
        prefEditor.apply();

        pager.animate().alpha(0).setDuration(300).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                pager.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
        dictionaryButton.setVisibility(View.VISIBLE);
        settingButton.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);

        if (topTextView.getVisibility()!=View.VISIBLE) {
            topTextView.setVisibility(View.VISIBLE);
            AlphaAnimation topTextViewAlphaAnimation = new AlphaAnimation(0, 1);
            topTextViewAlphaAnimation.setStartOffset(200);
            topTextViewAlphaAnimation.setDuration(900);
            topTextViewAlphaAnimation.setInterpolator(new DecelerateInterpolator());
            topTextView.startAnimation(topTextViewAlphaAnimation);
        }

        AlphaAnimation playButtonAlphaAnimation = new AlphaAnimation(0, 1);
        playButtonAlphaAnimation.setStartOffset(300);
        playButtonAlphaAnimation.setDuration(900);
        playButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        playButton.startAnimation(playButtonAlphaAnimation);

        AlphaAnimation settingsButtonAlphaAnimation = new AlphaAnimation(0, 1);
        settingsButtonAlphaAnimation.setStartOffset(350);
        settingsButtonAlphaAnimation.setDuration(900);
        settingsButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        settingButton.startAnimation(settingsButtonAlphaAnimation);

        AlphaAnimation dictionaryButtonAlphaAnimation = new AlphaAnimation(0, 1);
        dictionaryButtonAlphaAnimation.setStartOffset(400);
        dictionaryButtonAlphaAnimation.setDuration(900);
        dictionaryButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        dictionaryButton.startAnimation(dictionaryButtonAlphaAnimation);
    }

    public void next() {
        if(pager!=null){
            pager.setCurrentItem(pager.getCurrentItem()+1, true);
        }
    }
}

