package com.yzh.hsqxtszyb.mapper;
import java.util.List;
import java.util.Collection;

import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface YBJYSkHourMapper {

   List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectTemByDatetimeBetweenEqualAndStationIdCInAndTemIsNotNullAndTemLessThan(@Param("year")Integer year, @Param("minDatetime")Date minDatetime, @Param("maxDatetime")Date maxDatetime, @Param("stationIdCCollection")Collection<String> stationIdCCollection);
   List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectDPTByDatetimeBetweenEqualAndStationIdCInAndDPTIsNotNull(@Param("year")Integer year, @Param("minDatetime")Date minDatetime, @Param("maxDatetime")Date maxDatetime, @Param("stationIdCCollection")Collection<String> stationIdCCollection);
   List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectRHUByDatetimeBetweenEqualAndStationIdCInAndRHUIsNotNull(@Param("year")Integer year, @Param("minDatetime")Date minDatetime, @Param("maxDatetime")Date maxDatetime, @Param("stationIdCCollection")Collection<String> stationIdCCollection);
   List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectPREByDatetimeBetweenEqualAndStationIdCInAndPREIsNotNull(@Param("year")Integer year, @Param("minDatetime")Date minDatetime, @Param("maxDatetime")Date maxDatetime, @Param("stationIdCCollection")Collection<String> stationIdCCollection);
}