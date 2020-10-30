package com.yzh.hsqxtszyb.model;

import java.util.Date;

public class SK_Tem_Hour_Model {
    private Date DateTime;
    private String StationID;
    private float TEM;
    private float TEM_Max;
    private Date TEM_Max_OTime;
    private float TEM_Min;

    public float getTEM() {
        return TEM;
    }

    public void setTEM(float TEM) {
        this.TEM = TEM;
    }

    public float getTEM_Max() {
        return TEM_Max;
    }

    public void setTEM_Max(float TEM_Max) {
        this.TEM_Max = TEM_Max;
    }

    public Date getTEM_Max_OTime() {
        return TEM_Max_OTime;
    }

    public void setTEM_Max_OTime(Date TEM_Max_OTime) {
        this.TEM_Max_OTime = TEM_Max_OTime;
    }

    public float getTEM_Min() {
        return TEM_Min;
    }

    public void setTEM_Min(float TEM_Min) {
        this.TEM_Min = TEM_Min;
    }

    public Date getTEM_Min_OTime() {
        return TEM_Min_OTime;
    }

    public void setTEM_Min_OTime(Date TEM_Min_OTime) {
        this.TEM_Min_OTime = TEM_Min_OTime;
    }

    private Date TEM_Min_OTime;

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

}
