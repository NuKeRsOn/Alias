package me.jesuscodes.alias.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.dictionaries.DictionariesActivity;
import me.jesuscodes.alias.game.adapter.TeamsAdapter;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.util.Toaster;
import me.jesuscodes.alias.util.base.BaseFragment;

/**
 * Created by Jesus Christ. Amen.
 */
public class CreateTeamsFragment extends BaseFragment {

    private GameActionsListener mGameActionsListener;

    private RecyclerView mTeamList;
    private TeamsAdapter mTeamsAdapter;
    private ArrayList<AliasTeam> mTeams;

    private Button mStartGame;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_create_teams, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTeamList = (RecyclerView) findViewById(R.id.create_teams_list);
        mTeamsAdapter = new TeamsAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        );

        mTeamList.setLayoutManager(layoutManager);
        mTeamList.setAdapter(mTeamsAdapter);

        mStartGame = (Button) findViewById(R.id.create_play);

        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTeams = mTeamsAdapter.getAliasTeams();

                if (mTeams.size() > 1) {

                    mGameActionsListener.onGameStart(mTeams);

                } else {

                    Toaster.say("Add more teams!");
                }
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mGameActionsListener = (GameActionsListener) activity;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_start, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_dictionaries) {
            startActivity(new Intent(getActivity(), DictionariesActivity.class));
            return true;
        }
        if (item.getItemId()== R.id.action_settings) {
            startActivity(new Intent(getActivity(), DictionariesActivity.class));//SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}