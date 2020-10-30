package com.yzh.hsqxtszyb.model;

public class IDNameModel {
    private String ID;

    public IDNameModel() {
    }

    public IDNameModel(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Name;


}
