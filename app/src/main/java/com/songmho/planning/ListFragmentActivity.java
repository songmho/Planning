package com.songmho.planning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

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

        ListView list=(ListView)cur_container.findViewById(R.id.list);
        ArrayList<Mainlistitem> items=new ArrayList<>();
        int cur_position=getArguments().getInt("max_page");

        switch (cur_position){
            case 0:                     //To Do list
                Mainlistitem item1=new Mainlistitem("Title","duedate");
                items.add(item1);

                make_list(list, items);
                break;
            case 1:                     //Doing list
                Mainlistitem item2=new Mainlistitem("Title","duedate");
                items.add(item2);

                make_list(list, items);
                break;
            case 2:                     //Done list
                Mainlistitem item3=new Mainlistitem("Title","duedate");
                items.add(item3);

                make_list(list, items);
                break;
        }
        return cur_container;
    }

    private void make_list(ListView list, ArrayList<Mainlistitem> items) {      //ArrayList를 가지고 list를 만드는 메소드
        MainListAdapter listAdapter;
        listAdapter=new MainListAdapter(getActivity(),items);
        list.setAdapter(listAdapter);
    }
}
