package me.jesuscodes.alias.models;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasWord {
    public final String body;
    private boolean guessed = false;

    public AliasWord(String body) {
        this.body = body;
    }

    public boolean setGuessed(boolean guessed) {
        this.guessed = guessed;
        return guessed;
    }

    public boolean isGuessed() {
        return guessed;
    }
}
