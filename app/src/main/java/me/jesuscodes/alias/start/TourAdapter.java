package me.jesuscodes.alias.start;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jesus Christ. Amen.
 */
class TourAdapter extends FragmentStatePagerAdapter {
    public TourAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TourFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
