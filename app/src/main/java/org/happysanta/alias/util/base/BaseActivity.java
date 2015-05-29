package org.happysanta.alias.util.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.happysanta.alias.R;
import org.happysanta.alias.util.Dimen;

/**
 * Created by alex
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {

            setSupportActionBar(mToolbar);

            int paddingTop = Dimen.getStatusBarHeight();

//            mToolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
            mToolbar.setPadding(0, paddingTop, 0, 0);
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
