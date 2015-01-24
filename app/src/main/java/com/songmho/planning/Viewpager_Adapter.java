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
    String cur_bor;
    String main_title;
    public Viewpager_Adapter(FragmentManager supportFragmentManager, int MAX_PAGE, String cur_bor) {
        super(supportFragmentManager);
        this.PAGE=MAX_PAGE;
        this.cur_bor=cur_bor;
    }

    public Viewpager_Adapter(FragmentManager supportFragmentManager, int max_page, String sub, String main_title) {
        super(supportFragmentManager);
        this.PAGE=max_page;
        this.cur_bor=sub;
        this.main_title=main_title;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("max_page",position);
        bundle.putString("cur_bor",cur_bor);
        if(cur_bor.equals("sub"))
            bundle.putString("par_board",main_title);
        ListFragmentActivity listFragmentActivity=new ListFragmentActivity();
        listFragmentActivity.setArguments(bundle);
        return listFragmentActivity;}

    @Override
    public int getCount() {
        return PAGE;
    }
}
