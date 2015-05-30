package me.jesuscodes.alias.dictionaries;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.models.AliasDictionary;
import me.jesuscodes.alias.models.AliasWord;

import static me.jesuscodes.alias.AliasApplication.app;

/**
 * Created by Jesus Christ. Amen.
 */
public class Dictionaries {
    public static AliasDictionary getDictionary() {
        String dictionaryStringRu = "";
        String dictionaryStringEn = "";
        SharedPreferences prefs = app().getSharedPreferences("dictionaries", Context.MODE_MULTI_PROCESS);

        dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.default_en));
        dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.default_ru));

        if (prefs.getBoolean("full", false)) {
            dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.geo_en));
            dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.geo_ru));
            dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.basic_en));
            dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.basic_ru));
            dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.basic2_en));
            dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.basic2_ru));
        } else{
            if (prefs.getBoolean("geo", false)) {
                dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.geo_en));
                dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.geo_ru));
            }
            if (prefs.getBoolean("b1", false)) {
                dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.basic_en));
                dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.basic_ru));
            }
            if (prefs.getBoolean("b2", false)) {
                dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.basic2_en));
                dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.basic2_ru));
            }
        }
        if(prefs.getBoolean("sex", false)){
            dictionaryStringEn += getStringFromStream(app().getResources().openRawResource(R.raw.sex_en));
            dictionaryStringRu += getStringFromStream(app().getResources().openRawResource(R.raw.sex_ru));

        }



        String[] dictionaryArrayRu = dictionaryStringRu.split("\n");
        String[] dictionaryArrayEn = dictionaryStringEn.split("\n");

        ArrayList<AliasWord> words = new ArrayList<>();
        for (int i = 0; i < dictionaryArrayEn.length; i++) {
            String dictionaryWordRu = dictionaryArrayRu[i];
            String dictionaryWordEn = dictionaryArrayEn[i];
            words.add(new AliasWord(dictionaryWordRu, dictionaryWordEn));
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
