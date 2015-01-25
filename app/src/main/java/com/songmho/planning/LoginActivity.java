package com.songmho.planning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by songmho on 2015-01-25.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.login);
        Button signup=(Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(email.getText()).length()==0 || String.valueOf(password.getText()).length()==0)
                    Toast.makeText(getApplicationContext(),"이메일과 비밀번호를 적어주세요.",Toast.LENGTH_SHORT).show();
                else {
                    ParseUser.logInInBackground(String.valueOf(email.getText()),String.valueOf(password.getText()), new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if(parseUser!=null){
                                Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(),"이메일과 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}