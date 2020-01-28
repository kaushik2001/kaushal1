package com.appsnipp.loginsamples.apiinterface.responce;

import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginresponce {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
