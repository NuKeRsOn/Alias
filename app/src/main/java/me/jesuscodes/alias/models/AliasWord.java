package me.jesuscodes.alias.models;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasWord {
    public final String bodyRu;
    public final String bodyEn;
    private boolean guessed = false;

    public AliasWord(String bodyRu, String bodyEn) {
        this.bodyRu = bodyRu;
        this.bodyEn = bodyEn;
    }

    public boolean setGuessed(boolean guessed) {
        this.guessed = guessed;
        return guessed;
    }

    public boolean isGuessed() {
        return guessed;
    }
}
