package com.yzh.hsqxtszyb.mapper.ZNWG;
import java.util.List;

import com.yzh.hsqxtszyb.model.ZNWG.GuoJiaZNWGGd001003;
import java.time.LocalDateTime;

import com.yzh.hsqxtszyb.model.ZNWG.入库格点经纬度范围;
import org.apache.ibatis.annotations.Param;

public interface GuoJiaZNWGGd001003Mapper {
    GuoJiaZNWGGd001003 selectByPrimaryKey(@Param("lonindex") Long lonindex, @Param("latindex") Long latindex, @Param("qbdatetime") LocalDateTime qbdatetime, @Param("ybsx") Integer ybsx);

    List<GuoJiaZNWGGd001003> selectAllByQbdatetimeAndYbsx(@Param("qbdatetime")LocalDateTime qbdatetime,@Param("ybsx")Integer ybsx);
    List<GuoJiaZNWGGd001003> selectAllByLonindexAndLatindexAndQbdatetimeAndWiu10IsNotNullOrderByYbsx(@Param("lonindex")Long lonindex,@Param("latindex")Long latindex,@Param("qbdatetime")LocalDateTime qbdatetime);


    入库格点经纬度范围 selectFirstGDFWByMSName(@Param("MSName")String MSName);
}