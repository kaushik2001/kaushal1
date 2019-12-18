package com.appsnipp.loginsamples.resource_list;

public class data {
    String text1;
    String text2;
    String text;
    String text3;
    int img;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }



    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }



    public data(String text,String text1, String text2,String text3, int img) {

        this.text=text;
        this.text3=text3;

        this.text1 = text1;
        this.text2 = text2;
        this.img = img;
    }
}
