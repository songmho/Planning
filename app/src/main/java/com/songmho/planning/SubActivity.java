package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songmho on 2015-01-22.
 */
public class SubActivity extends ActionBarActivity {

    int MAX_PAGE=3;
    String main_title;
    ViewPager sub_viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent_get=getIntent();
        main_title=intent_get.getStringExtra("title");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle(main_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final TextView duedate=(TextView)findViewById(R.id.duedate);

        RadioButton radio_todo=(RadioButton)findViewById(R.id.radio_todo);
        RadioButton radio_doing=(RadioButton)findViewById(R.id.radio_doing);
        RadioButton radio_done=(RadioButton)findViewById(R.id.radio_done);

        Button todo=(Button)findViewById(R.id.todo);
        Button doing=(Button)findViewById(R.id.doing);
        Button done=(Button)findViewById(R.id.done);

        sub_viewPager=(ViewPager)findViewById(R.id.viewpager);

        find_state(intent_get, radio_todo, radio_doing, radio_done);
        sub_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE,"sub",main_title));
        sub_viewPager.setOnPageChangeListener(new Viewpager_indicator(todo,doing,done));

        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        ParseQuery<ParseObject> query= new ParseQuery<>(pref_login.getString("classname",""));
        query.whereContains("title",intent_get.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));
            }
        });

        radio_todo.setOnClickListener(new Radioclick(main_title,getApplicationContext(),"main",(pref_login.getString("classname",""))));
        radio_doing.setOnClickListener(new Radioclick(main_title,getApplicationContext(),"main", (pref_login.getString("classname", ""))));
        radio_done.setOnClickListener(new Radioclick(main_title,getApplicationContext(),"main", (pref_login.getString("classname", ""))));

        todo.setOnClickListener(new Button_click());
        doing.setOnClickListener(new Button_click());
        done.setOnClickListener(new Button_click());
    }

    private void find_state(Intent intent_get, RadioButton radio_todo, RadioButton radio_doing, RadioButton radio_done) {
        switch (intent_get.getIntExtra("state",0)){
            case 0:
                radio_todo.setChecked(true);
                break;
            case 1:
                radio_doing.setChecked(true);
                break;
            case 2:
                radio_done.setChecked(true);
                break;
        }
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
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAdd() {
        Bundle bundle=new Bundle();
        bundle.putString("board","sub");
        bundle.putString("main_title",main_title);
        AddActivity addActivity=new AddActivity();
        addActivity.setArguments(bundle);
        addActivity.show(getFragmentManager(),"tag");
    }

    private class Button_click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.todo:
                    sub_viewPager.setCurrentItem(0);
                    break;
                case R.id.doing:
                    sub_viewPager.setCurrentItem(1);
                    break;
                case R.id.done:
                    sub_viewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}
