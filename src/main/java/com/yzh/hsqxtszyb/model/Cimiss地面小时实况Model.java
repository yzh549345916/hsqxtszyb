package com.yzh.hsqxtszyb.model;

import java.util.Date;

public class Cimiss地面小时实况Model {
    private String Station_Id_C;

    public Cimiss地面小时实况Model(String station_Id_C, Date datetime, double PRE, double TEM, double TEM_Max, double TEM_Min, double WIN_D_Avg_10mi, double WIN_S_Avg_10mi, double RHU, double PRS) {
        Station_Id_C = station_Id_C;
        Datetime = datetime;
        this.PRE = PRE;
        this.TEM = TEM;
        this.TEM_Max = TEM_Max;
        this.TEM_Min = TEM_Min;
        this.WIN_D_Avg_10mi = WIN_D_Avg_10mi;
        this.WIN_S_Avg_10mi = WIN_S_Avg_10mi;
        this.RHU = RHU;
        this.PRS = PRS;
    }

    public Cimiss地面小时实况Model() {
    }

    public String getStation_Id_C() {
        return Station_Id_C;
    }

    public void setStation_Id_C(String station_Id_C) {
        Station_Id_C = station_Id_C;
    }

    public Date getDatetime() {
        return Datetime;
    }

    public void setDatetime(Date datetime) {
        Datetime = datetime;
    }

    public double getPRE() {
        return PRE;
    }

    public void setPRE(double PRE) {
        this.PRE = PRE;
    }

    public double getTEM() {
        return TEM;
    }

    public void setTEM(double TEM) {
        this.TEM = TEM;
    }

    public double getTEM_Max() {
        return TEM_Max;
    }

    public void setTEM_Max(double TEM_Max) {
        this.TEM_Max = TEM_Max;
    }

    public double getTEM_Min() {
        return TEM_Min;
    }

    public void setTEM_Min(double TEM_Min) {
        this.TEM_Min = TEM_Min;
    }

    public double getWIN_D_Avg_10mi() {
        return WIN_D_Avg_10mi;
    }

    public void setWIN_D_Avg_10mi(double WIN_D_Avg_10mi) {
        this.WIN_D_Avg_10mi = WIN_D_Avg_10mi;
    }

    public double getWIN_S_Avg_10mi() {
        return WIN_S_Avg_10mi;
    }

    public void setWIN_S_Avg_10mi(double WIN_S_Avg_10mi) {
        this.WIN_S_Avg_10mi = WIN_S_Avg_10mi;
    }

    public double getRHU() {
        return RHU;
    }

    public void setRHU(double RHU) {
        this.RHU = RHU;
    }

    public double getPRS() {
        return PRS;
    }

    public void setPRS(double PRS) {
        this.PRS = PRS;
    }

    private Date Datetime;
    private double PRE=-999999;
    private double TEM=-999999;
    private double TEM_Max=-999999;
    private double TEM_Min=-999999;
    private double WIN_D_Avg_10mi=-999999;
    private double WIN_S_Avg_10mi=-999999;
    private double RHU=-999999;
    private double PRS=-999999;
}
