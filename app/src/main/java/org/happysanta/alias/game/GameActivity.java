package org.happysanta.alias.game;

import android.app.Activity;
import android.os.Bundle;

import org.happysanta.alias.R;
import org.happysanta.alias.dictionaries.Dictionaries;
import org.happysanta.alias.models.AliasDictionary;
import org.happysanta.alias.models.AliasTeam;

import java.util.ArrayList;
import java.util.Locale;


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

        // переключатель в настройках
        if (Locale.getDefault().getISO3Language().equals("ru")) {
            dictionary = Dictionaries.getAll(this, R.raw.words_ru);
        } else {
            dictionary = Dictionaries.getAll(this, R.raw.words_en);
        }
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
        //todo Слова при новой загрузке
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new GameFragment())
                .commit();

    }

    public boolean played() {
        currentTeamIndex++;
        if (currentTeamIndex == teams.size()) {
            currentTeamIndex = 0;
            currentTeam = teams.get(0);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new RoundResultFragment())
                    .commit();

        } else {
            this.currentTeam = teams.get(currentTeamIndex);
            playRound();
        }
        return false;
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

    public int getCurrentTeamIndex() {
        return currentTeamIndex;
    }
}
