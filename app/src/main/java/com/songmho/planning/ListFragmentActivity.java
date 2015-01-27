package com.songmho.planning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-01-03.
 */
public class ListFragmentActivity extends Fragment {
    String cur_bor;
    int cur_position;
    Listitem[] item1;
    ArrayList<Listitem> items;
    ListView list;
    String classname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref_login=getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
        classname=pref_login.getString("classname","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        list=(ListView)cur_container.findViewById(R.id.list);
        items=new ArrayList<>();
        cur_position=getArguments().getInt("max_page");
        cur_bor=getArguments().getString("cur_bor");
        item1 = new Listitem[10];
        list.setOnItemClickListener(new listitemclick(cur_position));

        return cur_container;
    }

    private void make_list(ListView list, ArrayList<Listitem> items) {      //ArrayList를 가지고 list를 만드는 메소드
        MainListAdapter listAdapter;
        listAdapter=new MainListAdapter(getActivity(),items);
        list.setAdapter(listAdapter);
    }

    private class listitemclick implements android.widget.AdapterView.OnItemClickListener {
        int cur_position;
        public listitemclick(int cur_position) {
            this.cur_position=cur_position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
                intent=new Intent(getActivity(),SubActivity.class);
            if(cur_bor.equals("sub"))
                intent=new Intent(getActivity(),DetailActivity.class);
            switch (cur_position){
                case 0:
                    intent.putExtra("title",String.valueOf(parent.getItemAtPosition(position)));
                    intent.putExtra("state",0);
                    startActivity(intent);
                    break;
                case 1:
                    intent.putExtra("title",String.valueOf(parent.getItemAtPosition(position)));
                    intent.putExtra("state",1);
                    startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("title",String.valueOf(parent.getItemAtPosition(position)));
                    intent.putExtra("state",2);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        items.clear();
        ParseQuery<ParseObject> query=ParseQuery.getQuery(classname);
        query.whereContains("username", ParseUser.getCurrentUser().getString("name"));
        if(cur_bor.equals("main"))
            query.whereContains("board","main");
        else if(cur_bor.equals("sub")) {
            query.whereContains("par_board",getArguments().getString("par_board"));
            query.whereContains("board", "sub");
        }
        switch(cur_position){
            case 0:
                query.whereContains("state", "todo");
                break;
            case 1:
                query.whereContains("state", "doing");
                break;
            case 2:
                query.whereContains("state", "done");
                break;
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(parseObjects==null)
                    return;
                for (int i = 0; i < parseObjects.size(); i++) {
                    ParseObject parseObject = parseObjects.get(i);
                    item1[i] = new Listitem(parseObject.getString("title"),parseObject.getString("duedate"));
                    items.add(item1[i]);
                }
                    make_list(list, items);
            }
        });
    }
}
