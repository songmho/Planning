package com.songmho.planning;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xffFF7300));
        Intent intent=getIntent();


        TextView title=(TextView)findViewById(R.id.title);
        final TextView duedate=(TextView)findViewById(R.id.duedate);
        final TextView state=(TextView)findViewById(R.id.state);

        title.setText(intent.getStringExtra("title"));
        ParseQuery<ParseObject> query= new ParseQuery<>("Test");
        query.whereContains("title",intent.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));
                state.setText(object.getString("state"));
            }
        });
    }
}
