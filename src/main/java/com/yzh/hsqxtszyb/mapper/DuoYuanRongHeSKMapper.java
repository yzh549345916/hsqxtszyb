package com.yzh.hsqxtszyb.mapper;
import java.util.List;
import java.util.Collection;
import java.util.Date;

import com.yzh.hsqxtszyb.model.ZNWG.DuoYuanRongHeJoinSK1h;
import com.yzh.hsqxtszyb.model.预报检验.多元融合检验结果;
import org.apache.ibatis.annotations.Param;

public interface DuoYuanRongHeSKMapper {
    List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectTByMydatetimeBetweenEqualAndStationidInAndTIsNotNull(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);

    List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectTdByMydatetimeBetweenEqualAndStationidInAndTdIsNotNull(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);

    List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectRHByMydatetimeBetweenEqualAndStationidInAndRHIsNotNull(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);

    List<SelectYBJYSKAndStationIdCAndDatetimeResult> selectR1ByMydatetimeBetweenEqualAndStationidInAndR1IsNotNull(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);

    List<多元融合检验结果> selectTdByMydatetimeBetweenEqualAndStationidInJoinSK(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);
    List<多元融合检验结果> selectRHByMydatetimeBetweenEqualAndStationidInJoinSK(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);
    List<多元融合检验结果> selectTByMydatetimeBetweenEqualAndStationidInJoinSK(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);
    List<多元融合检验结果> selectR1ByMydatetimeBetweenEqualAndStationidInJoinSK(@Param("year") Integer year, @Param("minMydatetime") Date minMydatetime, @Param("maxMydatetime") Date maxMydatetime, @Param("stationidCollection") Collection<String> stationidCollection);
}