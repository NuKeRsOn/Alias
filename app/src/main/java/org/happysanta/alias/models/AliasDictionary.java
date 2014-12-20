package org.happysanta.alias.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jesus Christ. Amen.
 */
public class AliasDictionary {
    public ArrayList<AliasWord> words = new ArrayList<AliasWord>();

    public AliasDictionary( ArrayList<AliasWord> words){
        this.words = words;
    }

    public AliasWord pullWord(){

        if(words.size()==0){
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());

        AliasWord word = words.get(randomIndex);
        words.remove(randomIndex);
        return word;

    }



}
