package me.jesuscodes.alias.game;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.adapter.ResultGameAdapter;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.util.base.BaseFragment;

import static me.jesuscodes.alias.start.MainActivity.getPlayingTeams;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultGameFragment extends BaseFragment {

    private ResultGameAdapter mResultGameAdapter;
    private ArrayList<AliasTeam> mResultTeams;
    private RecyclerView mResultRoot;

    public ResultGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(

                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        );

        mResultTeams = getPlayingTeams();

        Collections.sort(mResultTeams, new Comparator<AliasTeam>() {
            @Override
            public int compare(AliasTeam lhs, AliasTeam rhs) {
                return rhs.getGuessedCount() - lhs.getGuessedCount();
            }
        });

        mResultGameAdapter = new ResultGameAdapter(mResultTeams);
        mResultGameAdapter.setRootActivity(getActivity());

        mResultRoot = (RecyclerView) findViewById(R.id.result_content);
        mResultRoot.setLayoutManager(linearLayoutManager);
        mResultRoot.setAdapter(mResultGameAdapter);

    }
}
