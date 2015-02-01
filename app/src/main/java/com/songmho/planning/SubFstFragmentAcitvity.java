package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songmho on 2015-01-30.
 */
public class SubFstFragmentAcitvity extends Fragment {
    String title;
    String classname;
    String duedate_str;
    String duetime_str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout cur_container=(FrameLayout)inflater.inflate(R.layout.activity_detail,container,false);

        SharedPreferences pref_login=getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
        classname=pref_login.getString("classname","");
        title=getArguments().getString("title");

        final TextView duedate=(TextView)cur_container.findViewById(R.id.duedate);
        final TextView duetime=(TextView)cur_container.findViewById(R.id.duetime);
        final RadioButton radio_todo=(RadioButton)cur_container.findViewById(R.id.radio_todo);
        final RadioButton radio_doing=(RadioButton)cur_container.findViewById(R.id.radio_doing);
        final RadioButton radio_done=(RadioButton)cur_container.findViewById(R.id.radio_done);
        ImageButton fix=(ImageButton)cur_container.findViewById(R.id.fix);

        ParseQuery<ParseObject> query= new ParseQuery<>(classname);
        query.whereContains("title",title);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
                duedate.setText(object.getString("duedate"));
                duetime.setText(object.getString("duetime"));
                duedate_str=object.getString("duedate");
                duetime_str=object.getString("duetime");

                if(object.getString("state").equals("todo"))
                    radio_todo.setChecked(true);
                else if(object.getString("state").equals("doing"))
                    radio_doing.setChecked(true);
                else
                    radio_done.setChecked(true);
            }
        });

        radio_todo.setOnClickListener(new Radioclick(title, getActivity(), "main", classname));
        radio_doing.setOnClickListener(new Radioclick(title, getActivity(), "main", classname));
        radio_done.setOnClickListener(new Radioclick(title, getActivity(), "main", classname));
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFix();
            }
        });
        return cur_container;
    }

    private void openFix() {
        Intent intent=new Intent(getActivity(), AddnFixActivity.class);
        intent.putExtra("button","fix");
        intent.putExtra("board","main");
        intent.putExtra("main_title",title);
        intent.putExtra("date",duedate_str);
        intent.putExtra("time",duetime_str);
        startActivity(intent);
    }

}
