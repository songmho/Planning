package com.songmho.planning;

import android.support.v4.view.ViewPager;
import android.widget.Button;

/**
 * Created by songmho on 2015-01-24.
 */
public class Viewpager_indicator implements ViewPager.OnPageChangeListener {
    Button todo;
    Button doing;
    Button done;
    public Viewpager_indicator(Button todo, Button doing, Button done) {
        this.todo=todo;
        this.doing=doing;
        this.done=done;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position){
            case 0:                         //To do Fragment
                todo.setSelected(true);
                doing.setSelected(false);
                done.setSelected(false);
                break;
            case 1:                         //Doing Fragment
                todo.setSelected(false);
                doing.setSelected(true);
                done.setSelected(false);
                break;
            case 2:                        //Done Fragment
                todo.setSelected(false);
                doing.setSelected(false);
                done.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
}
