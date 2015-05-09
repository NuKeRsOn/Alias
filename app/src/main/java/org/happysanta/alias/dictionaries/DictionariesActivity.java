package org.happysanta.alias.dictionaries;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import org.happysanta.alias.R;

public class DictionariesActivity extends ActionBarActivity implements BillingProcessor.IBillingHandler {

    private static final String DICTIONARY_GEO = "geo";
    private static final String DICTIONARY_BASIC_1 = "b1";
    private static final String DICTIONARY_BASIC_2 = "b2";
    private static final String DICTIONARY_ALL = "all";
    private static final String DICTIONARY_SEX = "sex";
    private BillingProcessor billingProcessor;
    private DictionariesAdapter adapter = new DictionariesAdapter();
    private SharedPreferences prefs;

    @Override
    public void onDestroy() {
        if (billingProcessor != null)
            billingProcessor.release();

        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionaries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences("dictionaries", MODE_MULTI_PROCESS);


        ListView dictionariesList = (ListView) findViewById(R.id.dictionaries);
        dictionariesList.setAdapter(adapter);
        dictionariesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Uri uri = Uri.parse("market://details?id=" + getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                            } finally {

                            }
                        }
                        prefs.edit().putBoolean(DICTIONARY_SEX, true).apply();
                        adapter.notifyDataSetChanged();
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
        billingProcessor = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp898Rf984tibHTq6pbIbpTxlNt1UJPsJEbyQ2DWOQ9Om/zqEN0vVM40SMaHeY3SZM9Y7bHtA1tt+BIyxh1OKyhTKn0+PJvkji1QoaTSRPI6naTPV6Ou+Un24a1F22+Knbi1E/L4yR0OrNhX0mdMPH3KQcPnzdQJ4joEJGnk9VV0U2t1uNQcrLno0BEM4E133HpGjX2YzTSG98KkNenSFcbQR7oQxZSFVaMr/awE9bl6mHg1tfu0mNyxJoKHKGUaRpmuSCKANzNrs0OkzGeYPtqVqj9CYVaHIDjE8yH0WnsCgSs9ajFucZfF3eKN+1hZDdDT1qzN99sky9PSouiVtowIDAQAB", this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!billingProcessor.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }
    private void buy(String dictionaryCode) {
        billingProcessor.purchase(this, dictionaryCode);
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails transactionDetails) {
        prefs.edit().putBoolean(productId, true).apply();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable throwable) {

    }

    @Override
    public void onBillingInitialized() {

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
        public boolean isEnabled(int position) {
            if(position==0){
                return true;
            }
            return !dictionaryActivated(position) && billingProcessor.isInitialized();
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
            View addedView = dictionaryView.findViewById(R.id.added);
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
            if(dictionaryActivated(position)) {
                costView.setVisibility(View.INVISIBLE);
                addedView.setVisibility(View.VISIBLE);
            }

            return dictionaryView;
        }
    }

    private boolean dictionaryActivated(int position) {

        if (prefs.getBoolean("all",false)){
            return true;
        }

        String dictionaryCode = "";
        switch (position) {
            case 0:
                dictionaryCode = DICTIONARY_SEX;
                break;
            case 1:
                dictionaryCode = DICTIONARY_GEO;
                break;
            case 2:
                dictionaryCode = DICTIONARY_BASIC_1;
                break;
            case 3:
                dictionaryCode = DICTIONARY_BASIC_2;
                break;
            case 4:
                return prefs.getBoolean(DICTIONARY_BASIC_1, false)
                        && prefs.getBoolean(DICTIONARY_BASIC_2, false)
                        && prefs.getBoolean(DICTIONARY_GEO, false);
        }

        return prefs.getBoolean(dictionaryCode, false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
