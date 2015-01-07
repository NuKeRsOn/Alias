package org.happysanta.alias.start;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.happysanta.alias.game.CreateTeamsFragment;
import org.happysanta.alias.game.GameActivity;
import org.happysanta.alias.R;
import org.happysanta.alias.settings.SettingsActivity;
import org.happysanta.alias.util.AnimationEndListener;


public class StartActivity extends Activity {

    private ImageView startTextView;
    private ImageView topTextView;
    private Button settingButton;
    private Button dictionaryButton;
    private Button playButton;

    private ImageView rulesImageView;
    private TextView rulesTextView;
    private Button rulesAgreeButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        settingButton = (Button) findViewById(R.id.settings);
        startTextView = (ImageView) findViewById(R.id.splash_text);
        topTextView = (ImageView) findViewById(R.id.menu_header);
        rulesTextView = (TextView) findViewById(R.id.rules_text);
        rulesImageView = (ImageView) findViewById(R.id.rules_image);
        rulesAgreeButton = (Button) findViewById(R.id.agree);
        dictionaryButton = (Button) findViewById(R.id.dictionary);
        playButton = (Button) findViewById(R.id.play);
        showStart();



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GameActivity.class));
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
        AlphaAnimation textAlphaAnimation = new AlphaAnimation(0, 1);
        textAlphaAnimation.setStartOffset(300);
        textAlphaAnimation.setDuration(900);
        textAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        textAlphaAnimation.setAnimationListener(new AnimationEndListener() {


            @Override
            public void onAnimationEnd(Animation animation) {

                AlphaAnimation textInvisibleAlphaAnimation = new AlphaAnimation(1, 0);
                textInvisibleAlphaAnimation.setStartOffset(500);
                textInvisibleAlphaAnimation.setDuration(700);
                textInvisibleAlphaAnimation.setInterpolator(new DecelerateInterpolator());
                startTextView.startAnimation(textInvisibleAlphaAnimation);
                textInvisibleAlphaAnimation.setAnimationListener(new AnimationEndListener() {


                    @Override
                    public void onAnimationEnd(Animation animation) {

                        startTextView.setVisibility(View.GONE);
                        boolean firstStart = true;
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                        firstStart = pref.getBoolean("first_start", true);
                        if(firstStart){
                            showFirstRule();
                        }else{
                            showMenu();
                        }
                    }


                });
            }

        });
        startTextView.startAnimation(textAlphaAnimation);

    }

    private void showMenu() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putBoolean("first_start", false);
        prefEditor.apply();

        dictionaryButton.setVisibility(View.VISIBLE);
        settingButton.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);



        if (topTextView.getVisibility()!=View.VISIBLE) {
            topTextView.setVisibility(View.VISIBLE);
            AlphaAnimation topTextViewAlphaAnimation = new AlphaAnimation(0, 1);
            topTextViewAlphaAnimation.setStartOffset(300);
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
        settingsButtonAlphaAnimation.setStartOffset(300);
        settingsButtonAlphaAnimation.setDuration(900);
        settingsButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        settingButton.startAnimation(settingsButtonAlphaAnimation);

        AlphaAnimation dictionaryButtonAlphaAnimation = new AlphaAnimation(0, 1);
        dictionaryButtonAlphaAnimation.setStartOffset(300);
        dictionaryButtonAlphaAnimation.setDuration(900);
        dictionaryButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        dictionaryButton.startAnimation(dictionaryButtonAlphaAnimation);


    }

    private void showFirstRule() {
        rulesAgreeButton.setVisibility(View.VISIBLE);
        topTextView.setVisibility(View.VISIBLE);
        rulesTextView.setVisibility(View.VISIBLE);
        rulesImageView.setVisibility(View.VISIBLE);

        AlphaAnimation imageAlphaAnimation = new AlphaAnimation(0, 1);
        imageAlphaAnimation.setStartOffset(300);
        imageAlphaAnimation.setDuration(900);
        imageAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        rulesImageView.startAnimation(imageAlphaAnimation);


        AlphaAnimation agreeButtonAlphaAnimation = new AlphaAnimation(0, 1);
        agreeButtonAlphaAnimation.setStartOffset(300);
        agreeButtonAlphaAnimation.setDuration(900);
        agreeButtonAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        rulesAgreeButton.startAnimation(agreeButtonAlphaAnimation);

        AlphaAnimation topTextViewAlphaAnimation = new AlphaAnimation(0, 1);
        topTextViewAlphaAnimation.setStartOffset(300);
        topTextViewAlphaAnimation.setDuration(900);
        topTextViewAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        topTextView.startAnimation(topTextViewAlphaAnimation);



        animateRule("Создайте команды", R.drawable.pic1, new AnimationEndListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                showSecondRule();
            }
        });

    }

    private void showSecondRule() {
        animateRule("Объясните слово, не говоря его", R.drawable.pic2, new AnimationEndListener() {


            @Override
            public void onAnimationEnd(Animation animation) {
                showThirdRule();
            }

        });
    }

    private void showThirdRule() {

        animateRule("Выкидывайте карточки", R.drawable.pic3, new AnimationEndListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                rulesTextView.setVisibility(View.GONE);
                rulesAgreeButton.setVisibility(View.GONE);
                rulesImageView.setVisibility(View.GONE);

                AlphaAnimation topTextViewAlphaAnimation = new AlphaAnimation(1, 0);
                topTextViewAlphaAnimation.setDuration(350);
                topTextViewAlphaAnimation.setStartOffset(100);
                topTextViewAlphaAnimation.setInterpolator(new DecelerateInterpolator());
                //topTextView.startAnimation(topTextViewAlphaAnimation);



                showMenu();
            }
        });

    }

    private void animateRule(String ruleText, int ruleImageId, final AnimationEndListener animationEndListener){

        rulesTextView.setText(ruleText);
        rulesImageView.setImageResource(ruleImageId);

        AnimationSet imageAnimationSet = new AnimationSet(false);
        imageAnimationSet.setDuration(350);
        imageAnimationSet.setStartOffset(100);

        AlphaAnimation imageAlphaAnimation = new AlphaAnimation(0, 1);
        imageAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        imageAnimationSet.addAnimation(imageAlphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
        );

        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        imageAnimationSet.addAnimation(translateAnimation);

        rulesImageView.startAnimation(imageAnimationSet);




        final AnimationSet rulesAnimationSet = new AnimationSet(false);
        rulesAnimationSet.setDuration(350);
        rulesAnimationSet.setStartOffset(100);

        AlphaAnimation rulesTextViewAlphaAnimation = new AlphaAnimation(0, 1);
        rulesTextViewAlphaAnimation.setInterpolator(new DecelerateInterpolator());
        rulesAnimationSet.addAnimation(rulesTextViewAlphaAnimation);

        TranslateAnimation rulesTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
        );

        rulesTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rulesAnimationSet.addAnimation(rulesTranslateAnimation);

        rulesTextView.startAnimation(rulesAnimationSet);


        rulesAnimationSet.setAnimationListener(new AnimationEndListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                rulesAgreeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AnimationSet imageAnimationSet = new AnimationSet(false);
                        imageAnimationSet.setDuration(350);

                        AlphaAnimation imageAlphaAnimation = new AlphaAnimation(1, 0);
                        imageAlphaAnimation.setInterpolator(new DecelerateInterpolator());
                        imageAnimationSet.addAnimation(imageAlphaAnimation);

                        TranslateAnimation rulesImageTranslateAnimation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0f,
                                Animation.RELATIVE_TO_SELF, -0.5f,
                                Animation.RELATIVE_TO_SELF, 0f,
                                Animation.RELATIVE_TO_SELF, 0f
                        );

                        rulesImageTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                        imageAnimationSet.addAnimation(rulesImageTranslateAnimation);


                        rulesImageView.startAnimation(imageAnimationSet);

                        AnimationSet rulesAnimationSet = new AnimationSet(false);
                        rulesAnimationSet.setDuration(350);

                        AlphaAnimation rulesTextViewAlphaAnimation = new AlphaAnimation(1, 0);
                        rulesTextViewAlphaAnimation.setInterpolator(new DecelerateInterpolator());
                        rulesAnimationSet.addAnimation(rulesTextViewAlphaAnimation);

                        TranslateAnimation rulesTranslateAnimation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0f,
                                Animation.RELATIVE_TO_SELF, -0.5f,
                                Animation.RELATIVE_TO_SELF, 0f,
                                Animation.RELATIVE_TO_SELF, 0f

                        );
                        rulesAnimationSet.addAnimation(rulesTranslateAnimation);
                        rulesAnimationSet.setAnimationListener(animationEndListener);
                        rulesTextView.startAnimation(rulesAnimationSet);
                    }
                });
            }


        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
