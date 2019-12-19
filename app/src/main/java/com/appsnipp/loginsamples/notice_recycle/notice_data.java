package com.appsnipp.loginsamples.notice_recycle;

public class notice_data {
    String heading;
    String desc;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String name;
    String time;

    public notice_data(String heading, String desc, String name, String time) {
        this.heading = heading;
        this.desc = desc;
        this.name = name;
        this.time = time;
    }



}
