package com.songmho.planning;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
        LinearLayout cur_container=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        ListView list=(ListView)cur_container.findViewById(R.id.list);
        switch (cur_position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return cur_container;
    }
}
