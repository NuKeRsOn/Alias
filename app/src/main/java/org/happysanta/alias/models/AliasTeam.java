package org.happysanta.alias.models;

import java.util.ArrayList;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasTeam {

    public String name;
    public ArrayList<AliasWord> words= new ArrayList<AliasWord>();

    public AliasTeam(String name){
        this.name = name;
    }

    public void addWord(AliasWord word) {
        words.add(word);
    }
}
