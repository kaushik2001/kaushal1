package com.appsnipp.loginsamples.apiinterface.responce;


import com.appsnipp.loginsamples.apiinterface.responce_get_set.visi_de;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class visidetail_responce {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("de")
    @Expose
    private List<visi_de> de = null;

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

    public List<visi_de> getDe() {
        return de;
    }

    public void setDe(List<visi_de> de) {
        this.de = de;
    }


}
