package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.EC.ECDataModel;
import com.yzh.hsqxtszyb.model.站点信息;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcSurfaceDao {
    public List<ECDataModel> getECSurface(@Param("TableName")String TableName,@Param("ID")String ID, @Param("DateString")String DateString, @Param("DataType") String DataType);
    public List<ECDataModel> getECSurfaceBySXIdsDataType(@Param("TableName")String TableName,@Param("ID")String ID, @Param("DateString")String DateString, @Param("DataType") String DataType, @Param("SX") int SX);
    public List<站点信息>  GetECStationByIdAndStationType(@Param("ID")String ID,@Param("StationLevlString")String StationLevlString);
}
