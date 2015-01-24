package com.songmho.planning;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
* Created by songmho on 2015-01-25.
*/
public class Radioclick implements View.OnClickListener {
    String main_title;
    Context context;
    String cur_board;
    public Radioclick(String main_title, Context applicationContext, String cur_board) {
        this.main_title=main_title;
        this.context=applicationContext;
        this.cur_board=cur_board;
    }

    @Override
    public void onClick(final View v) {
        ParseQuery<ParseObject> query=new ParseQuery<>("Test");
        query.whereContains("title",main_title);
        if(cur_board.equals("main"))
            query.whereContains("board","main");
        else if(cur_board.equals("sub"))
            query.whereContains("board","sub");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                ParseObject object=parseObjects.get(0);
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
                object.saveInBackground();Toast.makeText(context, "현재 상태 : " + object.getString("state"), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
