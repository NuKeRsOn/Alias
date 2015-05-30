package me.jesuscodes.alias.dictionaries;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import me.jesuscodes.alias.models.AliasDictionary;
import me.jesuscodes.alias.models.AliasWord;

/**
 * Created by Jesus Christ. Amen.
 */
public class Dictionaries {
    public static AliasDictionary getAll(Context context, int rawId) {

        InputStream inputStream = context.getResources().openRawResource(rawId);

        String dictionaryString = getStringFromStream(inputStream);
        String[] dictionaryArray = dictionaryString.split("\n");

        ArrayList<AliasWord> words = new ArrayList<>();

        for (String dictionaryWord : dictionaryArray){
            words.add(new AliasWord(dictionaryWord));
        }

        return new AliasDictionary(words);
    }
    private static String getStringFromStream(InputStream inputStream){
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}
