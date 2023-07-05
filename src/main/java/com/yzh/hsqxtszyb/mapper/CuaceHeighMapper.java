package com.yzh.hsqxtszyb.mapper;
import java.util.List;

import com.yzh.hsqxtszyb.model.huanbao.CuaceHeigh;
import java.util.Date;

import com.yzh.hsqxtszyb.model.huanbao.站点信息;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CuaceHeighMapper {

    List<CuaceHeigh> findAllByDatetimeAndValidtimeAndFcstlevel(@Param("datetime")Date datetime,@Param("validtime")Integer validtime,@Param("fcstlevel")Integer fcstlevel);
    List<CuaceHeigh> findAllByStationidAndDatetimeAndFcstlevel(@Param("stationid")String stationid,@Param("datetime")Date datetime,@Param("fcstlevel")Integer fcstlevel);
    List<站点信息> GetStationsByType(@Param("Type") String Type);
    List<站点信息> GetStationsByTypeAndStationID(@Param("Type") String Type,@Param("stationid")String stationid);
}