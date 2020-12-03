package com.yzh.hsqxtszyb.model.ZNWG;

public class 国家智能网格查询Model {
    private String name;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private String title;
    private  double[][] tem;
    private  double[][] vis;
    private  double[][] rhu;
    private  double[][] pre;
    private  Object[][] wind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getTem() {
        return tem;
    }

    public void setTem(double[][] tem) {
        this.tem = tem;
    }

    public double[][] getVis() {
        return vis;
    }

    public void setVis(double[][] vis) {
        this.vis = vis;
    }

    public double[][] getRhu() {
        return rhu;
    }

    public void setRhu(double[][] rhu) {
        this.rhu = rhu;
    }

    public double[][] getPre() {
        return pre;
    }

    public void setPre(double[][] pre) {
        this.pre = pre;
    }

    public Object[][] getWind() {
        return wind;
    }

    public void setWind(Object[][] wind) {
        this.wind = wind;
    }
}
