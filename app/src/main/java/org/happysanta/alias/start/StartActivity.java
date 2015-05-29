package org.happysanta.alias.start;

import android.os.Bundle;
import android.view.Menu;

import org.happysanta.alias.R;
import org.happysanta.alias.game.CreateTeamsFragment;
import org.happysanta.alias.util.base.BaseActivity;


public class StartActivity extends BaseActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (mToolbar != null) getSupportActionBar().setTitle("");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CreateTeamsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }
}

