package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class res_book_get_set {
    int id;
    String res_name,date,time,bookname;

    public res_book_get_set(int id, String res_name, String date, String time, String bookname) {
        this.id = id;
        this.res_name = res_name;
        this.date = date;
        this.time = time;
        this.bookname = bookname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
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

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
