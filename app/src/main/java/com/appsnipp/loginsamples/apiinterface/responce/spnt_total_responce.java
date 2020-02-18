package com.appsnipp.loginsamples.apiinterface.responce;


import com.appsnipp.loginsamples.apiinterface.responce_get_set.spnt_total_get_set;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class spnt_total_responce {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("de")
    @Expose
    private spnt_total_get_set de;

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


    public spnt_total_get_set getDe() {
        return de;
    }

    public void setDe(spnt_total_get_set de) {
        this.de = de;
    }
}
