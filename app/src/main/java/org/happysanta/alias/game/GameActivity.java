package org.happysanta.alias.game;

import android.app.Activity;
import android.os.Bundle;

import org.happysanta.alias.R;
import org.happysanta.alias.models.AliasDictionary;
import org.happysanta.alias.models.AliasTeam;
import org.happysanta.alias.models.AliasWord;

import java.util.ArrayList;


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
        dictionary = new AliasDictionary(new ArrayList<AliasWord>(){{
            add(new AliasWord("Вася"));
            add(new AliasWord("Петя"));
            add(new AliasWord("Маша"));
            add(new AliasWord("Козя"));
            add(new AliasWord("Ника"));
            add(new AliasWord("Дура"));
            add(new AliasWord("Котя"));
        }});

    }


    public void teamsCreated(ArrayList<AliasTeam> teams) {
        this.teams = teams;
        currentTeam = teams.get(0);
        currentTeamIndex = 0;
        playRound();
    }

    private void playRound() {
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
}