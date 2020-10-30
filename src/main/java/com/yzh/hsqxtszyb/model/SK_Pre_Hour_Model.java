package com.yzh.hsqxtszyb.model;

import java.util.Date;

public class SK_Pre_Hour_Model {
    private Date DateTime;
    private String StationID;
    private float PRE_1h;

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public String getStationID() {
        return StationID;
    }

    public void setStationID(String stationID) {
        StationID = stationID;
    }

    public float getPRE_1h() {
        return PRE_1h;
    }

    public void setPRE_1h(float PRE_1h) {
        this.PRE_1h = PRE_1h;
    }
}
