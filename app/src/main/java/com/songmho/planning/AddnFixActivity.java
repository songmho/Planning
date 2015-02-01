package com.songmho.planning;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.List;

/**
 * Created by songmho on 2015-01-29.
 */
public class AddnFixActivity extends ActionBarActivity {
    private int[] year=new int[2], mon=new int[2],day=new int[2], hour=new int[2], min=new int[2];
    String classname;
    Intent intent;
    Boolean isDateinput=false;
    Boolean isTimeinput=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("Add");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffff7300));

        final EditText edit_title=(EditText)findViewById(R.id.edit_title);
        final TextView text_date=(TextView)findViewById(R.id.text_date);
        final TextView text_time=(TextView)findViewById(R.id.text_time);

        Button bt_date=(Button)findViewById(R.id.bt_date);
        Button bt_time=(Button)findViewById(R.id.bt_time);
        Button bt_add=(Button)findViewById(R.id.bt_add);

        SharedPreferences pref_login=getSharedPreferences("login_info", Context.MODE_PRIVATE);
        classname=pref_login.getString("classname","");

        intent=getIntent();

        if(intent.getStringExtra("button").equals("fix")) {
            getSupportActionBar().setTitle("Fix");
            bt_add.setText("Fix");
            edit_title.setText(intent.getStringExtra("main_title"));
            text_date.setText(intent.getStringExtra("date"));
            text_time.setText(intent.getStringExtra("time"));
        }
        final Calendar calendar=Calendar.getInstance();

        bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year[0]= calendar.get(Calendar.YEAR);
                mon[0]=calendar.get(Calendar.MONTH);
                day[0]=calendar.get(Calendar.DAY_OF_MONTH);

                Dialog dlg_date=new DatePickerDialog(AddnFixActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int _year, int monthOfYear, int dayOfMonth) {
                        text_date.setText(String.valueOf(_year)+"."+String.valueOf(monthOfYear+1)+"."+String.valueOf(dayOfMonth));
                        year[1]=_year;
                        mon[1]=monthOfYear+1;
                        day[1]=dayOfMonth;
                        isDateinput=true;
                    }
                },year[0],mon[0],day[0]);
                dlg_date.show();
            }
        });

        bt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour[0]=calendar.get(Calendar.HOUR_OF_DAY);
                min[0]=calendar.get(Calendar.MINUTE);
                Dialog dlg_time=new TimePickerDialog(AddnFixActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        text_time.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                        hour[1]=hourOfDay;
                        min[1]=minute;
                        isTimeinput=true;
                    }
                },hour[0],min[0],true);
                dlg_time.show();
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getStringExtra("button").equals("add"))
                    add(edit_title);
                else if(intent.getStringExtra("button").equals("fix"))
                    fix(edit_title);
            }
        });
    }

    private void fix(final EditText edit_title) {
        ParseQuery<ParseObject> query=new ParseQuery<>(classname);
        query.whereContains("title",intent.getStringExtra("main_title"));
        query.whereContains("board",intent.getStringExtra("board"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                object.put("title",String.valueOf(edit_title.getText()));
                if(isDateinput)
                    object.put("duedate", String.valueOf(year[1]) + "." + String.valueOf(mon[1]) + "." + String.valueOf(day[1]));
                if(isTimeinput)
                    object.put("duetime", String.valueOf(hour[1]) + ":" + String.valueOf(min[1]));
                object.saveInBackground();
                Toast.makeText(getApplicationContext(), "fixed it.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void add(EditText edit_title) {
        ParseObject parseObject=new ParseObject(classname);
        parseObject.put("title",String.valueOf(edit_title.getText()));
        if(isDateinput && isTimeinput) {
            parseObject.put("duedate", String.valueOf(year[1]) + "." + String.valueOf(mon[1]) + "." + String.valueOf(day[1]));
            parseObject.put("duetime", String.valueOf(hour[1]) + ":" + String.valueOf(min[1]));
        }

        parseObject.put("state",intent.getStringExtra("state"));
        parseObject.put("username", ParseUser.getCurrentUser().get("name"));
        if(intent.getStringExtra("board").equals("main"))
            parseObject.put("board","main");
        else if(intent.getStringExtra("board").equals("sub")){
            parseObject.put("board","sub");
            parseObject.put("main_title",intent.getStringExtra("main_title"));
        }
        parseObject.saveInBackground();
        Toast.makeText(getApplicationContext(), "added it.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
