package com.yzh.hsqxtszyb.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.dao.xzjxhDao;
import com.yzh.hsqxtszyb.dao.zhiNengWangGeDao;
import com.yzh.hsqxtszyb.model.ZNWG.GJZNWGDataModel;
import com.yzh.hsqxtszyb.model.ZNWG.国家智能网格查询Model;
import com.yzh.hsqxtszyb.model.xzjxh.xzjxhStationModel;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ZhiNengWangGeservice {
    SqlSessionFactory sqlSessionFactoryXzjxh = SqlSessionFactoryUtil.getXzjxhSessionFactory();
    SqlSession sessionXzjxh = sqlSessionFactoryXzjxh.openSession();
    xzjxhDao myxzjxhDao = sessionXzjxh.getMapper(xzjxhDao.class);
    SqlSessionFactory sqlSessionFactoryZnwg = SqlSessionFactoryUtil.getZNWGSessionFactory();
    SqlSession sessionZnwg = sqlSessionFactoryZnwg.openSession();
    zhiNengWangGeDao myZnwgDao = sessionZnwg.getMapper(zhiNengWangGeDao.class);

    public List<国家智能网格查询Model> 获取环保局智能网格数据(long ybDateTimespan, long qbDateTimespan) {
        List<国家智能网格查询Model> cxDatas = new ArrayList<>();

        int timeSpHour = (int) ((ybDateTimespan - qbDateTimespan) / 1000 / 60 / 60);
        if (timeSpHour >= 0 && timeSpHour <= 72) {
            List<GJZNWGDataModel> dataLists = new ArrayList<>();
            List<xzjxhStationModel> stationLists = myxzjxhDao.getSqjxhStations((short) 71);
            StringBuffer idSb = new StringBuffer();
            for (xzjxhStationModel station : stationLists
            ) {
                idSb.append('\'').append(station.getGJStatioID()).append("',");
            }
            String StatioID = idSb.substring(0, idSb.length() - 1);
            short MinSX = (short) (timeSpHour + 0), MaxSX = (short) (timeSpHour + 168);
            DateTime qbDate = DateUtil.date(qbDateTimespan);
            dataLists = myZnwgDao.getGjZNWG3h240(StatioID, qbDate.toSqlDate(), (short) qbDate.hour(true), MinSX, MaxSX);
            if (dataLists.size() > 0) {
                for (GJZNWGDataModel item : dataLists
                ) {
                    item.setDateTimespan(DateUtil.offsetHour(DateUtil.date(item.getDate()), item.getSC() + item.getSX()).getTime());
                }
            }
            for (xzjxhStationModel station : stationLists
            ) {
                国家智能网格查询Model mydata = new 国家智能网格查询Model();
                mydata.setName(station.getName());
                String myID = station.getGJStatioID();
                List<GJZNWGDataModel> dataListsLs = dataLists.stream().filter(y -> y.getStatioID().equals(myID)).collect(Collectors.toList());
                double[][] tem = new double[dataListsLs.size()][2], vis = new double[dataListsLs.size()][2], rhu = new double[dataListsLs.size()][2], pre = new double[dataListsLs.size()][2];
                Object[][] wind = new Object[dataListsLs.size()][3];
                for (int i = 0; i < dataListsLs.size(); i++) {
                    GJZNWGDataModel item = dataListsLs.get(i);
                    tem[i][0] = item.getDateTimespan();
                    vis[i][0] = item.getDateTimespan();
                    rhu[i][0] = item.getDateTimespan();
                    pre[i][0] = item.getDateTimespan();
                    wind[i][0] = item.getDateTimespan();
                    tem[i][1] = NumberUtil.round(item.getTEM(), 1).doubleValue();
                    vis[i][1] = NumberUtil.round(item.getVIS(), 1).doubleValue();
                    rhu[i][1] = NumberUtil.round(item.getERH(), 1).doubleValue();
                    pre[i][1] = NumberUtil.round(item.getPRE_3H(), 1).doubleValue();
                    float winU = item.getWIU10();
                    float winV = item.getWIV10();
                    wind[i][1] = NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue();
                    wind[i][2] = 风向风速转换.GetFX(winV, winU);
                }
                mydata.setTem(tem);
                mydata.setPre(pre);
                mydata.setRhu(rhu);
                mydata.setVis(vis);
                mydata.setWind(wind);
                cxDatas.add(mydata);
            }
        }
        return cxDatas;
    }


}
