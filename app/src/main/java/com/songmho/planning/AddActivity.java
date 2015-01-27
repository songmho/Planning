package com.songmho.planning;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by songmho on 2015-01-15.
 */
public class AddActivity extends DialogFragment {

    EditText edit_title;
    EditText edit_duedate;
    String board;
    String main_title;
    String classname;
    public  AddActivity(){
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        board=getArguments().getString("board");
        main_title=getArguments().getString("main_title");
        builder.setView(inflater.inflate(R.layout.activity_add,null));

        builder.setTitle("일정 추가");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences pref_login=getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
                classname=pref_login.getString("classname","");
                ParseObject test=new ParseObject(classname);
                test.put("title",String.valueOf(edit_title.getText()));
                test.put("duedate",String.valueOf(edit_duedate.getText()));
                test.put("board",board);
                test.put("username", ParseUser.getCurrentUser().getString("name"));
                if(board.equals("sub"))
                    test.put("par_board",main_title);
                test.put("state","todo");
                test.saveInBackground();
                Toast.makeText(getActivity(),String.valueOf(edit_title.getText()),Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"취소되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        edit_title=(EditText)getDialog().findViewById(R.id.edit_title);
        edit_duedate=(EditText)getDialog().findViewById(R.id.edit_duedate);
    }

    /*
    public AddActivity(final Context context, final String board, final String main_title) {
        super(context);
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
                test.put("board",board);
                test.put("username", ParseUser.getCurrentUser().getString("name"));
                if(board.equals("sub"))
                   test.put("par_board",main_title);
                test.put("state","todo");
                test.saveInBackground();
                dismiss();
                Toast.makeText(context,"입력되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

    }*/
}
