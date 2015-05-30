package me.jesuscodes.alias.game.deprecated;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasDictionary;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.models.AliasWord;
import me.jesuscodes.alias.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jesus Christ. Amen.
 */
public class GameFragment extends BaseFragment {


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
    private int roundTime = 30;
    private int timeRemaining = 0;
    // game
    private boolean gameOver = false;
    AliasTeam currentTeam = new AliasTeam("Одуванчики");
    private AliasDictionary dictionary;
    private AliasWord currentWord;
    private View buttonsHolder;
    private TextView roundOverView;
    private Button nextTeamButton;
    private ListView wordsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        teamText = rootView.findViewById(R.id.text_team);
        nextTeamButton = (Button) rootView.findViewById(R.id.round_status_button);
        okButton = (ImageButton) rootView.findViewById(R.id.word_ok);
        niokButton = (ImageButton) rootView.findViewById(R.id.word_niok);
        wordView = (TextView) rootView.findViewById(R.id.word);
        timerTextView = (TextView) rootView.findViewById(R.id.timer_text);
        timerProgressView = (ProgressBar) rootView.findViewById(R.id.timer_progress);
        buttonsHolder = rootView.findViewById(R.id.buttons_holder);
        roundOverView = (TextView) rootView.findViewById(R.id.round_status);
        wordsList = (ListView) rootView.findViewById(R.id.list);

        roundTime = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("pref_time", "30"));
        timeRemaining = roundTime;
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

        return rootView;
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
        timerProgressView.setVisibility(View.GONE);
        timerTextView.setVisibility(View.GONE);
        buttonsHolder.setVisibility(View.GONE);
        wordView.setVisibility(View.GONE);
        nextTeamButton.setVisibility(View.VISIBLE);
        roundOverView.setVisibility(View.VISIBLE);

        // wordsList.setAdapter(new WordsAdapter(currentTeam));


        if (activity.getTeams().size() == activity.getCurrentTeamIndex()+1){
            nextTeamButton.setText(R.string.show_result);
            roundOverView.setVisibility(View.GONE);
        }else{
            nextTeamButton.setText(R.string.play_round);
//            roundOverView.setText(getString(R.string.teams_turn, activity.getNextTeam().name));
        }
        nextTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.played();

            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (GameActivity) activity;
    }

    private class WordsAdapter extends BaseAdapter {
        private final AliasTeam team;

        public WordsAdapter(AliasTeam currentTeam) {
            this.team = currentTeam;
        }

        @Override
        public int getCount() {
            return team.words.size();
        }

        @Override
        public AliasWord getItem(int position) {
            return team.words.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = activity.getLayoutInflater().inflate(R.layout.item_word, parent, false);
            TextView wordView = (TextView) itemView.findViewById(R.id.word);
            ImageView statusView = (ImageView) itemView.findViewById(R.id.image);
            AliasWord word = getItem(position);
            wordView.setText(word.body);

            if(word.isGuessed()){
                statusView.setBackgroundResource(R.drawable.word_guessed_background);
                statusView.setImageResource(R.drawable.v_white);
            } else {
                statusView.setBackgroundResource(R.drawable.word_notguessed_background);
                statusView.setImageResource(R.drawable.x_white);
            }

            return itemView;
        }
    }
}
