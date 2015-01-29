package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle("Planning");

        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        String classname=pref_login.getString("classname","");

        int MAX_PAGE=3;             //viewpager 총 페이지 갯수
        context=getApplicationContext();
        main_viewPager=(ViewPager)findViewById(R.id.viewpager);
        todo=(Button)findViewById(R.id.todo);
        doing=(Button)findViewById(R.id.doing);
        done=(Button)findViewById(R.id.done);

        todo.setOnClickListener(new Button_click());
        doing.setOnClickListener(new Button_click());
        done.setOnClickListener(new Button_click());

        main_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE,"main",classname));     //Adapter연결
        main_viewPager.setOnPageChangeListener(new Viewpager_indicator(todo,doing,done));
    }


    private void openAdd() {                    //add액티비티 연결
        Intent intent=new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("board","main");
        startActivity(intent);
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
