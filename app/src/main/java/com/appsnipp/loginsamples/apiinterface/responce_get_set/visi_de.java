package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class visi_de {
    int id;
    String fullname,numofvisi,vno,mobno,houseno,date,time,exittime;

    public visi_de(int id, String fullname, String numofvisi, String vno, String mobno, String houseno, String date, String time, String exittime) {
        this.id = id;
        this.fullname = fullname;
        this.numofvisi = numofvisi;
        this.vno = vno;
        this.mobno = mobno;
        this.houseno = houseno;
        this.date = date;
        this.time = time;
        this.exittime = exittime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumofvisi() {
        return numofvisi;
    }

    public void setNumofvisi(String numofvisi) {
        this.numofvisi = numofvisi;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public String getExittime() {
        return exittime;
    }

    public void setExittime(String exittime) {
        this.exittime = exittime;
    }
}
