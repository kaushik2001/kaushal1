package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class bill_get_set {
    int id;
    String billname,totalamt,billdate;

    public bill_get_set(int id, String billname, String totalamt, String billdate) {
        this.id = id;
        this.billname = billname;
        this.totalamt = totalamt;
        this.billdate = billdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillname() {
        return billname;
    }

    public void setBillname(String billname) {
        this.billname = billname;
    }

    public String getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(String totalamt) {
        this.totalamt = totalamt;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }
}
