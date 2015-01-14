package com.songmho.planning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songmho on 2015-01-13.
 */
public class MainListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<Mainlistitem> items;
    private int layout=R.layout.list_item;

    public MainListAdapter(Context context, ArrayList<Mainlistitem> items){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }

        Mainlistitem mainlistitem=items.get(position);

        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView duedate=(TextView)convertView.findViewById(R.id.duedate);
        title.setText(mainlistitem.getTitle());
        duedate.setText(mainlistitem.getDuedate());
        return convertView;
    }
}
