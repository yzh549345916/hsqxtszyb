package com.yzh.hsqxtszyb.model;

public class 地图站点详情表头Model {
    private boolean filterable;//是否过滤
    private String align;//'start' | 'center' | 'end'
    private String value;//值名称
    private String text;//标题名称
    private boolean sortable;//排序
    private boolean groupable;//分组
    private boolean divider;//分割线
    private double width;

    public 地图站点详情表头Model(boolean filterable, String align, String value, String text, boolean sortable, boolean groupable, boolean divider, double width) {
        this.filterable = filterable;
        this.align = align;
        this.value = value;
        this.text = text;
        this.sortable = sortable;
        this.groupable = groupable;
        this.divider = divider;
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public 地图站点详情表头Model() {
    }

    public 地图站点详情表头Model(boolean filterable, String align, String value, String text, boolean sortable, boolean groupable, boolean divider) {
        this.filterable = filterable;
        this.align = align;
        this.value = value;
        this.text = text;
        this.sortable = sortable;
        this.groupable = groupable;
        this.divider = divider;
    }

    public boolean isFilterable() {
        return filterable;
    }

    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public boolean isGroupable() {
        return groupable;
    }

    public void setGroupable(boolean groupable) {
        this.groupable = groupable;
    }

    public boolean isDivider() {
        return divider;
    }

    public void setDivider(boolean divider) {
        this.divider = divider;
    }
}
