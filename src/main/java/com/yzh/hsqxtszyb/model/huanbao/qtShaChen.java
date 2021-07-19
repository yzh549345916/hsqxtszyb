package com.yzh.hsqxtszyb.model.huanbao;

import java.util.Date;

public class qtShaChen {
    private String stationid;

    private Date datetime;

    private Integer validtime;

    private String fcstname;

    private Double fcstlevel;

    private Double fcstvalue;

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid == null ? null : stationid.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getValidtime() {
        return validtime;
    }

    public void setValidtime(Integer validtime) {
        this.validtime = validtime;
    }

    public String getFcstname() {
        return fcstname;
    }

    public void setFcstname(String fcstname) {
        this.fcstname = fcstname == null ? null : fcstname.trim();
    }

    public Double getFcstlevel() {
        return fcstlevel;
    }

    public void setFcstlevel(Double fcstlevel) {
        this.fcstlevel = fcstlevel;
    }

    public Double getFcstvalue() {
        return fcstvalue;
    }

    public void setFcstvalue(Double fcstvalue) {
        this.fcstvalue = fcstvalue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stationid=").append(stationid);
        sb.append(", datetime=").append(datetime);
        sb.append(", validtime=").append(validtime);
        sb.append(", fcstname=").append(fcstname);
        sb.append(", fcstlevel=").append(fcstlevel);
        sb.append(", fcstvalue=").append(fcstvalue);
        sb.append("]");
        return sb.toString();
    }
}