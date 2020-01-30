package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class notice_get_set {
    int id;
    String header,description,aname,datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public notice_get_set(int id, String header, String description, String aname, String datetime) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.aname = aname;
        this.datetime = datetime;
    }
}
