package com.yzh.hsqxtszyb.model;

public class OpenlayersQJMethodPropertiesModel {
    private String name;
    private String id;



    public int getStationLevel() {
        return StationLevel;
    }

    public void setStationLevel(int stationLevel) {
        StationLevel = stationLevel;
    }

    private int StationLevel;
    public OpenlayersQJMethodPropertiesModel(String name, String id, double ybvalue, String ybName, String ybUnit) {
        this.name = name;
        this.id = id;
        this.ybvalue = ybvalue;
        this.ybName = ybName;
        this.ybUnit = ybUnit;
    }
    public OpenlayersQJMethodPropertiesModel(String name, String id, int stationLevel, double ybvalue, String ybName, String ybUnit) {
        this.name = name;
        this.id = id;
        StationLevel = stationLevel;
        this.ybvalue = ybvalue;
        this.ybName = ybName;
        this.ybUnit = ybUnit;
    }

    private double ybvalue;
    private double ybvalue2;

    public double getYbvalue2() {
        return ybvalue2;
    }

    public void setYbvalue2(double ybvalue2) {
        this.ybvalue2 = ybvalue2;
    }

    public double getYbvalue() {
        return ybvalue;
    }

    public void setYbvalue(double ybvalue) {
        this.ybvalue = ybvalue;
    }

    public String getYbName() {
        return ybName;
    }

    public void setYbName(String ybName) {
        this.ybName = ybName;
    }

    public String getYbUnit() {
        return ybUnit;
    }

    public void setYbUnit(String ybUnit) {
        this.ybUnit = ybUnit;
    }

    private String ybName;
    private String ybUnit;
    private String ybName2;

    public OpenlayersQJMethodPropertiesModel(String name, String id, double ybvalue, double ybvalue2, String ybName, String ybUnit, String ybName2, String ybUnit2) {
        this.name = name;
        this.id = id;
        this.ybvalue = ybvalue;
        this.ybvalue2 = ybvalue2;
        this.ybName = ybName;
        this.ybUnit = ybUnit;
        this.ybName2 = ybName2;
        this.ybUnit2 = ybUnit2;
    }

    public String getYbName2() {
        return ybName2;
    }

    public void setYbName2(String ybName2) {
        this.ybName2 = ybName2;
    }

    public String getYbUnit2() {
        return ybUnit2;
    }

    public void setYbUnit2(String ybUnit2) {
        this.ybUnit2 = ybUnit2;
    }

    private String ybUnit2;
    public OpenlayersQJMethodPropertiesModel() {
    }

    public OpenlayersQJMethodPropertiesModel(String name, String id, double ybvalue) {
        this.name = name;
        this.id = id;
        this.ybvalue = ybvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
