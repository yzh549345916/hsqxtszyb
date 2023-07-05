package com.yzh.hsqxtszyb.杨姐区局项目临时;

public class 杨姐区局项目临时数据Model {
    private Double lonStart;
    private Double lonStep;
    private Integer lonCount;
    private Double latStart;
    private Double latStep;
    private Integer latCount;
    private Double[] v1;

    public 杨姐区局项目临时数据Model(Double lonStart, Double lonStep, Integer lonCOunt, Double latStart, Double latStep, Integer latCOunt, Double[] v1, Double[] v2) {
        this.lonStart = lonStart;
        this.lonStep = lonStep;
        this.lonCount = lonCOunt;
        this.latStart = latStart;
        this.latStep = latStep;
        this.latCount = latCOunt;
        this.v1 = v1;
        this.v2 = v2;
    }

    public 杨姐区局项目临时数据Model() {
    }

    public Double getLonStart() {
        return lonStart;
    }

    public void setLonStart(Double lonStart) {
        this.lonStart = lonStart;
    }

    public Double getLonStep() {
        return lonStep;
    }

    public void setLonStep(Double lonStep) {
        this.lonStep = lonStep;
    }

    public Integer getLonCount() {
        return lonCount;
    }

    public void setLonCount(Integer lonCount) {
        this.lonCount = lonCount;
    }

    public Double getLatStart() {
        return latStart;
    }

    public void setLatStart(Double latStart) {
        this.latStart = latStart;
    }

    public Double getLatStep() {
        return latStep;
    }

    public void setLatStep(Double latStep) {
        this.latStep = latStep;
    }

    public Integer getLatCount() {
        return latCount;
    }

    public void setLatCount(Integer latCount) {
        this.latCount = latCount;
    }

    public Double[] getV1() {
        return v1;
    }

    public void setV1(Double[] v1) {
        this.v1 = v1;
    }

    public Double[] getV2() {
        return v2;
    }

    public void setV2(Double[] v2) {
        this.v2 = v2;
    }

    private Double[] v2;
}
