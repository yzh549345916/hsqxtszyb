package com.yzh.hsqxtszyb.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import com.yzh.hsqxtszyb.cimiss.地面实况数据类型转换;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.mapper.CuaceHeighMapper;
import com.yzh.hsqxtszyb.mapper.CuaceSurfaceMapper;
import com.yzh.hsqxtszyb.mapper.qtShaChenMapper;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.model.EC.ECDataModel;
import com.yzh.hsqxtszyb.model.huanbao.*;
import com.yzh.hsqxtszyb.model.huanbao.站点信息;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

public class ShaChenService {
    public OpenlayersQJMethodBaseModel 获取沙尘站点数据( long times,int YbSx, String YBType, String DataTypeStr, Boolean IsHeigh, float Fcstlevel){
        OpenlayersQJMethodBaseModel myData = new OpenlayersQJMethodBaseModel("FeatureCollection");
        DateTime myDate = DateUtil.date(times);
        if(YBType.equals("亚洲沙尘模式")){
            OpenlayersQJMethodModel[] myFeas=亚洲沙尘模式站点数据处理(myDate,YbSx,DataTypeStr,IsHeigh,Fcstlevel);
            if(myFeas!=null){
                myData.setFeatures(myFeas);
            }

        }else if(YBType.equals("区台沙尘模式")){
            OpenlayersQJMethodModel[] myFeas=区台沙尘模式站点数据处理(myDate,YbSx,DataTypeStr,IsHeigh,Fcstlevel);
            if(myFeas!=null){
                myData.setFeatures(myFeas);
            }
        }
        return myData;
    }
    public OpenlayersQJMethodModel[] 亚洲沙尘模式站点数据处理(Date myDate, int YbSx, String DataTypeStr, Boolean IsHeigh, float Fcstlevel){
        String units= 基本处理.亚洲沙尘模式单位获取(DataTypeStr);
        String chineseName=基本处理.亚洲沙尘模式中文名称(DataTypeStr);
        if(IsHeigh){
            SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
            SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession(true);
            CuaceHeighMapper huanJingDao = sessionHuanJing.getMapper(CuaceHeighMapper.class);
            List<站点信息> stations=huanJingDao.GetStationsByType("shachen");
            if(stations.size()>0){
                List<CuaceHeigh> myDataLs= huanJingDao.findAllByDatetimeAndValidtimeAndFcstlevel(myDate,YbSx,(int)Fcstlevel);
                if(!myDataLs.isEmpty()){
                    OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[myDataLs.size()];
                    for(int i=0; i < myDataLs.size(); i++){
                        try{
                            CuaceHeigh datals = myDataLs.get(i);
                            站点信息 station = stations.stream().filter(y -> y.getID().equals(datals.getStationid())).findFirst().get();
                            double ybvalue = Convert.toDouble(ReflectUtil.getFieldValue(datals, DataTypeStr), (double) -999999);
                            myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getStation_levl(), ybvalue, chineseName, units));
                        } catch (Exception e) {

                        }
                    }
                    return myFeas;
                }
            }
            sessionHuanJing.close();
        }else{
            SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
            SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession(true);
            CuaceSurfaceMapper huanJingDao = sessionHuanJing.getMapper(CuaceSurfaceMapper.class);
            List<站点信息> stations=huanJingDao.GetStationsByType("shachen");
            if(stations.size()>0){
                List<CuaceSurface> myDataLs= huanJingDao.findAllByDatetimeAndValidtime(myDate,YbSx);
                if(!myDataLs.isEmpty()){
                    亚洲沙尘模式数据调整(myDataLs);
                    OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[myDataLs.size()];
                    for(int i=0; i < myDataLs.size(); i++){
                        try{
                            CuaceSurface datals = myDataLs.get(i);
                            站点信息 station = stations.stream().filter(y -> y.getID().equals(datals.getStationID())).findFirst().get();
                            double ybvalue = Convert.toDouble(ReflectUtil.getFieldValue(datals, DataTypeStr), (double) -999999);
                            myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getStation_levl(), ybvalue, chineseName, units));
                        } catch (Exception e) {

                        }
                    }
                    return myFeas;
                }
            }
            sessionHuanJing.close();
        }
        return null;
    }
    public OpenlayersQJMethodModel[] 区台沙尘模式站点数据处理(Date myDate, int YbSx, String DataTypeStr, Boolean IsHeigh, float Fcstlevel){
        String units= 基本处理.区台沙尘模式单位获取(DataTypeStr);
        String chineseName=基本处理.区台沙尘模式中文名称(DataTypeStr);
        if("PM25".equals(DataTypeStr)){
            DataTypeStr="PM2.5";
        }
        SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
        SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession(true);
        qtShaChenMapper huanJingDao = sessionHuanJing.getMapper(qtShaChenMapper.class);
        List<站点信息> stations=huanJingDao.GetStationsByType("shachen");
        if(stations.size()>0){
            List<qtShaChen> myDataLs= huanJingDao.findAllByDatetimeAndValidtimeAndFcstlevelAndFcstname(DateUtil.format(myDate, "yyyyMM"),myDate,YbSx,1000.0,DataTypeStr);
            if(!myDataLs.isEmpty()){
                OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[myDataLs.size()];
                for(int i=0; i < myDataLs.size(); i++){
                    try{
                        qtShaChen datals = myDataLs.get(i);
                        站点信息 station = stations.stream().filter(y -> y.getID().equals(datals.getStationid())).findFirst().get();
                        double ybvalue = datals.getFcstvalue();
                        myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getStation_levl(), ybvalue, chineseName, units));
                    } catch (Exception e) {

                    }
                }
                return myFeas;
            }
        }
        sessionHuanJing.close();
        return null;
    }
    private void 亚洲沙尘模式数据调整(List<CuaceSurface> myDataLs){
        //DDEPO_DUST、LOAD_DUST、WDEPO_DUST单位由kg/m2调整为ug/m2
        for(CuaceSurface item:myDataLs){
            item.setDDEPO_DUST(item.getDDEPO_DUST()*1000000000);
            item.setLOAD_DUST(item.getLOAD_DUST()*1000000000);
            item.setWDEPO_DUST(item.getWDEPO_DUST()*1000000000);
        }
    }

    public 地图站点详情Model 获取站点预报详情( long times, String StationID,String YBType, String DataTypeStr, Boolean IsHeigh, float Fcstlevel) {
        try {
            DateTime sdate = DateUtil.date(times);
           if (YBType.equals("亚洲沙尘模式")) {
                地图站点详情Model myData =亚洲沙尘模式站点详情数据处理(sdate,StationID,DataTypeStr,IsHeigh,Fcstlevel);
                return myData;
            }else if (YBType.equals("区台沙尘模式")) {
               地图站点详情Model myData =区台沙尘模式站点详情数据处理(sdate,StationID,DataTypeStr,IsHeigh,Fcstlevel);
               return myData;
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public 地图站点详情Model 亚洲沙尘模式站点详情数据处理(Date myDate, String StationID,  String DataTypeStr, Boolean IsHeigh, float Fcstlevel){
        String units= 基本处理.亚洲沙尘模式单位获取(DataTypeStr);
        String chineseName=基本处理.亚洲沙尘模式中文名称(DataTypeStr);
        if(IsHeigh){
            SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
            SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession();
            CuaceHeighMapper huanJingDao = sessionHuanJing.getMapper(CuaceHeighMapper.class);
            List<CuaceHeigh> myDataLs= huanJingDao.findAllByStationidAndDatetimeAndFcstlevel(StationID,myDate,(int)Fcstlevel);
            if(!myDataLs.isEmpty()){
                地图站点详情Model myData = new 地图站点详情Model();
                myData.setTitle(DateUtil.format(myDate, "亚洲沙尘模式MM月dd日HH时起报") +  chineseName +Fcstlevel+ "hPa预报");
                myData.setYb1Name(chineseName);
                地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[myDataLs.size()];
                地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
                headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",chineseName+"("+units+")",true,false,true);
                List<站点信息> myidList=huanJingDao.GetStationsByTypeAndStationID("shachen",StationID);
                String stationName="";
                if(myidList.size()>0){
                    stationName=myidList.get(0).getName();
                }
                for (int i = 0; i < myDataLs.size(); i++) {
                    CuaceHeigh ybls = myDataLs.get(i);
                    xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, DataTypeStr), (double) -999999),2).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getDatetime(), ybls.getValidtime()), "MM月dd日HH时"),StationID,stationName);
                }
                myData.setDatas(xqdatas);
                myData.setHeaders(headers);
                return myData;
            }
            sessionHuanJing.close();
        }else{
            SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
            SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession();
            CuaceSurfaceMapper huanJingDao = sessionHuanJing.getMapper(CuaceSurfaceMapper.class);
            List<CuaceSurface> myDataLs= huanJingDao.findAllByStationidAndDatetime(StationID,myDate);
            if(!myDataLs.isEmpty()){
                亚洲沙尘模式数据调整(myDataLs);
                地图站点详情Model myData = new 地图站点详情Model();
                myData.setTitle(DateUtil.format(myDate, "亚洲沙尘模式MM月dd日HH时起报") +  chineseName + "预报");
                myData.setYb1Name(chineseName);
                地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[myDataLs.size()];
                地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
                headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",chineseName+"("+units+")",true,false,true);
                List<站点信息> myidList=huanJingDao.GetStationsByTypeAndStationID("shachen",StationID);
                String stationName="";
                if(myidList.size()>0){
                    stationName=myidList.get(0).getName();
                }
                for (int i = 0; i < myDataLs.size(); i++) {
                    CuaceSurface ybls = myDataLs.get(i);
                    xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, DataTypeStr), (double) -999999),2).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getDatetime(), ybls.getValidTime()), "MM月dd日HH时"),StationID,stationName);
                }
                myData.setDatas(xqdatas);
                myData.setHeaders(headers);
                return myData;
            }
            sessionHuanJing.close();
        }
        return null;
    }

    public 地图站点详情Model 区台沙尘模式站点详情数据处理(Date myDate,String StationID, String DataTypeStr, Boolean IsHeigh, float Fcstlevel){
        String units= 基本处理.区台沙尘模式单位获取(DataTypeStr);
        String chineseName=基本处理.区台沙尘模式中文名称(DataTypeStr);
        if("PM25".equals(DataTypeStr)){
            DataTypeStr="PM2.5";
        }
        SqlSessionFactory sqlSessionFactoryHuanJing = SqlSessionFactoryUtil.getSqlHuanjingSessionFactory();
        SqlSession sessionHuanJing = sqlSessionFactoryHuanJing.openSession();
        qtShaChenMapper huanJingDao = sessionHuanJing.getMapper(qtShaChenMapper.class);
        List<qtShaChen> myDataLs= huanJingDao.findAllByStationidAndDatetimeAndFcstlevelAndFcstname(DateUtil.format(myDate, "yyyyMM"),StationID,myDate,1000.0,DataTypeStr);
        if(!myDataLs.isEmpty()){
            地图站点详情Model myData = new 地图站点详情Model();
            myData.setTitle(DateUtil.format(myDate, "区台沙尘模式MM月dd日HH时起报") +  chineseName + "预报");
            myData.setYb1Name(chineseName);
            地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[myDataLs.size()];
            地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
            headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
            headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
            headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
            headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",chineseName+"("+units+")",true,false,true);
            List<站点信息> myidList=huanJingDao.GetStationsByTypeAndStationID("shachen",StationID);
            String stationName="";
            if(myidList.size()>0){
                stationName=myidList.get(0).getName();
            }
            for (int i = 0; i < myDataLs.size(); i++) {
                qtShaChen ybls = myDataLs.get(i);
                xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, DataTypeStr), (double) -999999),2).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getDatetime(), ybls.getValidtime()), "MM月dd日HH时"),StationID,stationName);
            }
            myData.setDatas(xqdatas);
            myData.setHeaders(headers);
            return myData;
        }
        sessionHuanJing.close();
        return null;
    }
}
