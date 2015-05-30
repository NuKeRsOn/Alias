package me.jesuscodes.alias.start;

import android.os.Bundle;

import me.jesuscodes.alias.R;
import me.jesuscodes.alias.game.CreateTeamsFragment;
import me.jesuscodes.alias.util.base.BaseActivity;


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
}

