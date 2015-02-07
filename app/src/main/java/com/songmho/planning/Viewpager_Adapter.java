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
        if(cur_bor.equals("main")){
        Bundle bundle=new Bundle();
        bundle.putString("main_title",main_title);
        bundle.putInt("max_page",position);
        bundle.putString("cur_bor",cur_bor);
        bundle.putString("classname",classname);
        ListFragmentActivity listFragmentActivity=new ListFragmentActivity();
        listFragmentActivity.setArguments(bundle);
        return listFragmentActivity;
        }
        else if(cur_bor.equals("sub")){
            Bundle bundle=new Bundle();
            if(position==0){
                bundle.putString("title",main_title);
                SubFstFragmentAcitvity detailFragmentActivity =new SubFstFragmentAcitvity();
                detailFragmentActivity.setArguments(bundle);
                return detailFragmentActivity;
            }
            else{
                bundle.putInt("max_page",position-1);
                bundle.putString("cur_bor", cur_bor);
                bundle.putString("classname",classname);
                bundle.putString("main_title",main_title);
                ListFragmentActivity listFragmentActivity=new ListFragmentActivity();
                listFragmentActivity.setArguments(bundle);
                return listFragmentActivity;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE;
    }
}
