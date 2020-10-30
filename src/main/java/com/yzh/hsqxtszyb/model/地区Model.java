package com.yzh.hsqxtszyb.model;
import java.util.List;

public class 地区Model  {
    private String ID;
    private String Name;
    private boolean HasChildren;
    List<地区Model> Children;

    public List<地区Model> getChildren() {
        return Children;
    }

    public void setChildren(List<地区Model> children) {
        Children = children;
    }

    public 地区Model() {
    }

    public 地区Model(String ID, String name, boolean hasChildren) {
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


}
