package com.songmho.planning;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    int MAX_PAGE=3;
    Button indi_1;
    Button indi_2;
    Button indi_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle("Planning");
        ViewPager main_viewPager=(ViewPager)findViewById(R.id.viewpager);
        indi_1=(Button)findViewById(R.id.indicator1);
        indi_2=(Button)findViewById(R.id.indicator2);
        indi_3=(Button)findViewById(R.id.indicator3);
        indi_1.setSelected(true);
        indi_2.setSelected(false);
        indi_3.setSelected(false);
        main_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager()));
        main_viewPager.setOnPageChangeListener(new Viewpager_change());
    }

    private class Viewpager_Adapter extends FragmentPagerAdapter {
        public Viewpager_Adapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {return new ListFragment(position);}

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    private class Viewpager_change implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            switch (position){
                case 0:
                    indi_1.setSelected(true);
                    indi_2.setSelected(false);
                    indi_3.setSelected(false);
                    break;
                case 1:
                    indi_1.setSelected(false);
                    indi_2.setSelected(true);
                    indi_3.setSelected(false);
                    break;
                case 2:
                    indi_1.setSelected(false);
                    indi_2.setSelected(false);
                    indi_3.setSelected(true);
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
