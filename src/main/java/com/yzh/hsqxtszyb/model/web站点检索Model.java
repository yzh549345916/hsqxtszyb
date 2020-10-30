package com.yzh.hsqxtszyb.model;

public class web站点检索Model {
    private String ID;

    public String getStationLevlString() {
        return StationLevlString;
    }

    public void setStationLevlString(String stationLevlString) {
        StationLevlString = stationLevlString;
    }

    public web站点检索Model() {
    }

    public web站点检索Model(String ID) {
        this.ID = ID;
    }

    private String StationLevlString;
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }



}
