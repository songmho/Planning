package com.songmho.planning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        final EditText editText=(EditText)findViewById(R.id.edittext);
        Button button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),editText.getText(),Toast.LENGTH_SHORT).show();
                ParseObject test=new ParseObject("test");
                test.put("ttt",editText.getText());
                test.saveInBackground();
            }
        });
    }
}
