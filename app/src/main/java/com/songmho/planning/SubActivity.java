package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by songmho on 2015-01-30.
 */
public class SubActivity extends ActionBarActivity {
    int MAX_PAGE=4;

    String main_title;
    String classname;

    ViewPager sub_viewPager;
    Button info;
    Button bt_todo;
    Button bt_doing;
    Button bt_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent_get=getIntent();
        main_title=intent_get.getStringExtra("title");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle(main_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        classname=pref_login.getString("classname","");

        info=(Button)findViewById(R.id.info);
        bt_todo=(Button)findViewById(R.id.todo);
        bt_doing=(Button)findViewById(R.id.doing);
        bt_done=(Button)findViewById(R.id.done);

        sub_viewPager=(ViewPager)findViewById(R.id.viewpager);
        sub_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE,"sub",main_title,classname));
        sub_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        info.setSelected(true);
                        bt_todo.setSelected(false);
                        bt_doing.setSelected(false);
                        bt_done.setSelected(false);
                        break;
                    case 1:
                        info.setSelected(false);
                        bt_todo.setSelected(true);
                        bt_doing.setSelected(false);
                        bt_done.setSelected(false);
                        break;
                    case 2:
                        info.setSelected(false);
                        bt_todo.setSelected(false);
                        bt_doing.setSelected(true);
                        bt_done.setSelected(false);
                        break;
                    case 3:
                        info.setSelected(false);
                        bt_todo.setSelected(false);
                        bt_doing.setSelected(false);
                        bt_done.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        info.setOnClickListener(new ButtonClick());
        bt_todo.setOnClickListener(new ButtonClick());
        bt_doing.setOnClickListener(new ButtonClick());
        bt_done.setOnClickListener(new ButtonClick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.info:
                    sub_viewPager.setCurrentItem(0);
                    break;
                case R.id.todo:
                    sub_viewPager.setCurrentItem(1);
                    break;
                case R.id.doing:
                    sub_viewPager.setCurrentItem(2);
                    break;
                case R.id.done:
                    sub_viewPager.setCurrentItem(3);
                    break;
            }
        }
    }
}
