package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songmho on 2015-01-21.
 */
public class DetailActivity extends ActionBarActivity {
    String main_title;
    ImageButton fix;
    String duedate_str;
    String duetime_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        main_title=intent.getStringExtra("title");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle(main_title);

        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        String classname=pref_login.getString("classname","");

        final TextView duedate=(TextView)findViewById(R.id.duedate);
        final TextView duetime=(TextView)findViewById(R.id.duetime);
        RadioButton radio_todo=(RadioButton)findViewById(R.id.radio_todo);
        RadioButton radio_doing=(RadioButton)findViewById(R.id.radio_doing);
        RadioButton radio_done=(RadioButton)findViewById(R.id.radio_done);
        fix=(ImageButton)findViewById(R.id.fix);
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFix();
            }
        });

        find_state(intent,radio_todo,radio_doing,radio_done);
        ParseQuery<ParseObject> query= new ParseQuery<>(classname);
        query.whereContains("title",intent.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));
                duetime.setText(object.getString("duetime"));
                duedate_str=object.getString("duedate");
                duetime_str=object.getString("duetime");
            }
        });

        radio_todo.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", classname));
        radio_doing.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", classname));
        radio_done.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", classname));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFix() {
        Intent intent=new Intent(DetailActivity.this, AddnFixActivity.class);
        intent.putExtra("button","fix");
        intent.putExtra("board","sub");
        intent.putExtra("main_title",main_title);
        intent.putExtra("date",duedate_str);
        intent.putExtra("time",duetime_str);
        startActivity(intent);
    }
}
