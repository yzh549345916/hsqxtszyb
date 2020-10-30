package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StationDao {
    public List<区台格点数值预报站点Model> 获取区台格点数值预报站点预报(数值预报检索Model data);
    public List<区台格点数值预报站点Model> select_Szyb_GD_ZD_StationId_date(@Param("ID")String ID,@Param("DateString")String DateString, @Param("DataType") String DataType);

    public List<web站点信息> 根据省份ID获取盟市(web站点信息 data);
    public List<web站点信息> 根据盟市ID获取旗县区(web站点信息 data);
    public int 根据地区信息获取站点个数(web站点信息 data);
    public List<站点信息> 根据地区信息获取站点(web站点信息 data);
}
