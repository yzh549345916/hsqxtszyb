package com.yzh.hsqxtszyb.model;

public class 数值预报检索Model {
    private String ID;
    private String DateString;
    private String DataType;
    private int Purpose;
    private int Level;

    public 数值预报检索Model(String ID, String dateString, String dataType, int purpose, int level, int ybSx, String tableName) {
        this.ID = ID;
        DateString = dateString;
        DataType = dataType;
        Purpose = purpose;
        Level = level;
        YbSx = ybSx;
        TableName = tableName;
    }

    public int getYbSx() {
        return YbSx;
    }

    public void setYbSx(int ybSx) {
        YbSx = ybSx;
    }

    private int YbSx;
    private String TableName;

    public 数值预报检索Model(String ID, String dateString, String dataType, int ybSx, String tableName) {
        this.ID = ID;
        DateString = dateString;
        DataType = dataType;
        YbSx = ybSx;
        TableName = tableName;
    }

    public 数值预报检索Model(String ID, String dateString, String dataType, int purpose, int level, String tableName) {
        this.ID = ID;
        DateString = dateString;
        DataType = dataType;
        Purpose = purpose;
        Level = level;
        TableName = tableName;
    }

    public 数值预报检索Model(String ID, String dateString, String dataType) {
        this.ID = ID;
        DateString = dateString;
        DataType = dataType;
    }

    public int getPurpose() {
        return Purpose;
    }

    public void setPurpose(int purpose) {
        Purpose = purpose;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDateString() {
        return DateString;
    }

    public void setDateString(String dateString) {
        DateString = dateString;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }
}
