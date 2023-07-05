package com.yzh.hsqxtszyb.model.ZNWG;

public class 入库格点经纬度范围 {
    private Double lonStart;
    private Double lonEnd;
    private Double lonStep;
    private Double latStart;
    private Double latEnd;
    private Double latStep;
    private String MSName;

    public 入库格点经纬度范围(Double lonStart, Double lonEnd, Double lonStep, Double latStart, Double latEnd, Double latStep, String MSName) {
        this.lonStart = lonStart;
        this.lonEnd = lonEnd;
        this.lonStep = lonStep;
        this.latStart = latStart;
        this.latEnd = latEnd;
        this.latStep = latStep;
        this.MSName = MSName;
    }

    public 入库格点经纬度范围() {
    }

    public Double getLonStart() {
        return lonStart;
    }

    public void setLonStart(Double lonStart) {
        this.lonStart = lonStart;
    }

    public Double getLonEnd() {
        return lonEnd;
    }

    public void setLonEnd(Double lonEnd) {
        this.lonEnd = lonEnd;
    }

    public Double getLonStep() {
        return lonStep;
    }

    public void setLonStep(Double lonStep) {
        this.lonStep = lonStep;
    }

    public Double getLatStart() {
        return latStart;
    }

    public void setLatStart(Double latStart) {
        this.latStart = latStart;
    }

    public Double getLatEnd() {
        return latEnd;
    }

    public void setLatEnd(Double latEnd) {
        this.latEnd = latEnd;
    }

    public Double getLatStep() {
        return latStep;
    }

    public void setLatStep(Double latStep) {
        this.latStep = latStep;
    }

    public String getMSName() {
        return MSName;
    }

    public void setMSName(String MSName) {
        this.MSName = MSName;
    }
}
