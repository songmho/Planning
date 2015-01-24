package com.songmho.planning;

import android.content.Intent;
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
        RadioButton todo=(RadioButton)findViewById(R.id.todo);
        RadioButton doing=(RadioButton)findViewById(R.id.doing);
        RadioButton done=(RadioButton)findViewById(R.id.done);

        find_state(intent,todo,doing,done);
        ParseQuery<ParseObject> query= new ParseQuery<>("Test");
        query.whereContains("title",intent.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));

            }
        });
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
