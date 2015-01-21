package org.happysanta.alias.game;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasDictionary;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.models.AliasWord;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jesus Christ. Amen.
 */
public class  GameFragment extends Fragment {


    private ArrayList<AliasTeam> teams;
    private TextView wordView;
    private ImageButton okButton;
    private ImageButton niokButton;
    private TextView timerTextView;
    private ProgressBar timerProgressView;
    private View teamText;

    private GameActivity activity;
    // timer
    private Handler uiThreadHandler;
    private Timer timer;
    private TimerTask timerTask;
    private int roundTime = 60;
    private int timeRemaining = roundTime;
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
        okButton = (ImageButton) gameView.findViewById(R.id.word_ok);
        niokButton = (ImageButton) gameView.findViewById(R.id.word_niok);
        wordView = (TextView) gameView.findViewById(R.id.word);
        timerTextView = (TextView) gameView.findViewById(R.id.timer_text);
        timerProgressView = (ProgressBar) gameView.findViewById(R.id.timer_progress);
        buttonsHolder = gameView.findViewById(R.id.buttons_holder);
        gameOverView = (TextView) gameView.findViewById(R.id.game_over);

        // todo вытаскиваем время раунда из настроек
        if(roundTime==60) {
            timerTextView.setText("1:00");
        } else {
            timerTextView.setText("0:"+ roundTime);
        }
        currentTeam = activity.getCurrentTeam();
        dictionary = activity.getDictionary();
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
                            timerProgressView.setProgress((int) (100f /  roundTime * (roundTime - timeRemaining)));
                            if (timeRemaining > 9) {
                                timerTextView.setText("0:" + timeRemaining);
                            } else {
                                timerTextView.setText("0:0" + timeRemaining);
                            }

                        } else {
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
        timerProgressView.setVisibility(View.GONE);
        buttonsHolder.setVisibility(View.GONE);
        nextTeamButton.setVisibility(View.VISIBLE);
        gameOverView.setVisibility(View.VISIBLE);

        if (timeRemaining==0) {
            gameOverView.setText("Время закончилось");
        }else{
            gameOverView.setText("Закончились слова");
        }
        if (activity.getTeams().size() == activity.getCurrentTeamIndex()+1){
            nextTeamButton.setText("Показать результаты");
        }else{
            nextTeamButton.setText("Ход другой команды");
        }
        //todo Показать результате, когда заканчиваются слова
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
