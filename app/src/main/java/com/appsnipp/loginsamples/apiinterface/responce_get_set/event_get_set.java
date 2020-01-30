package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class event_get_set {
    int id;
    String name,place,members,date,time;

    public event_get_set(int id, String name, String place, String members, String date, String time) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.members = members;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
