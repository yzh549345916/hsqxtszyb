package com.yzh.hsqxtszyb.model.huanbao;

import java.util.Date;

public class CuaceSurface {
    private String stationID;

    private Date Datetime;

    private Integer validTime;

    private Double AOD550_DUST;

    private Double DDEPO_DUST;

    private Double DFLUX_DUST;

    private Double LOAD_DUST;

    private Double SCONC_DUST;

    private Double U10;

    private Double V10;

    private Double WDEPO_DUST;

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public Date getDatetime() {
        return Datetime;
    }

    public void setDatetime(Date datetime) {
        Datetime = datetime;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public Double getAOD550_DUST() {
        return AOD550_DUST;
    }

    public void setAOD550_DUST(Double AOD550_DUST) {
        this.AOD550_DUST = AOD550_DUST;
    }

    public Double getDDEPO_DUST() {
        return DDEPO_DUST;
    }

    public void setDDEPO_DUST(Double DDEPO_DUST) {
        this.DDEPO_DUST = DDEPO_DUST;
    }

    public Double getDFLUX_DUST() {
        return DFLUX_DUST;
    }

    public void setDFLUX_DUST(Double DFLUX_DUST) {
        this.DFLUX_DUST = DFLUX_DUST;
    }

    public Double getLOAD_DUST() {
        return LOAD_DUST;
    }

    public void setLOAD_DUST(Double LOAD_DUST) {
        this.LOAD_DUST = LOAD_DUST;
    }

    public Double getSCONC_DUST() {
        return SCONC_DUST;
    }

    public void setSCONC_DUST(Double SCONC_DUST) {
        this.SCONC_DUST = SCONC_DUST;
    }

    public Double getU10() {
        return U10;
    }

    public void setU10(Double u10) {
        U10 = u10;
    }

    public Double getV10() {
        return V10;
    }

    public void setV10(Double v10) {
        V10 = v10;
    }

    public Double getWDEPO_DUST() {
        return WDEPO_DUST;
    }

    public void setWDEPO_DUST(Double WDEPO_DUST) {
        this.WDEPO_DUST = WDEPO_DUST;
    }
}