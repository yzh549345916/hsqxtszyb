package com.yzh.hsqxtszyb.mapper;

import com.yzh.hsqxtszyb.model.huanbao.Jingjinji;
import java.util.Date;
import java.util.List;

import com.yzh.hsqxtszyb.model.huanbao.站点信息;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JingjinjiMapper {
    List<站点信息> GetStationsByType(@Param("Type") String Type);
    List<Jingjinji> findAllByDatetimeAndValidtimeAndFcstname(@Param("tableNameDate")String tableNameDate,@Param("datetime")Date datetime,@Param("validtime")Integer validtime,@Param("fcstname")String fcstname);
    List<Jingjinji> findAllByStationidAndDatetimeAndFcstname(@Param("tableNameDate")String tableNameDate,@Param("stationid")String stationid,@Param("datetime")Date datetime,@Param("fcstname")String fcstname);
    List<站点信息> GetStationsByTypeAndStationID(@Param("Type") String Type,@Param("stationid")String stationid);


}