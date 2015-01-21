package org.happysanta.alias.dictionaries;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.happysanta.alias.R;

public class DictionariesActivity extends ActionBarActivity {

    private static final int DICTIONARY_GEO = 1;
    private static final int DICTIONARY_BASIC_1 = 2;
    private static final int DICTIONARY_BASIC_2 = 3;
    private static final int DICTIONARY_ALL = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionaries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);


        ListView dictionariesList = (ListView) findViewById(R.id.dictionaries);
        dictionariesList.setAdapter(new DictionariesAdapter());
        dictionariesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        // todo rate the app
                        // buy(DICTIONARY_SEX);
                        break;
                    case 1:
                        buy(DICTIONARY_GEO);
                        break;
                    case 2:
                        buy(DICTIONARY_BASIC_1);
                        break;
                    case 3:
                        buy(DICTIONARY_BASIC_2);
                        break;
                    case 4:
                        buy(DICTIONARY_ALL);
                        break;
                }
            }
        });
    }

    private void buy(int dictionaryCode) {

    }


    private class DictionariesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View dictionaryView = getLayoutInflater().inflate(R.layout.item_dictionary, null);

            TextView titleView = (TextView) dictionaryView.findViewById(R.id.title);
            TextView costView = (TextView) dictionaryView.findViewById(R.id.cost);
            TextView countView = (TextView) dictionaryView.findViewById(R.id.count);
            // todo проверка на наличие
            switch (position) {
                case 0:
                    titleView.setText(R.string.dictionary_sex);
                    costView.setText(R.string.dictionary_rate);
                    countView.setText(getString(R.string.dictionary_count, 100));
                    break;
                case 1:
                    titleView.setText(R.string.dictionary_geo);
                    costView.setText("$1.00");
                    countView.setText(getString(R.string.dictionary_count, 500));
                    break;
                case 2:
                    titleView.setText(R.string.dictionary_basic_1);
                    costView.setText("$1.00");
                    countView.setText(getString(R.string.dictionary_count, 600));
                    break;
                case 3:
                    titleView.setText(R.string.dictionary_basic_2);
                    costView.setText("$1.00");
                    countView.setText(getString(R.string.dictionary_count, 600));
                    break;
                case 4:
                    titleView.setText(R.string.dictionary_all);
                    costView.setText("$2.49");
                    countView.setText(getString(R.string.dictionary_count, 1700));
                    break;
            }

            return dictionaryView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
