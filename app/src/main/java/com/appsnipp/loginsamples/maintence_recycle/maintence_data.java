package com.appsnipp.loginsamples.maintence_recycle;

public class maintence_data {
    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_ammount() {
        return m_ammount;
    }

    public void setM_ammount(String m_ammount) {
        this.m_ammount = m_ammount;
    }

    public String getM_lastdate() {
        return m_lastdate;
    }

    public void setM_lastdate(String m_lastdate) {
        this.m_lastdate = m_lastdate;
    }

    String m_name;
    String m_ammount;
    String m_lastdate;

    public maintence_data(String m_name, String m_ammount, String m_lastdate) {
        this.m_name = m_name;
        this.m_ammount = m_ammount;
        this.m_lastdate = m_lastdate;
    }


}
