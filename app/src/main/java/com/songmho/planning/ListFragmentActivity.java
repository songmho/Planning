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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        final ListView list=(ListView)cur_container.findViewById(R.id.list);
        final ArrayList<Mainlistitem> items=new ArrayList<>();
        int cur_position=getArguments().getInt("max_page");

        switch (cur_position){
            case 0:                     //To Do list
                final Mainlistitem[] item1 = new Mainlistitem[10];
                ParseQuery<ParseObject> query_todo=ParseQuery.getQuery("Test");
                query_todo.whereContains("state", "todo");
                query_todo.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item1[i] = new Mainlistitem(parseObject.getString("title"),parseObject.getString("duedate"));
                            items.add(item1[i]);
                        }
                        make_list(list, items);
                    }
                });
                    list.setOnItemClickListener(new listitemclick(cur_position));
                break;
            case 1:                     //Doing list
                final Mainlistitem[] item2 = new Mainlistitem[10];
                ParseQuery<ParseObject> query_doing=ParseQuery.getQuery("Test");
                query_doing.whereContains("state", "doing");
                query_doing.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item2[i] = new Mainlistitem(parseObject.getString("title"), parseObject.getString("duedate"));
                            items.add(item2[i]);
                        }
                        make_list(list, items);
                    }
                });
                list.setOnItemClickListener(new listitemclick(cur_position));
                break;
            case 2:                     //Done list
                final Mainlistitem[] item3 = new Mainlistitem[10];
                ParseQuery<ParseObject> query_done=ParseQuery.getQuery("Test");
                query_done.whereContains("state", "done");
                query_done.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        for (int i = 0; i < parseObjects.size(); i++) {
                            ParseObject parseObject = parseObjects.get(i);
                            item3[i] = new Mainlistitem(parseObject.getString("title"), parseObject.getString("duedate"));
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

    private void make_list(ListView list, ArrayList<Mainlistitem> items) {      //ArrayList를 가지고 list를 만드는 메소드
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
            switch (cur_position){
                case 0:
                    Intent intent=new Intent(getActivity(),DetailActivity.class);
                    intent.putExtra("title",String.valueOf(parent.getItemAtPosition(position)));
                    startActivity(intent);
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
}
