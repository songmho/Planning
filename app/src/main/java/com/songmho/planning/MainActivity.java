package com.songmho.planning;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {
    int MAX_PAGE=3;
    Fragment cur_fragment=new Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle("Planning");

          ViewPager main_viewPager=(ViewPager)findViewById(R.id.viewpager);
        main_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager()));
    }

    private class Viewpager_Adapter extends FragmentPagerAdapter {
        public Viewpager_Adapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new ListFragment(position);
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
