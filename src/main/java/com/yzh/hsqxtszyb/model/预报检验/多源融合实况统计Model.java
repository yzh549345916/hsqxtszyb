package com.yzh.hsqxtszyb.model.预报检验;

public class 多源融合实况统计Model {
    private String eleName;
    private String eleChineseName;
    //平均偏差
    private Double PJPC;
    //平均绝对误差
    private Double PJJDWC;
    private Integer Count;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public 多源融合实况统计Model(String eleName, String eleChineseName, Double PJPC, Double PJJDWC, Integer count, Double JFGWC, String JZInfo) {
        this.eleName = eleName;
        this.eleChineseName = eleChineseName;
        this.PJPC = PJPC;
        this.PJJDWC = PJJDWC;
        Count = count;
        this.JFGWC = JFGWC;
        this.JZInfo = JZInfo;
    }

    public 多源融合实况统计Model() {
    }

    public 多源融合实况统计Model(String eleName, String eleChineseName, Double PJJDWC, Integer count, Double JFGWC, String JZInfo) {
        this.eleName = eleName;
        this.eleChineseName = eleChineseName;
        this.PJJDWC = PJJDWC;
        Count = count;
        this.JFGWC = JFGWC;
        this.JZInfo = JZInfo;
    }

    //均方根误差
    private Double JFGWC;
    //极值信息
    private String JZInfo;

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    public String getEleChineseName() {
        return eleChineseName;
    }

    public void setEleChineseName(String eleChineseName) {
        this.eleChineseName = eleChineseName;
    }

    public Double getPJPC() {
        return PJPC;
    }

    public void setPJPC(Double PJPC) {
        this.PJPC = PJPC;
    }

    public Double getPJJDWC() {
        return PJJDWC;
    }

    public void setPJJDWC(Double PJJDWC) {
        this.PJJDWC = PJJDWC;
    }

    public Double getJFGWC() {
        return JFGWC;
    }

    public void setJFGWC(Double JFGWC) {
        this.JFGWC = JFGWC;
    }

    public String getJZInfo() {
        return JZInfo;
    }

    public void setJZInfo(String JZInfo) {
        this.JZInfo = JZInfo;
    }
}
