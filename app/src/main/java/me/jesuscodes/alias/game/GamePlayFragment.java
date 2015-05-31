package me.jesuscodes.alias.game;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasDictionary;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.models.AliasWord;
import me.jesuscodes.alias.util.base.BaseFragment;

import static me.jesuscodes.alias.start.MainActivity.getCurrentIndex;
import static me.jesuscodes.alias.start.MainActivity.getCurrentTeam;
import static me.jesuscodes.alias.start.MainActivity.getDictionary;
import static me.jesuscodes.alias.start.MainActivity.getPlayingTeams;

public class GamePlayFragment extends BaseFragment {

    private GameActionsListener mGameActionsListener;

    //region Game process data

    private ArrayList<AliasTeam> mResultingTeams = new ArrayList<>();

    private AliasTeam mCurrentTeam;
    private AliasDictionary mDictionary;
    private AliasWord mCurrentWord;
    //endregion

    //region Countdown views
    private RelativeLayout mCountDown;
    private TextView mTeamName;
    private TextView mSecondsToStart;
    private Button mReadyButton;
    //endregion

    //region Game process views
    private RelativeLayout mGameProcess;

    // timer
    private ProgressBar mTimeLeftProgress;
    private TextView mTimeLeft;

    // words
    private TextView mEngWord;
    private TextView mRusWord;

    // buttons
    private ImageView mCorrect;
    private ImageView mWrong;
    //endregion

    //region Timer
    private int mCD;
    private Handler mHandler;
    private Runnable mPrepareTimerRunnable;
    private Runnable mGameplayTimerRunnable;
    //endregion

    public GamePlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gameplay, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHandler = new Handler();
        mResultingTeams = getPlayingTeams();
        mCurrentTeam = getCurrentTeam();
        mDictionary = getDictionary();

        initGameProcess();
        initCountDown();
        initTimers();

        setPrepare();
    }

    private void initCountDown() {

        mCountDown = (RelativeLayout) findViewById(R.id.game_countdown);

        mTeamName = (TextView) findViewById(R.id.cd_team_name);
        mSecondsToStart = (TextView) findViewById(R.id.cd_time);
        mReadyButton = (Button) findViewById(R.id.cd_ready_button);

        mReadyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPrepareTimer();
            }
        });
    }

    private void initGameProcess() {

        mGameProcess = (RelativeLayout) findViewById(R.id.game_process);

        mTimeLeftProgress = (ProgressBar) findViewById(R.id.prc_timer_progress);
        mTimeLeft = (TextView) findViewById(R.id.prc_timer_text);

        mEngWord = (TextView) findViewById(R.id.prc_word_english);
        mRusWord = (TextView) findViewById(R.id.prc_word_russian);

        mCorrect = (ImageView) findViewById(R.id.prc_correct);
        mCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentWord.setGuessed(true);
                mCurrentTeam.addWord(mCurrentWord);
                nextWord();
            }
        });

        mWrong = (ImageView) findViewById(R.id.prc_wrong);
        mWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentWord.setGuessed(false);
                mCurrentTeam.addWord(mCurrentWord);
                nextWord();
            }
        });
    }

    private void initTimers() {

        mPrepareTimerRunnable = new Runnable() {

            @Override
            public void run() {

                mCD--;
                mSecondsToStart.setText(getString(R.string.prepare_time, mCD));

                if (mCD == 0) startRound();
            }
        };

        mGameplayTimerRunnable = new Runnable() {

            @Override
            public void run() {

                mCD--;

                Log.d("GamePlayFragment", "run - mCD: "
                        + mCD);

                mTimeLeftProgress.setProgress(60 - mCD);
                mTimeLeft.setText(String.format("0:%02d", mCD));

                if (mCD == 0) finishRound();
            }
        };
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mGameActionsListener = (GameActionsListener) activity;
    }

    @Override
    protected void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacks(mPrepareTimerRunnable);
        mHandler.removeCallbacks(mGameplayTimerRunnable);
    }

    private void setPrepare() {

        mTeamName.setText(getString(R.string.player_turn, mCurrentTeam.getName()));

        mGameProcess.setVisibility(View.GONE);
        mCountDown.setVisibility(View.VISIBLE);

        mReadyButton.setVisibility(View.VISIBLE);
        mSecondsToStart.setVisibility(View.INVISIBLE);

    }

    private void startRound() {

        mTimeLeft.setText("1:00");
        mTimeLeftProgress.setProgress(0);

        mCountDown.setVisibility(View.GONE);
        mGameProcess.setVisibility(View.VISIBLE);

        nextWord();

        setGameplayTimer();
    }

    private void finishRound() {

        mResultingTeams.set(getCurrentIndex(), mCurrentTeam);
        mGameActionsListener.onFinishRound(mResultingTeams);
    }

    private void nextWord() {

        mCurrentWord = mDictionary.pullWord();

        mEngWord.setText(mCurrentWord.bodyEn);
        mRusWord.setText(mCurrentWord.bodyRu);
    }

    private void setPrepareTimer() {

        mCD = 5;
        mSecondsToStart.setText(getString(R.string.prepare_time, mCD));

        mReadyButton.setVisibility(View.INVISIBLE);
        mSecondsToStart.setVisibility(View.VISIBLE);

        for (int i = 1; i <= 5; i++) {

            mHandler.postDelayed(mPrepareTimerRunnable, i * 1000);
        }
    }

    private void setGameplayTimer() {

        mCD = 60;

        for (int i = 1; i <= 60; i++) {

            mHandler.postDelayed(mGameplayTimerRunnable, i * 1000);
        }
    }
}
