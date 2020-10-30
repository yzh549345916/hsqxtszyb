package com.yzh.hsqxtszyb.model;

public class 数值预报方法多要素查询Model {
    private String name;
    private String title;
    private String TemYbName="温度预报";
    private String TmaxYbName="最高气温预报";
    private String TminYbName="最低气温预报";
    private String RHUYbName="相对湿度预报";
    private String Wind1YbName="10米风速预报";
    private String Wind2YbName="10米风向预报";
    private String TemSkName="温度实况";
    private String TmaxSkName="最高气温实况";
    private String TminSkName="最低气温实况";
    private String RHUSkName="相对湿度实况";
    private String Wind1SkName="10米风速实况";
    private String Wind2SkName="10米风向实况";
    private double[][] TemYbValue;
    private double[][] TmaxYbValue;
    private double[][] TminYbValue;
    private double[][] RHUYbValue;
    private double[][] Wind1YbValue;
    private Object[][] Wind2YbValue;
    private double[][] TemSkValue;
    private double[][] TmaxSkValue;
    private double[][] TminSkValue;
    private double[][] RHUSkValue;
    private double[][] Wind1SkValue;
    private Object[][] Wind2SkValue;
    public 数值预报方法多要素查询Model(String name, String title, String temYbName, String tmaxYbName, String tminYbName, String RHUYbName, String wind1YbName, String wind2YbName, String temSkName, String tmaxSkName, String tminSkName, String RHUSkName, String wind1SkName, String wind2SkName, double[][] temYbValue, double[][] tmaxYbValue, double[][] tminYbValue, double[][] RHUYbValue, double[][] wind1YbValue, Object[][] wind2YbValue, double[][] temSkValue, double[][] tmaxSkValue, double[][] tminSkValue, double[][] RHUSkValue, double[][] wind1SkValue) {
        this.name = name;
        this.title = title;
        TemYbName = temYbName;
        TmaxYbName = tmaxYbName;
        TminYbName = tminYbName;
        this.RHUYbName = RHUYbName;
        Wind1YbName = wind1YbName;
        Wind2YbName = wind2YbName;
        TemSkName = temSkName;
        TmaxSkName = tmaxSkName;
        TminSkName = tminSkName;
        this.RHUSkName = RHUSkName;
        Wind1SkName = wind1SkName;
        Wind2SkName = wind2SkName;
        TemYbValue = temYbValue;
        TmaxYbValue = tmaxYbValue;
        TminYbValue = tminYbValue;
        this.RHUYbValue = RHUYbValue;
        Wind1YbValue = wind1YbValue;
        Wind2YbValue = wind2YbValue;
        TemSkValue = temSkValue;
        TmaxSkValue = tmaxSkValue;
        TminSkValue = tminSkValue;
        this.RHUSkValue = RHUSkValue;
        Wind1SkValue = wind1SkValue;
    }

    public void setYB(double[][] temYbValue, double[][] tmaxYbValue, double[][] tminYbValue, double[][] RHUYbValue, double[][] wind1YbValue, Object[][] wind2YbValue){
        TemYbValue = temYbValue;
        TmaxYbValue = tmaxYbValue;
        TminYbValue = tminYbValue;
        this.RHUYbValue = RHUYbValue;
        Wind1YbValue = wind1YbValue;
        Wind2YbValue = wind2YbValue;
    }

    public void setSK(double[][] temSkValue, double[][] tmaxSkValue, double[][] tminSkValue, double[][] RHUSkValue, double[][] wind1SkValue){
        TemSkValue = temSkValue;
        TmaxSkValue = tmaxSkValue;
        TminSkValue = tminSkValue;
        this.RHUSkValue = RHUSkValue;
        Wind1SkValue = wind1SkValue;
    }
    public 数值预报方法多要素查询Model() {
    }



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

    public String getTemYbName() {
        return TemYbName;
    }

    public void setTemYbName(String temYbName) {
        TemYbName = temYbName;
    }

    public String getTmaxYbName() {
        return TmaxYbName;
    }

    public void setTmaxYbName(String tmaxYbName) {
        TmaxYbName = tmaxYbName;
    }

