package me.jesuscodes.alias.models;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasTeam {

    private String name;
    public ArrayList<AliasWord> words= new ArrayList<>();

    public AliasTeam(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addWord(AliasWord word) {
        words.add(word);
    }
}
