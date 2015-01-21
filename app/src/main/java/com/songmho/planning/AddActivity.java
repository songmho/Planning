package com.songmho.planning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

/**
 * Created by songmho on 2015-01-15.
 */
public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);

        final EditText edit_title=(EditText)findViewById(R.id.edit_title);
        final EditText edit_duedate=(EditText)findViewById(R.id.edit_duedate);
        Button button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject test=new ParseObject("Test");
                test.put("title",String.valueOf(edit_title.getText()));
                test.put("duedate",String.valueOf(edit_duedate.getText()));
                test.put("state","todo");
                test.saveInBackground();
            }
        });
    }
}
