package com.songmho.planning;

/**
 * Created by songmho on 2015-01-13.
 */
public class Listitem {
    String title;
    String duedate;
    String duetime;
    double burndown;

    public Listitem(String title, String duedate,String duetime, double burndown){
        this.title=title;
        this.duedate=duedate;
        this.duetime=duetime;
        this.burndown=burndown;
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
    public double getBurndown(){
        return  this.burndown;
    }
}
