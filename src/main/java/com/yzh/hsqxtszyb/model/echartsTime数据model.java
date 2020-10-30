package com.yzh.hsqxtszyb.model;

public class echartsTime数据model {
    private long time;
    private double value;

    public echartsTime数据model(long time, double value) {
        this.time = time;
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
