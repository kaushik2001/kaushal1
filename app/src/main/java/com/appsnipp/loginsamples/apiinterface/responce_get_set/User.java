package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class User {
    int id;
    String fname,lname,mobno,email,houseno;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public User(int id, String fname, String lname, String mobno, String email, String houseno) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mobno = mobno;
        this.email = email;
        this.houseno = houseno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }
}
