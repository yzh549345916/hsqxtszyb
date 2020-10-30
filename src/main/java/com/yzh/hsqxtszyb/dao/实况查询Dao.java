package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface 实况查询Dao extends BaseDao {
    public List<SK_Tem_Hour_Model> getSK(@Param("StationID")String StationID, @Param("StartTime") Date StartTime, @Param("EndTime") Date EndTime);
}
