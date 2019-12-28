package com.appsnipp.loginsamples.bill_recycle;

public class bill_data {

    String b_due;
    String b_no;
    String b_name;
    String b_total;
    public bill_data(String b_no, String b_name, String b_total, String b_due) {
        this.b_no = b_no;
        this.b_name = b_name;
        this.b_total = b_total;
        this.b_due = b_due;
    }


    public String getB_no() {
        return b_no;
    }

    public void setB_no(String b_no) {
        this.b_no = b_no;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_total() {
        return b_total;
    }

    public void setB_total(String b_total) {
        this.b_total = b_total;
    }

    public String getB_due() {
        return b_due;
    }

    public void setB_due(String b_due) {
        this.b_due = b_due;
    }

}
