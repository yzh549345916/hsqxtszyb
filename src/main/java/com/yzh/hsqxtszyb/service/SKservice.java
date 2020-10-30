package com.yzh.hsqxtszyb.service;

import cn.hutool.core.date.DateUtil;
import com.yzh.hsqxtszyb.cimiss.地面实况数据类型转换;
import com.yzh.hsqxtszyb.dao.StationDaoImpl;
import com.yzh.hsqxtszyb.dao.实况查询Dao;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SKservice {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlServerSessionFactory();
    SqlSession session = sqlSessionFactory.openSession();
    实况查询Dao skDao = session.getMapper(实况查询Dao.class);

    public List<SK_Tem_Hour_Model> 根据站点起止时间获取温度() {
        String dateStr = "2020-10-01";
        Date sdate=DateUtil.parse(dateStr);
        dateStr = "2020-10-03";
        Date edate=DateUtil.parse(dateStr);
        List<SK_Tem_Hour_Model> mydata = skDao.getSK("53464",sdate,edate);
        return mydata;
    }







}
