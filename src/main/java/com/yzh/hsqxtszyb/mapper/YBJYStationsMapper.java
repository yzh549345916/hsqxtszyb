package com.yzh.hsqxtszyb.mapper;
import java.util.Collection;

import com.yzh.hsqxtszyb.model.huanbao.YBJYStations;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YBJYStationsMapper {
    List<YBJYStations> selectAllByYbname(@Param("ybname")String ybname);
    List<YBJYStations> selectAllByYbnameAndStationLevlBetweenEqual(@Param("ybname")String ybname,@Param("minStationLevl")Integer minStationLevl,@Param("maxStationLevl")Integer maxStationLevl);
    List<YBJYStations> selectAllByYbnameAndStationLevlIn(@Param("ybname")String ybname,@Param("stationLevlCollection")Collection<Integer> stationLevlCollection);
    List<YBJYStations> selectAllByYbnameAndStationLevlInAndAdminCodeReg(@Param("ybname")String ybname,@Param("stationLevlCollection")Collection<Integer> stationLevlCollection,@Param("AdminCodeReg")String AdminCodeReg);
}