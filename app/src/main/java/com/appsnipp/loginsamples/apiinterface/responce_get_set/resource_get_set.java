package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class resource_get_set {
    int id;
    String name,capacity,charge,details;

    public resource_get_set(int id, String name, String capacity, String charge, String details) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.charge = charge;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
