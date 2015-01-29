package com.songmho.planning;

/**
 * Created by songmho on 2015-01-13.
 */
public class Listitem {
    String title;
    String duedate;
    String duetime;

    public Listitem(String title, String duedate,String duetime){
        this.title=title;
        this.duedate=duedate;
        this.duetime=duetime;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDuedate(){
        return this.duedate;
    }
    public String getDuetime(){
        return this.duetime;
    }
}
