package com.songmho.planning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
    ListAdapter listAdapter;

    ImageButton add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout cur_container=(FrameLayout)inflater.inflate(R.layout.fragment_list,container,false);

        add=(ImageButton)cur_container.findViewById(R.id.add);
        list=(ListView)cur_container.findViewById(R.id.list);
        items=new ArrayList<>();
        cur_position=getArguments().getInt("max_page");
        cur_bor=getArguments().getString("cur_bor");
        classname=getArguments().getString("classname");
        item1 = new Listitem[100];

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });
        list.setOnItemClickListener(new Listitemclick(cur_position));
        list.setOnItemLongClickListener(new ListLongclick(cur_position));
        return cur_container;
    }

    private void openAdd() {                    //add액티비티 연결
        Intent intent=new Intent(getActivity(), AddnFixActivity.class);
        intent.putExtra("button","add");
        if(cur_bor.equals("main"))
           intent.putExtra("board","main");
        else if(cur_bor.equals("sub")){
            intent.putExtra("board","sub");
            intent.putExtra("main_title",getArguments().getString("main_title"));
        }
        switch (cur_position) {
            case 0:
                intent.putExtra("state","todo");
                break;
            case 1:
                intent.putExtra("state","doing");
                break;
            case 2:
                intent.putExtra("state","done");
                break;
        }
        startActivity(intent);
    }

    private void make_list(ListView list, ArrayList<Listitem> items) {      //ArrayList를 가지고 list를 만드는 메소드
        listAdapter=new ListAdapter(getActivity(),items);
        list.setAdapter(listAdapter);
    }

    private class Listitemclick implements android.widget.AdapterView.OnItemClickListener {
        int cur_position;
        public Listitemclick(int cur_position) {
            this.cur_position=cur_position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
                intent=new Intent(getActivity(),DetailActivity.class);
            if(cur_bor.equals("main")) {
                intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("par_board",String.valueOf(parent.getItemAtPosition(position)));
            }
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
        if(items!=null)
        items.clear();
        ParseQuery<ParseObject> query=new ParseQuery<>(classname);
        query.whereContains("username", ParseUser.getCurrentUser().getString("name"));
        if(cur_bor.equals("main"))
            query.whereContains("board","main");
        else if(cur_bor.equals("sub")) {
            query.whereContains("main_title",getArguments().getString("main_title"));
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
                    String duedate=parseObject.getString("duedate_year")+"."+parseObject.getString("duedate_mon")+"."+parseObject.getString("duedate_day");
                    item1[i] = new Listitem(parseObject.getString("title"),duedate,parseObject.getString("duetime"));
                    items.add(item1[i]);
                }
                    make_list(list, items);
            }
        });
    }


    private class ListLongclick implements AdapterView.OnItemLongClickListener {
        int cur_position;
        public ListLongclick(int cur_position) {
            this.cur_position=cur_position;
        }
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setMessage("Do you want delete it?").setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ParseQuery<ParseObject> query=new ParseQuery<>(classname);
                            query.whereContains("username", ParseUser.getCurrentUser().getString("name"));
                            if(cur_bor.equals("main"))
                                query.whereContains("board","main");
                            else if(cur_bor.equals("sub")) {
                                query.whereContains("main_title",getArguments().getString("main_title"));
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
                                    ParseObject object=parseObjects.get(position);
                                    try {
                                        object.delete();
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    object.saveInBackground();
                                    Toast.makeText(getActivity().getApplicationContext(),"deleted it.",Toast.LENGTH_SHORT).show();
                                    onResume();
                                }
                            });
                        }
                    }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

            return true;
        }
    }
}
