package me.jesuscodes.alias.game;

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

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.dictionaries.DictionariesActivity;
import me.jesuscodes.alias.game.adapter.TeamsAdapter;
import me.jesuscodes.alias.util.base.BaseFragment;

/**
 * Created by Jesus Christ. Amen.
 */
public class CreateTeamsFragment extends BaseFragment {

    private RecyclerView mTeamList;
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

        mStartGame = (Button) findViewById(R.id.create_play);
        mTeamList = (RecyclerView) findViewById(R.id.create_teams_list);

        TeamsAdapter mTeamsAdapter = new TeamsAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false
        );

        mTeamList.setLayoutManager(layoutManager);
        mTeamList.setAdapter(mTeamsAdapter);
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