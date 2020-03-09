package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class mainte_get_set {


    int id;
    String billname,amount,lastdate;

    public mainte_get_set(int id, String billname, String amount, String lastdate) {
        this.id = id;
        this.billname = billname;
        this.amount = amount;
        this.lastdate = lastdate;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
}
