package org.happysanta.alias.util.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by alex
 */
public class BaseFragment extends Fragment {

    protected Toolbar mToolbar;
    protected View mRoot;
    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;
        mToolbar = ((BaseActivity) activity).getToolbar();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRoot = view;
    }

    public View findViewById(int id) {

        return mRoot.findViewById(id);
    }

    protected void onBackPressed() {

        mActivity.onBackPressed();
    }
}
