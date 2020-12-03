package com.yzh.hsqxtszyb.model.ZNWG;

import java.util.Date;

public class GJZNWGDataModel {
    private String StatioID;
    private Date Date;
    private short SC;
    private short SX;
    private float TEM=-99999;
    private float ERH=-99999;
    private float WIV10=-99999;
    private float WIU10=-99999;
    private float PRE_3H=-99999;
    private float PPH=-99999;
    private float VIS=-99999;
    private long DateTimespan;

    public long getDateTimespan() {
        return DateTimespan;
    }

    public void setDateTimespan(long dateTimespan) {
        DateTimespan = dateTimespan;
    }

    public GJZNWGDataModel() {
    }

    public GJZNWGDataModel(String statioID, java.util.Date date, short SC, short SX, float TEM, float ERH, float WIV10, float WIU10, float PRE_3H, float PPH, float VIS, float ECT) {
        StatioID = statioID;
        Date = date;
        this.SC = SC;
        this.SX = SX;
        this.TEM = TEM;
        this.ERH = ERH;
        this.WIV10 = WIV10;
        this.WIU10 = WIU10;
        this.PRE_3H = PRE_3H;
        this.PPH = PPH;
        this.VIS = VIS;
        this.ECT = ECT;
    }

    public String getStatioID() {
        return StatioID;
    }

    public void setStatioID(String statioID) {
        StatioID = statioID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public short getSC() {
        return SC;
    }

    public void setSC(short SC) {
        this.SC = SC;
    }

    public short getSX() {
        return SX;
    }

    public void setSX(short SX) {
        this.SX = SX;
    }

    public float getTEM() {
        return TEM;
    }

    public void setTEM(float TEM) {
        this.TEM = TEM;
    }

    public float getERH() {
        return ERH;
    }

    public void setERH(float ERH) {
        this.ERH = ERH;
    }

    public float getWIV10() {
        return WIV10;
    }

    public void setWIV10(float WIV10) {
        this.WIV10 = WIV10;
    }

    public float getWIU10() {
        return WIU10;
    }

    public void setWIU10(float WIU10) {
        this.WIU10 = WIU10;
    }

    public float getPRE_3H() {
        return PRE_3H;
    }

    public void setPRE_3H(float PRE_3H) {
        this.PRE_3H = PRE_3H;
    }

    public float getPPH() {
        return PPH;
    }

    public void setPPH(float PPH) {
        this.PPH = PPH;
    }

    public float getVIS() {
        return VIS;
    }

    public void setVIS(float VIS) {
        this.VIS = VIS;
    }

    public float getECT() {
        return ECT;
    }

    public void setECT(float ECT) {
        this.ECT = ECT;
    }

    private float ECT;
}
