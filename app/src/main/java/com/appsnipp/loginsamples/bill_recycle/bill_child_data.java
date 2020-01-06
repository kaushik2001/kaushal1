package com.appsnipp.loginsamples.bill_recycle;

public class bill_child_data {
    String tdname,tdamt;

    public String getTdname() {
        return tdname;
    }

    public void setTdname(String tdname) {
        this.tdname = tdname;
    }

    public String getTdamt() {
        return tdamt;
    }

    public void setTdamt(String tdamt) {
        this.tdamt = tdamt;
    }

    public bill_child_data(String tdname, String tdamt) {
        this.tdname = tdname;
        this.tdamt = tdamt;
    }
}
