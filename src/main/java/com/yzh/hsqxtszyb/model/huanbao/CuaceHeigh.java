package com.yzh.hsqxtszyb.model.huanbao;

import java.util.Date;

public class CuaceHeigh {
    private String stationid;

    private Date datetime;

    private Integer validtime;

    private Integer fcstlevel;

    private Double CONC_DUST;

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

    public Integer getFcstlevel() {
        return fcstlevel;
    }

    public void setFcstlevel(Integer fcstlevel) {
        this.fcstlevel = fcstlevel;
    }

    public Double getConcDust() {
        return CONC_DUST;
    }

    public void setConcDust(Double concDust) {
        this.CONC_DUST = concDust;
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
        sb.append(", fcstlevel=").append(fcstlevel);
        sb.append(", concDust=").append(CONC_DUST);
        sb.append("]");
        return sb.toString();
    }
}