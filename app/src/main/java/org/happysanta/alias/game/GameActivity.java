package org.happysanta.alias.game;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Message;

import org.happysanta.alias.R;
import org.happysanta.alias.dictionaries.Dictionaries;
import org.happysanta.alias.models.AliasDictionary;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.models.AliasWord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class GameActivity extends Activity {

    private ArrayList<AliasTeam> teams;
    private AliasTeam currentTeam;
    private int currentTeamIndex;
    private AliasDictionary dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CreateTeamsFragment())
                    .commit();
        }
        dictionary = Dictionaries.getAll(this);
    }


    public void teamsCreated(ArrayList<AliasTeam> teams) {
        this.teams = teams;
        currentTeam = teams.get(0);
        currentTeamIndex = 0;
        playRound();
    }

    public void playRound() {
        PrepareFragment fragment = new PrepareFragment();
        Bundle args = new Bundle();
        args.putString("team_name", currentTeam.name);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                    .commit();
    }

    public void ready() {
        // какая команда играет, какой раунд
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new GameFragment())
                .commit();

    }

    public void played() {
        currentTeamIndex++;
        if(currentTeamIndex==teams.size()){
            currentTeamIndex = 0;
            currentTeam = teams.get(0);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new RoundResultFragment())
                    .commit();
        } else {
            this.currentTeam = teams.get(currentTeamIndex);
            playRound();
        }
    }

    public void roundResultViewed() {
        finish();
    }


    public AliasDictionary getDictionary() {
       return dictionary;
    }

    public ArrayList<AliasTeam> getTeams() {
        return teams;
    }

    public AliasTeam getCurrentTeam() {
        return currentTeam;
    }
}
