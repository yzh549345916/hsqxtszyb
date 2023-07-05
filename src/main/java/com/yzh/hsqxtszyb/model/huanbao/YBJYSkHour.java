package com.yzh.hsqxtszyb.model.huanbao;

import java.util.Date;

public class YBJYSkHour {
    private String stationIdC;

    private Date datetime;

    private Double tem;

    private Double dpt;

    private Double rhu;

    private Double pre1h;

    private Double winDAvg10mi;

    private Double winSAvg10mi;

    private Double winDSMax;

    private Double winSMax;

    private Double winDInstMax;

    private Double winSInstMax;

    public String getStationIdC() {
        return stationIdC;
    }

    public void setStationIdC(String stationIdC) {
        this.stationIdC = stationIdC;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getTem() {
        return tem;
    }

    public void setTem(Double tem) {
        this.tem = tem;
    }

    public Double getDpt() {
        return dpt;
    }

    public void setDpt(Double dpt) {
        this.dpt = dpt;
    }

    public Double getRhu() {
        return rhu;
    }

    public void setRhu(Double rhu) {
        this.rhu = rhu;
    }

    public Double getPre1h() {
        return pre1h;
    }

    public void setPre1h(Double pre1h) {
        this.pre1h = pre1h;
    }

    public Double getWinDAvg10mi() {
        return winDAvg10mi;
    }

    public void setWinDAvg10mi(Double winDAvg10mi) {
        this.winDAvg10mi = winDAvg10mi;
    }

    public Double getWinSAvg10mi() {
        return winSAvg10mi;
    }

    public void setWinSAvg10mi(Double winSAvg10mi) {
        this.winSAvg10mi = winSAvg10mi;
    }

    public Double getWinDSMax() {
        return winDSMax;
    }

    public void setWinDSMax(Double winDSMax) {
        this.winDSMax = winDSMax;
    }

    public Double getWinSMax() {
        return winSMax;
    }

    public void setWinSMax(Double winSMax) {
        this.winSMax = winSMax;
    }

    public Double getWinDInstMax() {
        return winDInstMax;
    }

    public void setWinDInstMax(Double winDInstMax) {
        this.winDInstMax = winDInstMax;
    }

    public Double getWinSInstMax() {
        return winSInstMax;
    }

    public void setWinSInstMax(Double winSInstMax) {
        this.winSInstMax = winSInstMax;
    }
}