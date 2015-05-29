package org.happysanta.alias.game;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.happysanta.alias.R;
import org.happysanta.alias.game.adapter.TeamsAdapter;
import org.happysanta.alias.util.base.BaseFragment;

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

}
