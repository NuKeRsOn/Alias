package org.happysanta.alias.game;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Space;
import android.widget.TextView;


import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasDictionary;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.models.AliasWord;
import org.happysanta.alias.util.AnimationEndListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jesus Christ. Amen.
 */
public class GameFragment extends Fragment {

    private TextView wordView;
    private Button okButton;
    private Button niokButton;
    private TextView timerView;
    private View teamText;

    private GameActivity activity;
    // timer
    private Handler uiThreadHandler;
    private Timer timer;
    private TimerTask timerTask;

    private int timeRemaining = 30;
    // game
    private boolean gameOver = false;
    AliasTeam currentTeam = new AliasTeam("Одуванчики");
    private AliasDictionary dictionary;
    private AliasWord currentWord;
    private View buttonsHolder;
    private TextView gameOverView;
    private Button nextTeamButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gameView = inflater.inflate(R.layout.fragment_game, container, false);
        teamText = gameView.findViewById(R.id.text_team);
        nextTeamButton = (Button) gameView.findViewById(R.id.next_team);
        okButton = (Button) gameView.findViewById(R.id.word_ok);
        niokButton = (Button) gameView.findViewById(R.id.word_niok);
        wordView = (TextView) gameView.findViewById(R.id.word);
        timerView = (TextView) gameView.findViewById(R.id.timer);
        buttonsHolder = gameView.findViewById(R.id.buttons_holder);
        gameOverView = (TextView) gameView.findViewById(R.id.game_over);

        dictionary = activity.getDictionary();
        // todo вытаскивать из базы данных
        nextWord();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWord.setGuessed(true);
                nextWord();
            }
        });
        niokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWord.setGuessed(false);
                nextWord();
            }
        });

        startTimer();

        return gameView;
    }

    private void startTimer() {
        uiThreadHandler = new Handler();

        timer = new Timer();
        scheduleTask();

    }
    private void scheduleTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                uiThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(gameOver){
                            return;
                        }
                        timeRemaining--;
                        if(timeRemaining>0) {
                            scheduleTask();
                            timerView.setText("Осталось " + timeRemaining + " секунд");
                            if (timeRemaining<5) {
                                timerView.setTextColor(getResources().getColor(R.color.timer_red));
                            }
                        }else{
                            gameOver();
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask, 1000);
    }

    private void nextWord() {
        currentWord = dictionary.pullWord();
        if(currentWord==null){
            gameOver();
            return;
        }
        currentTeam.addWord(currentWord);
        wordView.setText(currentWord.body);
    }

    private void gameOver() {
        gameOver = true;
        timerView.setVisibility(View.GONE);
        buttonsHolder.setVisibility(View.GONE);
        nextTeamButton.setVisibility(View.VISIBLE);
        gameOverView.setVisibility(View.VISIBLE);
        if (timeRemaining==0) {
            gameOverView.setText("Время закончилось");
        }else{
            gameOverView.setText("Закончились слова");
        }
        nextTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.played();
            }
        });

        wordView.setVisibility(View.GONE);
        // todo animate game over

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (GameActivity) activity;
    }
}
