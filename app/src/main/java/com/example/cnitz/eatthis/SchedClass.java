package com.example.cnitz.eatthis;

/**
 * Created by Cnitz on 10/21/15.
 */
public class SchedClass {

    public int id;
    public String name;
    public String startTime;
    public String endTime;
    public String location;
    public boolean mondays;
    public boolean tuesdays;
    public boolean wednesdays;
    public boolean thursdays;
    public boolean fridays;

    //Setters
    public SchedClass setId(int id){
        this.id = id;
        return this;
    }
    public SchedClass setName(String name) {
        this.name = name;
        return this;
    }
    public SchedClass setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }
    public SchedClass setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
    public SchedClass setLocation(String location) {
        this.location = location;
        return this;
    }
    public SchedClass setDays(String days){
        if(days.equals(new String("Monday"))){
            mondays = true;
            return this;
        }
        else if(days.equals(new String("Tuesday"))){
            tuesdays = true;
            return this;
        }
        else if(days.equals(new String("Wednesday"))){
            wednesdays = true;
            return this;
        }
        else if(days.equals(new String("Thrusday"))){
            thursdays = true;
            return this;
        }
        else if(days.equals(new String("Friday"))){
            fridays = true;
            return this;
        }
        else if(days.equals(new String("M+W+F"))){
            mondays = true;
            wednesdays = true;
            fridays = true;
            return this;
        }
        else if(days.equals(new String("T+R"))){
            tuesdays = true;
            thursdays = true;
            return this;
        }
        else return this;
    }

    //Getters
    public int getId() { return id; }
    public String getName() {return name;}
    public String getStartTime(){return startTime;}
    public String getEndTime(){return endTime;}
    public String getLocation(){return location;}
    public boolean getMonday() {return mondays;}
    public boolean getTuesday() {return tuesdays;}
    public boolean getWednesday(){return wednesdays;}
    public boolean getThursdays(){return thursdays;}
    public boolean getFridays(){return fridays;}





}
