package com.songmho.planning;

import android.os.Bundle;
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
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("max_page",position);
        ListFragmentActivity listFragmentActivity=new ListFragmentActivity();
        listFragmentActivity.setArguments(bundle);
        return listFragmentActivity;}

    @Override
    public int getCount() {
        return PAGE;
    }
}
