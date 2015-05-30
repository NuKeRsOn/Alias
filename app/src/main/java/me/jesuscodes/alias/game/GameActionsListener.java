package me.jesuscodes.alias.game;

import java.util.ArrayList;

import me.jesuscodes.alias.models.AliasTeam;

/**
 * Created by alex
 */
public interface GameActionsListener {

    void onGameStart(ArrayList<AliasTeam> teams);
    void onGameEnd(ArrayList<AliasTeam> teams);
}
