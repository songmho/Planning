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
    String classname;
    public Viewpager_Adapter(FragmentManager supportFragmentManager, int MAX_PAGE, String cur_bor,String classname) {
        super(supportFragmentManager);
        this.PAGE=MAX_PAGE;
        this.cur_bor=cur_bor;
        this.classname=classname;
    }

    public Viewpager_Adapter(FragmentManager supportFragmentManager, int max_page, String sub, String main_title,String classname) {
        super(supportFragmentManager);
        this.PAGE=max_page;
        this.cur_bor=sub;
        this.main_title=main_title;
        this.classname=classname;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("max_page",position);
        bundle.putString("cur_bor",cur_bor);
        bundle.putString("classname",classname);
        if(cur_bor.equals("sub"))
            bundle.putString("main_title",main_title);
        ListFragmentActivity listFragmentActivity=new ListFragmentActivity();
        listFragmentActivity.setArguments(bundle);
        return listFragmentActivity;}

    @Override
    public int getCount() {
        return PAGE;
    }
}
