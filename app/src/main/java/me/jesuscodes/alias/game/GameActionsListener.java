package me.jesuscodes.alias.game;

import java.util.ArrayList;

import me.jesuscodes.alias.models.AliasTeam;

/**
 * Created by alex
 */
public interface GameActionsListener {

    void onStartGame(ArrayList<AliasTeam> teams);
    void onFinishGame();
    void onFinishRound(ArrayList<AliasTeam> teams);
    void onStartRound();
    void onRestartGame();
    void onRecreateGame();
}
