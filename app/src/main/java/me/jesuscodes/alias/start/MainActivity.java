package me.jesuscodes.alias.start;

import android.os.Bundle;

import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.dictionaries.Dictionaries;
import me.jesuscodes.alias.game.CreateTeamsFragment;
import me.jesuscodes.alias.game.GameActionsListener;
import me.jesuscodes.alias.game.GamePlayFragment;
import me.jesuscodes.alias.game.ResultGameFragment;
import me.jesuscodes.alias.game.ResultTeamFragment;
import me.jesuscodes.alias.models.AliasDictionary;
import me.jesuscodes.alias.models.AliasTeam;
import me.jesuscodes.alias.util.ColorUtil;
import me.jesuscodes.alias.util.base.BaseActivity;


// TODO needs some refactoring here
@SuppressWarnings("ConstantConditions")
public class MainActivity extends BaseActivity implements GameActionsListener {

    private static ArrayList<AliasTeam> sPlayingTeams = new ArrayList<>();

    // unsorted teams
    private static ArrayList<AliasTeam> sClearTeams = new ArrayList<>();
    private static AliasDictionary sDictionary = Dictionaries.getDictionary();
    private static int sCurrentIndex;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mToolbar.setBackgroundColor(ColorUtil.get(android.R.color.transparent));
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.container, new CreateTeamsFragment())
                .commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onStartGame(ArrayList<AliasTeam> teams) {

        sPlayingTeams = teams;
        sClearTeams = (ArrayList<AliasTeam>) teams.clone();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new GamePlayFragment())
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .commit();

        mToolbar.setBackgroundColor(ColorUtil.get(android.R.color.transparent));
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);
    }

    @Override
    public void onStartRound() {

        sCurrentIndex++;

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.container, new GamePlayFragment())
                .commit();

        mToolbar.setBackgroundColor(ColorUtil.get(android.R.color.transparent));
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);
    }

    @Override
    public void onFinishGame() {

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.container, new ResultGameFragment())
                .commit();

        mToolbar.setBackgroundColor(ColorUtil.get(R.color.color_primary));
        mToolbar.setTitle("Game results");

        setSupportActionBar(mToolbar);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onFinishRound(ArrayList<AliasTeam> teams) {

        sPlayingTeams = teams;

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.container, new ResultTeamFragment())
                .commit();

        mToolbar.setBackgroundColor(ColorUtil.get(R.color.color_primary));
        mToolbar.setTitle(
                String.format("Words guessed by %s", getCurrentTeam().getName())
        );

        setSupportActionBar(mToolbar);
    }

    @Override
    public void onRestartGame() {

        refresh();
        onStartGame(sClearTeams);
    }

    @Override
    public void onRecreateGame() {

        refresh();

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit,
                        R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.container, new CreateTeamsFragment())
                .commit();

        mToolbar.setBackgroundColor(ColorUtil.get(android.R.color.transparent));
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);
    }

    public static ArrayList<AliasTeam> getPlayingTeams() {
        return sPlayingTeams;
    }

    public static AliasTeam getCurrentTeam() {
        return sPlayingTeams.get(sCurrentIndex);
    }

    public static int getCurrentIndex() {
        return sCurrentIndex;
    }

    public static AliasDictionary getDictionary() {
        return sDictionary;
    }

    public static boolean isFinish() {

        return (sCurrentIndex + 1) == sPlayingTeams.size();
    }

    private static void refresh() {

        for (AliasTeam t : sClearTeams) t.clearWords();
        sCurrentIndex = 0;
    }
}

