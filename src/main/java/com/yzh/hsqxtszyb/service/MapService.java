package com.yzh.hsqxtszyb.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.yzh.hsqxtszyb.cimiss.地面实况数据类型转换;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.dao.StationDaoImpl;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.model.EC.ECDataModel;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MapService {
    StationDaoImpl stationDaoImpl = new StationDaoImpl();

    public OpenlayersQJMethodBaseModel getMyjsonCs() {
        OpenlayersQJMethodBaseModel myData = new OpenlayersQJMethodBaseModel("FeatureCollection");
        OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[2];
        /*myFeas[0]=new OpenlayersQJMethodModel("Feature",new OpenlayersgeometryModel("Point", new Double[]{111.70893300, 40.76124776}),new OpenlayersQJMethodPropertiesModel("赛罕","53466",3.2,"温度","℃"));
        myFeas[1]=new OpenlayersQJMethodModel("Feature",new OpenlayersgeometryModel("Point", new Double[]{113.70893300, 44.76124776}),new OpenlayersQJMethodPropertiesModel("山顶","53463",3.5,"温度","℃"));*/
        myData.setFeatures(myFeas);
        return myData;
    }

    public OpenlayersQJMethodBaseModel 获取站点预报数据(String YBType, String DataTypeID, String StationTye, String DQID, long times, int YbSx, int stationlevelType, double stationlevel) {
        try {
            int dataTypeIDInt = Integer.parseInt(DataTypeID);

            List<站点信息> ids;
            if (YBType.equals("区台新方法")) {
                ids = 根据ID获取区台新方法站点(DQID, StationTye);
                if (ids.size() > 0) {

                    StringBuffer stringBuffer = new StringBuffer();
                    for (站点信息 id : ids
                    ) {
                        stringBuffer.append(id.getID() + ",");
                    }
                    String idStr = stringBuffer.substring(0, stringBuffer.length() - 1);
                    OpenlayersQJMethodBaseModel myData = new OpenlayersQJMethodBaseModel("FeatureCollection");
                    List<区台格点数值预报站点Model> mydataYBs = 根据站点信息起报时间预报时效获取新方法预报(idStr, dataTypeIDInt, times, YbSx);
                    if (mydataYBs.size() > 0) {
                        OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[mydataYBs.size()];
                        String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(dataTypeIDInt);
                        String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(DataTypeID));
                        String myYsUnit = 地面实况数据类型转换.地面预报数据类型单位转换(Integer.parseInt(DataTypeID));
                        for (int i = 0; i < mydataYBs.size(); i++) {
                            try {
                                区台格点数值预报站点Model datals = mydataYBs.get(i);
                                站点信息 station = ids.stream().filter(y -> y.getID().equals(datals.getID())).findFirst().get();
                                //风有两个变量特殊处理
                                if (dataTypeIDInt == 4) {
                                    double winu = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIU10"), (double) -999999);
                                    double winv = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIV10"), (double) -999999);
                                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), winfsDouble, winfxDouble, "10米风速", "m/s", "10米风速", "rad"));
                                } else {
                                    double ybvalue = NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(datals, mySzybDataType), (double) -999999), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), ybvalue, myYsText, myYsUnit));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        myData.setFeatures(myFeas);
                    }
                    return myData;
                } else {
                    return null;
                }
            } else if (YBType.equals("RMAPS")) {
                ids = 根据ID获取Rmaps站点(DQID, StationTye);
                if (ids.size() > 0) {

                    StringBuffer stringBuffer = new StringBuffer();
                    for (站点信息 id : ids
                    ) {
                        stringBuffer.append(id.getID() + ",");
                    }
                    String idStr = stringBuffer.substring(0, stringBuffer.length() - 1);
                    OpenlayersQJMethodBaseModel myData = new OpenlayersQJMethodBaseModel("FeatureCollection");
                    List<Rmaps格点数值预报站点Model> mydataYBs = 根据站点信息起报时间预报时效获取Rmaps站点预报(idStr, dataTypeIDInt, times, YbSx, stationlevelType, stationlevel);
                    if (mydataYBs.size() > 0) {
                        OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[mydataYBs.size()];
                        String mySzybDataType = 地面实况数据类型转换.Rmaps预报数据库地面实况数据类型转换(dataTypeIDInt, stationlevelType, stationlevel);
                        String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(DataTypeID));
                        String myYsUnit = 地面实况数据类型转换.地面预报数据类型单位转换(Integer.parseInt(DataTypeID));
                        for (int i = 0; i < mydataYBs.size(); i++) {
                            try {
                                Rmaps格点数值预报站点Model datals = mydataYBs.get(i);
                                站点信息 station = ids.stream().filter(y -> y.getID().equals(datals.getID())).findFirst().get();
                                //风有两个变量特殊处理
                                if (dataTypeIDInt == 4) {
                                    double winu = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIU10"), (double) -999999);
                                    double winv = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIV10"), (double) -999999);
                                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), winfsDouble, winfxDouble, "10米风速", "m/s", "10米风向", "rad"));
                                } else {
                                    double ybvalue = NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(datals, mySzybDataType), (double) -999999), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), ybvalue, myYsText, myYsUnit));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        myData.setFeatures(myFeas);
                    }
                    return myData;
                } else {
                    return null;
                }
            }else if (YBType.equals("EC")) {
                ids = 根据ID获取EC站点(DQID, StationTye);
                if (ids.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (站点信息 id : ids
                    ) {
                        stringBuffer.append(id.getID() + ",");
                    }
                    String idStr = stringBuffer.substring(0, stringBuffer.length() - 1);
                    OpenlayersQJMethodBaseModel myData = new OpenlayersQJMethodBaseModel("FeatureCollection");
                    List<ECDataModel> mydataYBs= 根据站点信息起报时间预报时效获取EC站点预报(idStr, dataTypeIDInt, times, YbSx, stationlevelType, stationlevel);
                    if (mydataYBs.size() > 0) {
                        OpenlayersQJMethodModel[] myFeas = new OpenlayersQJMethodModel[mydataYBs.size()];
                        String mySzybDataType = 地面实况数据类型转换.EC预报数据库地面实况数据类型转换(dataTypeIDInt, stationlevelType, stationlevel);
                        String myYsText = 地面实况数据类型转换.EC地面数据类型文字描述转换(dataTypeIDInt);
                        String myYsUnit = 地面实况数据类型转换.EC预报数据类型单位转换(dataTypeIDInt);
                        for (int i = 0; i < mydataYBs.size(); i++) {
                            try {
                                ECDataModel datals = mydataYBs.get(i);
                                站点信息 station = ids.stream().filter(y -> y.getID().equals(datals.getID())).findFirst().get();
                                //风有两个变量特殊处理
                                if (dataTypeIDInt == 4) {
                                    double winu = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIU10"), (double) -999999);
                                    double winv = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIV10"), (double) -999999);
                                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), winfsDouble, winfxDouble, "10米风速", "m/s", "10米风向", "rad"));
                                }
                                else if (dataTypeIDInt == 4100) {
                                    double winu = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIU100"), (double) -999999);
                                    double winv = Convert.toDouble(ReflectUtil.getFieldValue(datals, "WIV100"), (double) -999999);
                                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), winfsDouble, winfxDouble, "10米风速", "m/s", "10米风向", "rad"));
                                }
                                else {
                                    double ybvalue = NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(datals, mySzybDataType), (double) -999999), 1).doubleValue();
                                    myFeas[i] = new OpenlayersQJMethodModel("Feature", new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}), new OpenlayersQJMethodPropertiesModel(station.getName(), station.getID(), station.getLevel(), ybvalue, myYsText, myYsUnit));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        myData.setFeatures(myFeas);
                    }
                    return myData;
                } else {
                    return null;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<站点信息> 根据ID获取区台新方法站点(String ID, String StationType) {
        web站点检索Model myweb站点信息 = new web站点检索Model();
        List<站点信息> stat = new ArrayList<>();
        if (ID == null) {
            myweb站点信息.setID("15");
        } else {
            String[] idSz = ID.split(",");
            if (idSz.length > 1) {
                String myIDs = "";
                for (String myID : idSz
                ) {
                    myIDs += "^" + myID + "|";
                }
                myweb站点信息.setID(myIDs.substring(0, myIDs.length() - 1));
            } else {
                myweb站点信息.setID("^" + ID);
            }

        }
        StringBuilder sb = new StringBuilder();
        if (StationType.contains("国家站")) {
            sb.append("12,13,");

        }
        if (StationType.contains("区域站")) {
            sb.append("14,");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        myweb站点信息.setStationLevlString(sb.toString());
        try {
            stat = stationDaoImpl.根据地区信息站点类型获取站点(myweb站点信息);


        } catch (Exception e) {
            e.getMessage();
        }
        return stat;
    }

    public List<站点信息> 根据ID获取Rmaps站点(String ID, String StationType) {
        web站点检索Model myweb站点信息 = new web站点检索Model();
        List<站点信息> stat = new ArrayList<>();
        if (ID == null) {
            myweb站点信息.setID("15");
        } else {
            String[] idSz = ID.split(",");
            if (idSz.length > 1) {
                String myIDs = "";
                for (String myID : idSz
                ) {
                    myIDs += "^" + myID + "|";
                }
                myweb站点信息.setID(myIDs.substring(0, myIDs.length() - 1));
            } else {
                myweb站点信息.setID("^" + ID);
            }

        }
        StringBuilder sb = new StringBuilder();
        if (StationType.contains("国家站")) {
            sb.append("12,13,");
        }
        if (StationType.contains("区域站")) {
            sb.append("14,");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        myweb站点信息.setStationLevlString(sb.toString());
        try {
            stat = stationDaoImpl.根据地区信息站点类型获取Rmaps站点(myweb站点信息);


        } catch (Exception e) {
            e.getMessage();
        }
        return stat;
    }
    @Test
    public void cs(){
        //获取站点预报数据("EC","4100","区域站","150121,150123",1615852800000L,12,103,0);
        获取站点预报详情("EC","4100","53466",1615852800000L,103,0);
    }
    public String 获取风流场Json(String YBType, long times, int YbSx, int stationlevelType, double stationlevel,double dlat)
    {
       try{
           DateTime myDate = DateUtil.date(times);
           if(YBType.contains("RMAPS")){
               if(stationlevel==0&&stationlevelType==103){
                   SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                   SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHH");
                   String datePath1 = df.format(myDate);
                   String timePath1 = df2.format(myDate);
                   String myFilePath= StrUtil.format("/home/qxt/区台数值预报文件/szyb/json/rmaps数据/风流场/{}/RMAPS_wind_{}_{}_{}.json", datePath1,dlat,timePath1,String.format("%04d", YbSx) );;
                   if(FileUtil.exist(myFilePath)){
                       return FileUtil.readUtf8String(myFilePath);
                   }
                   else{
                       return myFilePath;
                   }
               }
           }
           return "";
       } catch (IORuntimeException e) {
           e.printStackTrace();
           return "";
       }
    }
    public List<站点信息> 根据ID获取EC站点(String ID, String StationType) {
        web站点检索Model myweb站点信息 = new web站点检索Model();
        List<站点信息> stat = new ArrayList<>();
        if (ID == null) {
            myweb站点信息.setID("Admin_Code LIKE '15%'");
        } else {
            String[] idSz = ID.split(",");
            if (idSz.length > 1) {
                String myIDs = "";
                for (String myID : idSz
                ) {
                    myIDs += " Admin_Code LIKE '" + myID + "%' OR";
                }
                myweb站点信息.setID(myIDs.substring(0, myIDs.length() - 3));
            } else {
                myweb站点信息.setID("Admin_Code LIKE '" + ID+ "%'");
            }

        }
        StringBuilder sb = new StringBuilder();
        if (StationType.contains("国家站")) {
            sb.append("12,13,");
        }
        if (StationType.contains("区域站")) {
            sb.append("14,");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        myweb站点信息.setStationLevlString(sb.toString());
        try {
            stat = stationDaoImpl.根据地区信息站点类型获取EC站点(myweb站点信息);
        } catch (Exception e) {
            e.getMessage();
        }
        return stat;
    }
    public List<区台格点数值预报站点Model> 根据站点信息起报时间预报时效获取新方法预报(String ids, int dataTypeId, long timespan, int YbSx) {
        DateTime sdate = DateUtil.date(timespan);
        List<区台格点数值预报站点Model> mydataYBs = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return mydataYBs;
        }
        try {
            String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(dataTypeId);
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(ids, DateUtil.formatDateTime(sdate), mySzybDataType, 1, 103, YbSx, "Szyb_GD_ZD_" + DateUtil.format(sdate, "yyyyMMdd"));
            mydataYBs = stationDaoImpl.根据站点列表预报时效获取区台格点数值预报站点预报(indexData);

        } catch (Exception e) {
            e.getMessage();
        }
        return mydataYBs;
    }

    public List<Rmaps格点数值预报站点Model> 根据站点信息起报时间预报时效获取Rmaps站点预报(String ids, int dataTypeId, long timespan, int YbSx, int stationlevelType, double stationlevel) {
        DateTime sdate = DateUtil.date(timespan);
        List<Rmaps格点数值预报站点Model> mydataYBs = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return mydataYBs;
        }
        try {
            String mySzybDataType = 地面实况数据类型转换.Rmaps预报数据库地面实况数据类型转换(dataTypeId, stationlevelType, stationlevel);
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(ids, DateUtil.formatDateTime(sdate), mySzybDataType, YbSx, "Rmaps_GD_ZD_" + DateUtil.format(sdate, "yyyyMMdd"));
            mydataYBs = stationDaoImpl.根据站点列表预报时效获取Rmaps数值预报站点预报(indexData);

        } catch (Exception e) {
            e.getMessage();
        }
        return mydataYBs;
    }
    public List<ECDataModel> 根据站点信息起报时间预报时效获取EC站点预报(String ids, int dataTypeId, long timespan, int YbSx, int stationlevelType, double stationlevel) {
        DateTime sdate = DateUtil.date(timespan);
        List<ECDataModel> mydataYBs = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return mydataYBs;
        }
        try {
            String mySzybDataType = 地面实况数据类型转换.EC预报数据库地面实况数据类型转换(dataTypeId, stationlevelType, stationlevel);
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(ids, DateUtil.formatDateTime(sdate), mySzybDataType, YbSx,  DateUtil.format(sdate, "yyyyMMdd"));
            mydataYBs = stationDaoImpl.根据站点列表预报时效获取EC数值预报站点预报(indexData);

        } catch (Exception e) {
            e.getMessage();
        }
        return mydataYBs;
    }
    //region
    public 地图站点详情Model 获取站点预报详情(String YBType, String DataTypeID, String StationID, long timespan,int stationlevelType, int stationlevel) {
        try {
            int dataTypeIDInt = Integer.parseInt(DataTypeID);
            DateTime sdate = DateUtil.date(timespan);
            if (YBType.equals("区台新方法")) {
                String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(dataTypeIDInt);

                数值预报检索Model indexData = new 数值预报检索Model(StationID, DateUtil.formatDateTime(sdate), mySzybDataType,1,103,"Szyb_GD_ZD_"+DateUtil.format(sdate,"yyyyMMdd"));

                地图站点详情Model myData = new 地图站点详情Model();
                List<区台格点数值预报站点Model> mydataYBs = stationDaoImpl.根据站点列表获取区台格点数值预报站点预报(indexData);
                if(mydataYBs.size()==0){
                    return myData;
                }
                String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(DataTypeID));
                String myYsUnit = 地面实况数据类型转换.地面预报数据类型单位转换(Integer.parseInt(DataTypeID));
                myData.setTitle(DateUtil.format(sdate, "MM月dd日HH时起报") +  myYsText + "要素预报");
                myData.setYb1Name(myYsText);
                地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[mydataYBs.size()];
                if (mySzybDataType.contains("WIU10")){
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[6];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1","风速(m/s)",true,false,true);
                    headers[4]=new 地图站点详情表头Model(true,"center","ybValue2","风向",true,false,true);
                    headers[5]=new 地图站点详情表头Model(true,"center","ybValue3","风力",true,false,true);
                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        区台格点数值预报站点Model ybls = mydataYBs.get(i);
                        Double winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU10"), (double) -999999);
                        Double winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV10"), (double) -999999);
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(),风向风速转换.GetFX(winV, winU),风向风速转换.GetFL(winV, winU),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);

                }else
                {
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",myYsText+"("+myYsUnit+")",true,false,true);

                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        区台格点数值预报站点Model ybls = mydataYBs.get(i);
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, mySzybDataType), (double) -999999),1).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);
                }
                return myData;
            } else if (YBType.equals("RMAPS")) {
                String mySzybDataType = 地面实况数据类型转换.Rmaps预报数据库地面实况数据类型转换(dataTypeIDInt, stationlevelType, stationlevel);
                数值预报检索Model indexData = new 数值预报检索Model(StationID, DateUtil.formatDateTime(sdate), mySzybDataType, "Rmaps_GD_ZD_" + DateUtil.format(sdate, "yyyyMMdd"));
                地图站点详情Model myData = new 地图站点详情Model();
                List<Rmaps格点数值预报站点Model> mydataYBs = stationDaoImpl.根据站点列表预报获取Rmaps数值预报站点预报(indexData);
                if(mydataYBs.size()==0){
                    return myData;
                }
                String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(DataTypeID));
                String myYsUnit = 地面实况数据类型转换.地面预报数据类型单位转换(Integer.parseInt(DataTypeID));
                myData.setTitle(DateUtil.format(sdate, "MM月dd日HH时起报") +  myYsText + "要素预报");
                myData.setYb1Name(myYsText);
                地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[mydataYBs.size()];
                if (dataTypeIDInt == 4){
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[6];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1","风速(m/s)",true,false,true);
                    headers[4]=new 地图站点详情表头Model(true,"center","ybValue2","风向",true,false,true);
                    headers[5]=new 地图站点详情表头Model(true,"center","ybValue3","风力",true,false,true);
                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        Rmaps格点数值预报站点Model ybls = mydataYBs.get(i);
                        Double winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU10"), (double) -999999);
                        Double winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV10"), (double) -999999);
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(),风向风速转换.GetFX(winV, winU),风向风速转换.GetFL(winV, winU),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);

                }else
                {
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",myYsText+"("+myYsUnit+")",true,false,true);

                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        Rmaps格点数值预报站点Model ybls = mydataYBs.get(i);
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, mySzybDataType), (double) -999999),1).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);
                }
                return myData;
            }
            else if (YBType.equals("EC")) {
                String mySzybDataType = 地面实况数据类型转换.EC预报数据库地面实况数据类型转换(dataTypeIDInt, stationlevelType, stationlevel);
                数值预报检索Model indexData = new 数值预报检索Model(StationID, DateUtil.formatDateTime(sdate), mySzybDataType, DateUtil.format(sdate, "yyyyMMdd"));
                地图站点详情Model myData = new 地图站点详情Model();
                List<ECDataModel> mydataYBs = stationDaoImpl.根据站点列表预报获取EC数值预报站点预报(indexData);
                if(mydataYBs.size()==0){
                    return myData;
                }
                String myYsText = 地面实况数据类型转换.EC地面数据类型文字描述转换(dataTypeIDInt);
                String myYsUnit = 地面实况数据类型转换.EC预报数据类型单位转换(dataTypeIDInt);
                myData.setTitle(DateUtil.format(sdate, "MM月dd日HH时起报") +  myYsText + "要素预报");
                myData.setYb1Name(myYsText);
                地图站点详情数据内容[] xqdatas=new 地图站点详情数据内容[mydataYBs.size()];
                if (dataTypeIDInt == 4||dataTypeIDInt == 4100){
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[6];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1","风速(m/s)",true,false,true);
                    headers[4]=new 地图站点详情表头Model(true,"center","ybValue2","风向",true,false,true);
                    headers[5]=new 地图站点详情表头Model(true,"center","ybValue3","风力",true,false,true);
                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        ECDataModel ybls = mydataYBs.get(i);
                        Double winU = -999999D;
                        Double winV = -999999D;
                        if(dataTypeIDInt == 4100){
                            winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU100"), (double) -999999);
                            winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV100"), (double) -999999);
                        } else if (dataTypeIDInt == 4) {
                            winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU10"), (double) -999999);
                            winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV10"), (double) -999999);
                        }
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(),风向风速转换.GetFX(winV, winU),风向风速转换.GetFL(winV, winU),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);

                }
                else
                {
                    地图站点详情表头Model[] headers=new 地图站点详情表头Model[4];
                    headers[0]=new 地图站点详情表头Model(true,"center","stationID","站号",true,false,true);
                    headers[1]=new 地图站点详情表头Model(true,"center","stationName","站名",true,false,true);
                    headers[2]=new 地图站点详情表头Model(true,"center","timeStr","时间",true,false,true);
                    headers[3]=new 地图站点详情表头Model(true,"center","ybValue1",myYsText+"("+myYsUnit+")",true,false,true);

                    List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(StationID));
                    String stationName="";
                    if(myidList.size()>0){
                        stationName=myidList.get(0).getName();
                    }
                    for (int i = 0; i < mydataYBs.size(); i++) {
                        ECDataModel ybls = mydataYBs.get(i);
                        xqdatas[i]=new 地图站点详情数据内容(NumberUtil.round(Convert.toDouble(ReflectUtil.getFieldValue(ybls, mySzybDataType), (double) -999999),1).doubleValue(),DateUtil.format(DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()), "MM月dd日HH时"),StationID,stationName);
                    }
                    myData.setDatas(xqdatas);
                    myData.setHeaders(headers);
                }
                return myData;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //endregion
}
