package com.yzh.hsqxtszyb.service;

import cn.hutool.core.date.DateUtil;
import com.yzh.hsqxtszyb.dao.xzjxhDao;
import com.yzh.hsqxtszyb.model.SK_Tem_Hour_Model;
import com.yzh.hsqxtszyb.model.xzjxh.xzjxhStationModel;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

public class ZhiNengWangGeservice {
    SqlSessionFactory sqlSessionFactoryXzjxh = SqlSessionFactoryUtil.getXzjxhSessionFactory();
    SqlSession sessionXzjxh = sqlSessionFactoryXzjxh.openSession();
    xzjxhDao myxzjxhDao = sessionXzjxh.getMapper(xzjxhDao.class);

    public List<xzjxhStationModel> 获取社区站点() {
        return  myxzjxhDao.getSqjxhStations((short) 71);
    }
    public void 获取环保局智能网格数据(){
        List<xzjxhStationModel> stationLists=myxzjxhDao.getSqjxhStations((short) 71);
        StringBuffer idSb=new StringBuffer();
        for (xzjxhStationModel station:stationLists
             ) {
            idSb.append('\'').append(station.getGJStatioID()).append("',");
        }
        String idStr= idSb.substring(0,idSb.length()-1);
    }
    public void 根据站点起报时间获取国家智能网格预报(String stationIDs,Date qbDate,int sc){

    }

}
