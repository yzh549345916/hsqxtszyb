package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.xzjxh.xzjxhStationModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface xzjxhDao extends BaseDao {
    public List<xzjxhStationModel> getSqjxhStations(@Param("Station_levl")short Station_levl);
}
