package me.jesuscodes.alias.models;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasTeam {

    private int mGuessedCount;
    private int mTeamColor;
    private String mTeamName;
    private ArrayList<AliasWord> mWords = new ArrayList<>();

    public AliasTeam(String name){
        this.mTeamName = name;
    }

    public void setName(String name) {
        this.mTeamName = name;
    }

    public String getName() {
        return mTeamName;
    }

    public int getTeamColor() {
        return mTeamColor;
    }

    public void setTeamColor(int teamColor) {
        mTeamColor = teamColor;
    }

    public void addWord(AliasWord word) {

        if (word.isGuessed()) mGuessedCount++;

        mWords.add(word);
    }

    public void clearWords() {

        mGuessedCount = 0;
        mWords.clear();
    }

    public ArrayList<AliasWord> getWords() {
        return mWords;
    }

    public int getGuessedCount() {
        return mGuessedCount;
    }
}
