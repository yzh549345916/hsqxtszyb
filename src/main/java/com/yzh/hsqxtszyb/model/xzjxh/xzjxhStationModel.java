package com.yzh.hsqxtszyb.model.xzjxh;

public class xzjxhStationModel {
    private String StatioID;
    private String Name;
    private short Station_levl;
    private double WD;
    private double JD;
    private double High;
    private String GJStatioID;

    public String getStatioID() {
        return StatioID;
    }

    public void setStatioID(String statioID) {
        StatioID = statioID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public short getStation_levl() {
        return Station_levl;
    }

    public void setStation_levl(short station_levl) {
        Station_levl = station_levl;
    }

    public double getWD() {
        return WD;
    }

    public void setWD(double WD) {
        this.WD = WD;
    }

    public double getJD() {
        return JD;
    }

    public void setJD(double JD) {
        this.JD = JD;
    }

    public double getHigh() {
        return High;
    }

    public void setHigh(double high) {
        High = high;
    }

    public String getGJStatioID() {
        return GJStatioID;
    }

    public void setGJStatioID(String GJStatioID) {
        this.GJStatioID = GJStatioID;
    }
}
