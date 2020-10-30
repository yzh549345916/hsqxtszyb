package com.yzh.hsqxtszyb.model;

import java.util.Date;

public class SK_RHU_Model {
    private Date DateTime;
    private String StationID;
    private float DPT;
    private float RHU;

    public float getDPT() {
        return DPT;
    }

    public void setDPT(float DPT) {
        this.DPT = DPT;
    }

    public float getRHU() {
        return RHU;
    }

    public void setRHU(float RHU) {
        this.RHU = RHU;
    }

    public float getVAP() {
        return VAP;
    }

    public void setVAP(float VAP) {
        this.VAP = VAP;
    }

    public float getRHU_Min() {
        return RHU_Min;
    }

    public void setRHU_Min(float RHU_Min) {
        this.RHU_Min = RHU_Min;
    }

    public Date getRHU_Min_OTIME() {
        return RHU_Min_OTIME;
    }

    public void setRHU_Min_OTIME(Date RHU_Min_OTIME) {
        this.RHU_Min_OTIME = RHU_Min_OTIME;
    }

    private float VAP;
    private float RHU_Min;
    private Date RHU_Min_OTIME;

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
