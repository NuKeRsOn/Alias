package org.happysanta.alias.game;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.happysanta.alias.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jesus Christ. Amen.
 */
public class PrepareFragment extends Fragment {

    private TimerTask timerTask;
    private Timer timer;
    private Handler uiThreadHandler;
    private int timeRemaining = 5;
    private TextView timeRemainingTextView;
    private TextView teamView;
    private String teamName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prepare, container, false);

        teamView = (TextView) rootView.findViewById(R.id.team);
        timeRemainingTextView = (TextView) rootView.findViewById(R.id.time_remaining);

        uiThreadHandler = new Handler();

        timer = new Timer();
        timeRemainingTextView.setText(getString(R.string.start_in, timeRemaining));
        scheduleTask();

        Bundle args = getArguments();
        teamName = args.getString("team_name");

        teamView.setText(getString(R.string.teams_turn, teamName));
        return rootView;
    }

    private void scheduleTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                uiThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        timeRemaining--;
                        if(timeRemaining>0) {
                            scheduleTask();
                            timeRemainingTextView.setText(getString(R.string.start_in, timeRemaining));
                        }else{
                            ready();
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask, 1000);
    }

    private void ready(){

        GameActivity gameActivity = (GameActivity) getActivity();

        gameActivity.ready();

    }
}
