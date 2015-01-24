package com.songmho.planning;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-01-03.
 */
public class ListFragmentActivity extends Fragment {
    String cur_bor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        final ListView list=(ListView)cur_container.findViewById(R.id.list);
        final ArrayList<Listitem> items=new ArrayList<>();
        int cur_position=getArguments().getInt("max_page");
        cur_bor=getArguments().getString("cur_bor");
        switch (cur_position){
            case 0:                     //To Do list
                final Listitem[] item1 = new Listitem[10];
                ParseQuery<ParseObject> query_todo=ParseQuery.getQuery("Test");
                if(cur_bor.equals("main"))
                    query_todo.whereContains("board","main");
                else if(cur_bor.equals("sub")) {
                    query_todo.whereContains("par_board",getArguments().getString("par_board"));
                    query_todo.whereContains("board", "sub");
                }
                query_todo.whereContains("state", "todo");
                query_todo.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item1[i] = new Listitem(parseObject.getString("title"),parseObject.getString("duedate"));
                            items.add(item1[i]);
                        }
                        make_list(list, items);
                    }
                });
                    list.setOnItemClickListener(new listitemclick(cur_position));
                break;
            case 1:                     //Doing list
                final Listitem[] item2 = new Listitem[10];
                ParseQuery<ParseObject> query_doing=ParseQuery.getQuery("Test");
                if(cur_bor.equals("main"))
                    query_doing.whereContains("board","main");
                else if(cur_bor.equals("sub")) {
                    query_doing.whereContains("par_board",getArguments().getString("par_board"));
                    query_doing.whereContains("board", "sub");
                }
                query_doing.whereContains("state", "doing");
                query_doing.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item2[i] = new Listitem(parseObject.getString("title"), parseObject.getString("duedate"));
                            items.add(item2[i]);
                        }
                        make_list(list, items);
                    }
                });
                list.setOnItemClickListener(new listitemclick(cur_position));
                break;
            case 2:                     //Done list
                final Listitem[] item3 = new Listitem[10];
                ParseQuery<ParseObject> query_done=ParseQuery.getQuery("Test");
                if(cur_bor.equals("main"))
                    query_done.whereContains("board","main");
                else if(cur_bor.equals("sub")) {
                    query_done.whereContains("par_board",getArguments().getString("par_board"));
                    query_done.whereContains("board", "sub");
                }
                query_done.whereContains("state", "done");
                query_done.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item3[i] = new Listitem(parseObject.getString("title"), parseObject.getString("duedate"));
                            items.add(item3[i]);
                        }
                        make_list(list, items);
                    }
                });
                list.setOnItemClickListener(new listitemclick(cur_position));
                break;
        }
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
}
