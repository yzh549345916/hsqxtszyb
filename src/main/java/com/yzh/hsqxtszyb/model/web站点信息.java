package com.yzh.hsqxtszyb.model;

import java.util.List;

public class web站点信息 {
    private String ID;
    private String Name;
    private boolean HasChildren;

    public web站点信息(String ID) {
        this.ID = ID;
    }

    public web站点信息() {
    }

    public web站点信息(String ID, String name, boolean hasChildren) {
        this.ID = ID;
        Name = name;
        HasChildren = hasChildren;
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

    public boolean isHasChildren() {
        return HasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        HasChildren = hasChildren;
    }
    public 地区Model ConverTo地区(){
      return new 地区Model(this.ID,this.Name,this.HasChildren);
    }
}
