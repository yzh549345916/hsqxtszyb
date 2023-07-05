package com.yzh.hsqxtszyb.mapper;

import com.yzh.hsqxtszyb.model.huanbao.CuaceSurface;
import java.util.Date;
import java.util.List;

import com.yzh.hsqxtszyb.model.huanbao.站点信息;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CuaceSurfaceMapper {
    List<站点信息> GetStationsByType(@Param("Type") String Type);
    List<CuaceSurface> findAllByDatetimeAndValidtime(@Param("datetime")Date datetime,@Param("validtime")Integer validtime);
    List<CuaceSurface> findAllByStationidAndDatetime(@Param("stationid")String stationid,@Param("datetime")Date datetime);
    List<站点信息> GetStationsByTypeAndStationID(@Param("Type") String Type,@Param("stationid")String stationid);
}