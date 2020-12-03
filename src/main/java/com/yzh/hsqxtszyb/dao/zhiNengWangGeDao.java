package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.ZNWG.GJZNWGDataModel;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface zhiNengWangGeDao extends BaseDao {
    public List<GJZNWGDataModel> getGjZNWG3h240(@Param("StatioID")String StatioID, @Param("Date") Date Date, @Param("SC")short SC, @Param("MinSX")short MinSX, @Param("MaxSX")short MaxSX);
}
