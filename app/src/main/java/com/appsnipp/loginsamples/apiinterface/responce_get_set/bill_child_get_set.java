package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class bill_child_get_set {
    int id;
    String bill_id,spnt_name,spnt_amt;

    public bill_child_get_set(int id, String bill_id, String spnt_name, String spnt_amt) {
        this.id = id;
        this.bill_id = bill_id;
        this.spnt_name = spnt_name;
        this.spnt_amt = spnt_amt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getSpnt_name() {
        return spnt_name;
    }

    public void setSpnt_name(String spnt_name) {
        this.spnt_name = spnt_name;
    }

    public String getSpnt_amt() {
        return spnt_amt;
    }

    public void setSpnt_amt(String spnt_amt) {
        this.spnt_amt = spnt_amt;
    }
}
