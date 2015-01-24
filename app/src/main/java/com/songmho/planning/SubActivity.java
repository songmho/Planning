package com.songmho.planning;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

        ViewPager sub_viewPager=(ViewPager)findViewById(R.id.viewpager);

        find_state(intent_get, radio_todo, radio_doing, radio_done);
        sub_viewPager.setAdapter(new Viewpager_Adapter(getSupportFragmentManager(),MAX_PAGE,"sub",main_title));
        sub_viewPager.setOnPageChangeListener(new Viewpager_change(todo,doing,done));

        ParseQuery<ParseObject> query= new ParseQuery<>("Test");
        query.whereContains("title",intent_get.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));

            }
        });

        radio_todo.setOnClickListener(new radioclick());
        radio_doing.setOnClickListener(new radioclick());
        radio_done.setOnClickListener(new radioclick());
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
        Intent goAddAct=new Intent(SubActivity.this,AddActivity.class);
        goAddAct.putExtra("cur_board", "sub");
        goAddAct.putExtra("title",main_title);
        startActivity(goAddAct);
    }

    private class radioclick implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            ParseQuery<ParseObject> query=new ParseQuery<>("Test");
            query.whereContains("title",main_title);
            query.whereContains("board","main");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> parseObjects, ParseException e) {
                    ParseObject object=parseObjects.get(0);
                    Log.d("fdfdfdf",object.getString("title"));
                    object.remove("state");
                    switch (v.getId()){
                        case R.id.radio_todo:
                            object.put("state", "todo");
                            break;

                        case R.id.radio_doing:
                            object.put("state", "doing");
                            break;

                        case R.id.radio_done:
                            object.put("state", "done");
                            break;
                    }
                    object.saveInBackground();
                    Log.d("ddfdf",object.getString("state"));
                    Toast.makeText(getApplicationContext(),"현재 상태 : "+object.getString("state"),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
