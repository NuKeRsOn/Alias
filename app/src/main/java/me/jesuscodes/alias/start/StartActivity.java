package me.jesuscodes.alias.start;

import android.os.Bundle;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.CreateTeamsFragment;
import me.jesuscodes.alias.game.GameActionsListener;
import me.jesuscodes.alias.game.GamePlayFragment;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.util.base.BaseActivity;


public class StartActivity extends BaseActivity implements GameActionsListener {

    private static ArrayList<AliasTeam> sTeams = new ArrayList<>();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (mToolbar != null) getSupportActionBar().setTitle("");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CreateTeamsFragment()).commit();
    }

    @Override
    public void onGameStart(ArrayList<AliasTeam> teams) {

        sTeams = teams;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new GamePlayFragment()).commit();
    }

    @Override
    public void onGameEnd(ArrayList<AliasTeam> teams) {

        sTeams = teams;

        // TODO implement result fragment
    }

    public static ArrayList<AliasTeam> getTeams() {
        return sTeams;
    }
}

