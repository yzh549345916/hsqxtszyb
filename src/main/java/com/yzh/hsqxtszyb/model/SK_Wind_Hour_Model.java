package com.yzh.hsqxtszyb.model;

import java.util.Date;

public class SK_Wind_Hour_Model {
    private Date DateTime;
    private String StationID;
    private float WIN_D_Avg_2mi;
    private float WIN_S_Avg_2mi;
    private float WIN_D_Avg_10mi;
    private float WIN_S_Avg_10mi;
    private float WIN_D_S_Max;
    private float WIN_S_Max;
    private Date WIN_S_Max_OTime;
    private float WIN_D_INST;
    private float WIN_S_INST;
    private float WIN_D_INST_Max;

    public float getWIN_D_Avg_2mi() {
        return WIN_D_Avg_2mi;
    }

    public void setWIN_D_Avg_2mi(float WIN_D_Avg_2mi) {
        this.WIN_D_Avg_2mi = WIN_D_Avg_2mi;
    }

    public float getWIN_S_Avg_2mi() {
        return WIN_S_Avg_2mi;
    }

    public void setWIN_S_Avg_2mi(float WIN_S_Avg_2mi) {
        this.WIN_S_Avg_2mi = WIN_S_Avg_2mi;
    }

    public float getWIN_D_Avg_10mi() {
        return WIN_D_Avg_10mi;
    }

    public void setWIN_D_Avg_10mi(float WIN_D_Avg_10mi) {
        this.WIN_D_Avg_10mi = WIN_D_Avg_10mi;
    }

    public float getWIN_S_Avg_10mi() {
        return WIN_S_Avg_10mi;
    }

    public void setWIN_S_Avg_10mi(float WIN_S_Avg_10mi) {
        this.WIN_S_Avg_10mi = WIN_S_Avg_10mi;
    }

    public float getWIN_D_S_Max() {
        return WIN_D_S_Max;
    }

    public void setWIN_D_S_Max(float WIN_D_S_Max) {
        this.WIN_D_S_Max = WIN_D_S_Max;
    }

    public float getWIN_S_Max() {
        return WIN_S_Max;
    }

    public void setWIN_S_Max(float WIN_S_Max) {
        this.WIN_S_Max = WIN_S_Max;
    }

    public Date getWIN_S_Max_OTime() {
        return WIN_S_Max_OTime;
    }

    public void setWIN_S_Max_OTime(Date WIN_S_Max_OTime) {
        this.WIN_S_Max_OTime = WIN_S_Max_OTime;
    }

    public float getWIN_D_INST() {
        return WIN_D_INST;
    }

    public void setWIN_D_INST(float WIN_D_INST) {
        this.WIN_D_INST = WIN_D_INST;
    }

    public float getWIN_S_INST() {
        return WIN_S_INST;
    }

    public void setWIN_S_INST(float WIN_S_INST) {
        this.WIN_S_INST = WIN_S_INST;
    }

    public float getWIN_D_INST_Max() {
        return WIN_D_INST_Max;
    }

    public void setWIN_D_INST_Max(float WIN_D_INST_Max) {
        this.WIN_D_INST_Max = WIN_D_INST_Max;
    }

    public float getWIN_S_Inst_Max() {
        return WIN_S_Inst_Max;
    }

    public void setWIN_S_Inst_Max(float WIN_S_Inst_Max) {
        this.WIN_S_Inst_Max = WIN_S_Inst_Max;
    }

    public Date getWIN_S_INST_Max_OTime() {
        return WIN_S_INST_Max_OTime;
    }

    public void setWIN_S_INST_Max_OTime(Date WIN_S_INST_Max_OTime) {
        this.WIN_S_INST_Max_OTime = WIN_S_INST_Max_OTime;
    }

    private float WIN_S_Inst_Max;
    private Date WIN_S_INST_Max_OTime;

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
