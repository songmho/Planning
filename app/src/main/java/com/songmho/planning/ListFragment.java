package com.songmho.planning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by songmho on 2015-01-03.
 */
public class ListFragment extends Fragment {
    int cur_position;
    public ListFragment(int position) {
        this.cur_position=position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout cur_container=(FrameLayout)inflater.inflate(R.layout.fragment_list,container,false);

        ListView list=(ListView)cur_container.findViewById(R.id.list);
        ArrayList<Mainlistitem> items=new ArrayList<>();
        MainListAdapter listAdapter;

        switch (cur_position){
            case 0:
                Mainlistitem item1=new Mainlistitem("Title","duedate");

                items.add(item1);
                listAdapter=new MainListAdapter(getActivity(),items);
                list.setAdapter(listAdapter);
                break;
            case 1:
                Mainlistitem item2=new Mainlistitem("Title","duedate");

                items.add(item2);
                listAdapter=new MainListAdapter(getActivity(),items);
                list.setAdapter(listAdapter);
                break;
            case 2:
                Mainlistitem item3=new Mainlistitem("Title","duedate");

                items.add(item3);
                listAdapter=new MainListAdapter(getActivity(),items);
                list.setAdapter(listAdapter);
                break;
        }
        return cur_container;
    }
}
