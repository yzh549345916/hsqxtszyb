package com.yzh.hsqxtszyb.model;

public class 地图站点详情Model {
    private 地图站点详情表头Model[] headers;
    private 地图站点详情数据内容[] datas;
    private String name;
    private String title;
    private String yb1Name;
    private String yb2Name;
    private String sk1Name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public 地图站点详情表头Model[] getHeaders() {
        return headers;
    }

    public void setHeaders(地图站点详情表头Model[] headers) {
        this.headers = headers;
    }

    public 地图站点详情数据内容[] getDatas() {
        return datas;
    }

    public void setDatas(地图站点详情数据内容[] datas) {
        this.datas = datas;
    }

    public 地图站点详情Model() {

    }
}