    public String getTminYbName() {
        return TminYbName;
    }

    public void setTminYbName(String tminYbName) {
        TminYbName = tminYbName;
    }

    public String getRHUYbName() {
        return RHUYbName;
    }

    public void setRHUYbName(String RHUYbName) {
        this.RHUYbName = RHUYbName;
    }

    public String getWind1YbName() {
        return Wind1YbName;
    }

    public void setWind1YbName(String wind1YbName) {
        Wind1YbName = wind1YbName;
    }

    public String getWind2YbName() {
        return Wind2YbName;
    }

    public void setWind2YbName(String wind2YbName) {
        Wind2YbName = wind2YbName;
    }

    public String getTemSkName() {
        return TemSkName;
    }

    public void setTemSkName(String temSkName) {
        TemSkName = temSkName;
    }

    public String getTmaxSkName() {
        return TmaxSkName;
    }

    public void setTmaxSkName(String tmaxSkName) {
        TmaxSkName = tmaxSkName;
    }

    public String getTminSkName() {
        return TminSkName;
    }

    public void setTminSkName(String tminSkName) {
        TminSkName = tminSkName;
    }

    public String getRHUSkName() {
        return RHUSkName;
    }

    public void setRHUSkName(String RHUSkName) {
        this.RHUSkName = RHUSkName;
    }

    public String getWind1SkName() {
        return Wind1SkName;
    }

    public void setWind1SkName(String wind1SkName) {
        Wind1SkName = wind1SkName;
    }

    public String getWind2SkName() {
        return Wind2SkName;
    }

    public void setWind2SkName(String wind2SkName) {
        Wind2SkName = wind2SkName;
    }

    public double[][] getTemYbValue() {
        return TemYbValue;
    }

    public void setTemYbValue(double[][] temYbValue) {
        TemYbValue = temYbValue;
    }

    public double[][] getTmaxYbValue() {
        return TmaxYbValue;
    }

    public void setTmaxYbValue(double[][] tmaxYbValue) {
        TmaxYbValue = tmaxYbValue;
    }

    public double[][] getTminYbValue() {
        return TminYbValue;
    }

    public void setTminYbValue(double[][] tminYbValue) {
        TminYbValue = tminYbValue;
    }

    public double[][] getRHUYbValue() {
        return RHUYbValue;
    }

    public void setRHUYbValue(double[][] RHUYbValue) {
        this.RHUYbValue = RHUYbValue;
    }

    public double[][] getWind1YbValue() {
        return Wind1YbValue;
    }

    public void setWind1YbValue(double[][] wind1YbValue) {
        Wind1YbValue = wind1YbValue;
    }

    public Object[][] getWind2YbValue() {
        return Wind2YbValue;
    }

    public void setWind2YbValue(Object[][] wind2YbValue) {
        Wind2YbValue = wind2YbValue;
    }

    public double[][] getTemSkValue() {
        return TemSkValue;
    }

    public void setTemSkValue(double[][] temSkValue) {
        TemSkValue = temSkValue;
    }

    public double[][] getTmaxSkValue() {
        return TmaxSkValue;
    }

    public void setTmaxSkValue(double[][] tmaxSkValue) {
        TmaxSkValue = tmaxSkValue;
    }

    public double[][] getTminSkValue() {
        return TminSkValue;
    }

    public void setTminSkValue(double[][] tminSkValue) {
        TminSkValue = tminSkValue;
    }

    public double[][] getRHUSkValue() {
        return RHUSkValue;
    }

    public void setRHUSkValue(double[][] RHUSkValue) {
        this.RHUSkValue = RHUSkValue;
    }

    public double[][] getWind1SkValue() {
        return Wind1SkValue;
    }

    public void setWind1SkValue(double[][] wind1SkValue) {
        Wind1SkValue = wind1SkValue;
    }

    public Object[][] getWind2SkValue() {
        return Wind2SkValue;
    }

    public void setWind2SkValue(Object[][] wind2SkValue) {
        Wind2SkValue = wind2SkValue;
    }







}
