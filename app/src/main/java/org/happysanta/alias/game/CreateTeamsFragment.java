package org.happysanta.alias.game;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasTeam;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class CreateTeamsFragment extends Fragment {

    private CreateTeamsAdapter adapter;
    private ArrayList<AliasTeam> teams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_teams, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        adapter= new CreateTeamsAdapter(getActivity());
        listView.setAdapter(adapter);
        Button playButton = (Button) rootView.findViewById(R.id.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teams = adapter.getTeams();
                if(teams.isEmpty()){
                    Toast.makeText(getActivity(), R.string.teams_empty, Toast.LENGTH_SHORT).show();
                }else {
                    play();
                }
            }
        });
        return rootView;
    }


    private void play(){
        GameActivity gameActivity = (GameActivity) getActivity();
        gameActivity.teamsCreated(teams);
    }
}
