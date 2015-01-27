package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        main_title=intent.getStringExtra("title");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        getSupportActionBar().setTitle(main_title);

        final TextView duedate=(TextView)findViewById(R.id.duedate);
        RadioButton radio_todo=(RadioButton)findViewById(R.id.radio_todo);
        RadioButton radio_doing=(RadioButton)findViewById(R.id.radio_doing);
        RadioButton radio_done=(RadioButton)findViewById(R.id.radio_done);

        find_state(intent,radio_todo,radio_doing,radio_done);
        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        ParseQuery<ParseObject> query= new ParseQuery<>(pref_login.getString("classname",""));
        query.whereContains("title",intent.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));
            }
        });

        radio_todo.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", (pref_login.getString("classname", ""))));
        radio_doing.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", (pref_login.getString("classname", ""))));
        radio_done.setOnClickListener(new Radioclick(main_title, getApplicationContext(), "sub", (pref_login.getString("classname", ""))));
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
}
