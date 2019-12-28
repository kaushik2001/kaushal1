package com.appsnipp.loginsamples.member_recycle;

public class member_data {
    public member_data(String m_flat, String m_name, String m_mobile, String m_email, String m_member) {
        this.m_flat = m_flat;
        this.m_name = m_name;
        this.m_mobile = m_mobile;
        this.m_email = m_email;
        this.m_member = m_member;
    }

    public String getM_flat() {
        return m_flat;
    }

    public void setM_flat(String m_flat) {
        this.m_flat = m_flat;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    public String getM_member() {
        return m_member;
    }

    public void setM_member(String m_member) {
        this.m_member = m_member;
    }

    String m_flat,m_name,m_mobile,m_email,m_member;
}
