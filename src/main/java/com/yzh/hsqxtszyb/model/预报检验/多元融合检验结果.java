package com.yzh.hsqxtszyb.model.预报检验;

import java.util.Date;

public class 多元融合检验结果 {
    private Double DYSKValue;
    private Double StationSKValue;
    /**
     * @Author YZH
    * @Description //多元融合实况-站点实况
     * @Date 15:26 2022/3/21
     * @Param
    * @return
    **/
    private Double CZValue;

    public 多元融合检验结果(String stationIdC, Date datetime,Double DYSKValue, Double stationSKValue, Double CZValue) {
        this.DYSKValue = DYSKValue;
        StationSKValue = stationSKValue;
        this.CZValue = CZValue;
        this.stationIdC = stationIdC;
        this.datetime = datetime;
    }

    public 多元融合检验结果() {
    }

    public Double getDYSKValue() {
        return DYSKValue;
    }

    public void setDYSKValue(Double DYSKValue) {
        this.DYSKValue = DYSKValue;
    }

    public Double getStationSKValue() {
        return StationSKValue;
    }

    public void setStationSKValue(Double stationSKValue) {
        StationSKValue = stationSKValue;
    }

    public Double getCZValue() {
        return CZValue;
    }

    public void setCZValue(Double CZValue) {
        this.CZValue = CZValue;
    }

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

    private String stationIdC;
    private Date datetime;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    private String stationName;
}
