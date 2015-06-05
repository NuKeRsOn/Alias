package me.jesuscodes.alias.game;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.adapter.ResultTeamAdapter;
import me.jesuscodes.alias.models.AliasWord;
import me.jesuscodes.alias.util.base.BaseFragment;

import static me.jesuscodes.alias.start.MainActivity.getCurrentTeam;
import static me.jesuscodes.alias.start.MainActivity.isFinish;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultTeamFragment extends BaseFragment {

    private GameActionsListener mGameActionsListener;

    private ArrayList<AliasWord> mWords;
    private RecyclerView mWordsList;
    private Button mNextTeam;

    public ResultTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_team, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWords = getCurrentTeam().getWords();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(

                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        );

        ResultTeamAdapter adapter = new ResultTeamAdapter(mWords);

        mWordsList = (RecyclerView) findViewById(R.id.team_res_team_list);
        mWordsList.setLayoutManager(linearLayoutManager);
        mWordsList.setAdapter(adapter);

        mNextTeam = (Button) findViewById(R.id.team_res_next);
        mNextTeam.setText(isFinish() ? "Show results" : "Start new round!");

        mNextTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFinish()) mGameActionsListener.onFinishGame();
                else            mGameActionsListener.onStartRound();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mGameActionsListener = (GameActionsListener) activity;
    }
}
