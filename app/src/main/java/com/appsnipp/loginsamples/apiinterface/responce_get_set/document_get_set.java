package com.appsnipp.loginsamples.apiinterface.responce_get_set;

public class document_get_set {
    int id;
    String document_name,document_file;

    public document_get_set(int id, String document_name, String document_file) {
        this.id = id;
        this.document_name = document_name;
        this.document_file = document_file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_file() {
        return document_file;
    }

    public void setDocument_file(String document_file) {
        this.document_file = document_file;
    }
}
