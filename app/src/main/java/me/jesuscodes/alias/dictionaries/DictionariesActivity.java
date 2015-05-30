package me.jesuscodes.alias.dictionaries;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.util.base.BaseActivity;

public class DictionariesActivity extends BaseActivity implements BillingProcessor.IBillingHandler {

    private static final String DICTIONARY_GEO = "geo";
    private static final String DICTIONARY_BASIC_1 = "b1";
    private static final String DICTIONARY_BASIC_2 = "b2";
    private static final String DICTIONARY_FULL = "full";
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


        ListView dictionariesList = (ListView) findViewById(R.id.list);
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
                        buy(DICTIONARY_FULL);
                        break;
                    case 5:
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, ("http://jesuscodes.me/alias/"));
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
                        shareIntent.setType("text/plain");
                        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
                        break;
                }
            }
        });
        View aboutView = View.inflate(this, R.layout.about, null);
        View santa = aboutView.findViewById(R.id.santa);
        santa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/happysanta")));
            }
        });
        dictionariesList.addFooterView(aboutView, null, false);
        String billingKey = getString(R.string.billinLicenseKey);
        billingProcessor = new BillingProcessor(
                this,
                billingKey,
                this);

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
            return 6;
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
                    titleView.setText(R.string.dictionary_full);
                    costView.setText("$2.49");
                    countView.setText(getString(R.string.dictionary_count, 1700));
                    break;
                case 5:
                    titleView.setText(R.string.share);
                    costView.setText(R.string.free);
                    countView.setVisibility(View.GONE);
                    return dictionaryView;
            }
            if(dictionaryActivated(position)) {
                costView.setVisibility(View.INVISIBLE);
                addedView.setVisibility(View.VISIBLE);
            }

            return dictionaryView;
        }
    }

    private boolean dictionaryActivated(int position) {
        if(position>0)
            if (prefs.getBoolean("full",false)){
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
