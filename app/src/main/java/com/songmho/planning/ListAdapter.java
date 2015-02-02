package com.songmho.planning;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by songmho on 2015-01-13.
 */
public class ListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<Listitem> items;
    private int layout=R.layout.list_item;

    public ListAdapter(Context context, ArrayList<Listitem> items){
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

        notifyDataSetChanged();
        Listitem listitem =items.get(position);

        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView duedate=(TextView)convertView.findViewById(R.id.duedate);
        TextView duetime=(TextView)convertView.findViewById(R.id.duetime);
        TextView burndown=(TextView)convertView.findViewById(R.id.burndown);

        title.setText(listitem.getTitle());
        duedate.setText(listitem.getDuedate());
        duetime.setText(listitem.getDuetime());
        burndown.setText(String.valueOf(listitem.getBurndown()));

        String _duedate = listitem.getDuedate();
        if(!_duedate.equals(" . . ")) {
            String[] _date = _duedate.split("\\.");
            int year = Integer.valueOf(_date[0]);
            int mon = Integer.valueOf(_date[1]);
            int day = Integer.valueOf(_date[2]);

            Calendar calendar = Calendar.getInstance();

            if(year<calendar.get(calendar.YEAR))                            //현재 년도가 저장된 년도보다 작을 때
                convertView.setBackground(new ColorDrawable(0xffED174F));   //red
            else if(year>=calendar.get(calendar.YEAR)){                     //현재 년도가 저장된 년도와 같거나 클 때
                if(mon<calendar.get(calendar.MONTH))                        //현재 월이 저장된 월보다 작을 때
                    convertView.setBackground(new ColorDrawable(0xffED174F));   //red
                else if(mon>=calendar.get(calendar.MONTH)){                 //같거나 클 때
                    if(day<calendar.get(calendar.DATE))                         //현재 일이 저장된 년도보다 작을 때
                        convertView.setBackground(new ColorDrawable(0xffED174F));   //red

                    else if(day==calendar.get(calendar.DATE))                   //같거나 클 때때
                       convertView.setBackground(new ColorDrawable(0xffFFC423));   //yellow
                }
            }

           /* if (year == calendar.get(calendar.YEAR)) {
                if (mon == calendar.get(calendar.MONTH) + 1) {
                    if (calendar.get(calendar.DAY_OF_MONTH)>=day && calendar.get(calendar.DAY_OF_MONTH)<day+3)
                        convertView.setBackground(new ColorDrawable(0xffFFC423));   //yellow
                    else if (calendar.get(calendar.DAY_OF_MONTH)<day)
                        convertView.setBackground(new ColorDrawable(0xffED174F));   //red
                }
            }*/

        }

        return convertView;
    }
}
