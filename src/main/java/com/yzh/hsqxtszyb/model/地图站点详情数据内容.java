package com.yzh.hsqxtszyb.model;

public class 地图站点详情数据内容 {
    private  double skValue1;
    private  double ybValue1;
    private  String ybValue2;

    public 地图站点详情数据内容(double ybValue1, String ybValue2, String ybValue3, String timeStr, String stationID, String stationName) {
        this.ybValue1 = ybValue1;
        this.ybValue2 = ybValue2;
        this.ybValue3 = ybValue3;
        this.timeStr = timeStr;
        this.stationID = stationID;
        this.stationName = stationName;
    }

    public String getYbValue3() {
        return ybValue3;
    }

    public void setYbValue3(String ybValue3) {
        this.ybValue3 = ybValue3;
    }

    private  String ybValue3;
    private  String timeStr;
    private String stationID;
    private String stationName;

    public 地图站点详情数据内容(double ybValue1, String timeStr, String stationID, String stationName) {
        this.ybValue1 = ybValue1;
        this.timeStr = timeStr;
        this.stationID = stationID;
        this.stationName = stationName;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public 地图站点详情数据内容() {
    }

    public 地图站点详情数据内容(double ybValue1, String timeStr) {
        this.ybValue1 = ybValue1;
        this.timeStr = timeStr;
    }

    public double getSkValue1() {
        return skValue1;
    }

    public void setSkValue1(double skValue1) {
        this.skValue1 = skValue1;
    }

    public double getYbValue1() {
        return ybValue1;
    }

    public void setYbValue1(double ybValue1) {
        this.ybValue1 = ybValue1;
    }

    public String getYbValue2() {
        return ybValue2;
    }

    public void setYbValue2(String ybValue2) {
        this.ybValue2 = ybValue2;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }



}
