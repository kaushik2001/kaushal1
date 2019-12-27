package com.appsnipp.loginsamples.event_recycle_view;

public class event_data {
    String e_name;
    String e_place;
    String e_member;
    String e_date;

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_place() {
        return e_place;
    }

    public void setE_place(String e_place) {
        this.e_place = e_place;
    }

    public String getE_member() {
        return e_member;
    }

    public void setE_member(String e_member) {
        this.e_member = e_member;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    String e_time;

    public event_data(String e_name, String e_place, String e_member, String e_date, String e_time) {
        this.e_name = e_name;
        this.e_place = e_place;
        this.e_member = e_member;
        this.e_date = e_date;
        this.e_time = e_time;
    }



}
