package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class prof_get_set {
    int id;
    String emptype,proftype,cmpname, desi, conno;

    public prof_get_set(int id, String emptype, String proftype, String cmpname, String desi, String conno) {
        this.id = id;
        this.emptype = emptype;
        this.proftype = proftype;
        this.cmpname = cmpname;
        this.desi = desi;
        this.conno = conno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public String getProftype() {
        return proftype;
    }

    public void setProftype(String proftype) {
        this.proftype = proftype;
    }

    public String getCmpname() {
        return cmpname;
    }

    public void setCmpname(String cmpname) {
        this.cmpname = cmpname;
    }

    public String getDesi() {
        return desi;
    }

    public void setDesi(String desi) {
        this.desi = desi;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }
}
