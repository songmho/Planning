package com.songmho.planning;

/**
 * Created by songmho on 2015-01-13.
 */
public class Listitem {
    String title;
    String duedate;
    public Listitem(String title, String duedate){
        this.title=title;
        this.duedate=duedate;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDuedate(){
        return this.duedate;
    }
}
