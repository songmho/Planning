package com.songmho.planning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
* Created by songmho on 2015-01-14.
*/
public class Viewpager_Adapter extends FragmentPagerAdapter {
    int PAGE;
    public Viewpager_Adapter(FragmentManager supportFragmentManager, int MAX_PAGE) {
        super(supportFragmentManager);
        this.PAGE=MAX_PAGE;
    }

    @Override
    public Fragment getItem(int position) {return new ListFragmentActivity(position);}

    @Override
    public int getCount() {
        return PAGE;
    }
}
