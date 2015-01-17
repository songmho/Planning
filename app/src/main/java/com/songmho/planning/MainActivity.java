package com.songmho.planning;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    Button todo;
    Button doing;
    Button done;
    ViewPager main_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle("Planning");

        int MAX_PAGE=3;             //viewpager 총 페이지 갯수

        main_viewPager=(ViewPager)findViewById(R.id.viewpager);
        todo=(Button)findViewById(R.id.todo);
        doing=(Button)findViewById(R.id.doing);
        done=(Button)findViewById(R.id.done);

        todo.setOnClickListener(new Button_click());
        doing.setOnClickListener(new Button_click());
        done.setOnClickListener(new Button_click());

        main_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE));     //Adapter연결
        main_viewPager.setOnPageChangeListener(new Viewpager_change());
    }


    private void openAdd() {                    //add액티비티 연결
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }

    private class Viewpager_change implements ViewPager.OnPageChangeListener {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                openAdd();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class Button_click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.todo:
                    main_viewPager.setCurrentItem(0);
                    break;
                case R.id.doing:
                    main_viewPager.setCurrentItem(1);
                    break;
                case R.id.done:
                    main_viewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}
