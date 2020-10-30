package com.yzh.hsqxtszyb.model;

public class 数值预报查询Model {
    private String name;
    private String title;

    public 数值预报查询Model(String name, double[][] skValue1, double[][] ybValue1) {
        this.name = name;
        this.skValue1 = skValue1;
        this.ybValue1 = ybValue1;
    }

    private String yb1Name;
    private String yb2Name;
    private String sk1Name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYb1Name() {
        return yb1Name;
    }

    public void setYb1Name(String yb1Name) {
        this.yb1Name = yb1Name;
    }

    public String getYb2Name() {
        return yb2Name;
    }

    public void setYb2Name(String yb2Name) {
        this.yb2Name = yb2Name;
    }

    public String getSk1Name() {
        return sk1Name;
    }

    public void setSk1Name(String sk1Name) {
        this.sk1Name = sk1Name;
    }

    public 数值预报查询Model() {
    }

    public 数值预报查询Model(String name, double[][] skValue1, double[][] ybValue1, String[][] ybValue2) {
        this.name = name;
        this.skValue1 = skValue1;
        this.ybValue1 = ybValue1;
        this.ybValue2 = ybValue2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getSkValue1() {
        return skValue1;
    }

    public void setSkValue1(double[][] skValue1) {
        this.skValue1 = skValue1;
    }

    public double[][] getYbValue1() {
        return ybValue1;
    }

    public void setYbValue1(double[][] ybValue1) {
        this.ybValue1 = ybValue1;
    }

    public Object[][] getYbValue2() {
        return ybValue2;
    }

    public void setYbValue2(Object[][] ybValue2) {
        this.ybValue2 = ybValue2;
    }

    private  double[][] skValue1;
    private  double[][] ybValue1;
    private  Object[][] ybValue2;





}
