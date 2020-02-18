package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class spnt_total_get_set {
    String bill_id,sum;

    public spnt_total_get_set(String bill_id, String sum) {
        this.bill_id = bill_id;
        this.sum = sum;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
