package com.yzh.hsqxtszyb.model;

import cn.hutool.core.date.DateTime;

public class 折线显示Model {
    private double value;

    public 折线显示Model(double value, Object value2, long times) {
        this.value = value;
        this.value2 = value2;
        this.times = times;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    private Object value2;
    public 折线显示Model(double value, long times) {
        this.value = value;
        this.times = times;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    private long times;
}
