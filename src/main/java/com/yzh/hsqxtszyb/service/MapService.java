package com.yzh.hsqxtszyb.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import com.yzh.hsqxtszyb.cimiss.地面实况数据类型转换;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.dao.StationDao;
import com.yzh.hsqxtszyb.dao.StationDaoImpl;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapService {
    StationDaoImpl stationDaoImpl = new StationDaoImpl();
    public OpenlayersQJMethodBaseModel getMyjsonCs(){
        OpenlayersQJMethodBaseModel myData=new OpenlayersQJMethodBaseModel("FeatureCollection");
        OpenlayersQJMethodModel[] myFeas= new OpenlayersQJMethodModel[2];
        /*myFeas[0]=new OpenlayersQJMethodModel("Feature",new OpenlayersgeometryModel("Point", new Double[]{111.70893300, 40.76124776}),new OpenlayersQJMethodPropertiesModel("赛罕","53466",3.2,"温度","℃"));
        myFeas[1]=new OpenlayersQJMethodModel("Feature",new OpenlayersgeometryModel("Point", new Double[]{113.70893300, 44.76124776}),new OpenlayersQJMethodPropertiesModel("山顶","53463",3.5,"温度","℃"));*/
        myData.setFeatures(myFeas);
         return  myData;
    }

    public OpenlayersQJMethodBaseModel 获取站点预报数据(String YBType,String DataTypeID,String StationTye,String DQID, long times,int YbSx){
        try{
            int dataTypeIDInt=Integer.parseInt(DataTypeID);

            List<站点信息> ids=new ArrayList<>();
            if(YBType.equals("区台新方法")){
                ids=根据ID获取区台新方法站点(DQID,StationTye);
                if(ids.size()>0){

                    StringBuffer stringBuffer=new StringBuffer();
                    for (站点信息 id:ids
                         ) {
                        stringBuffer.append(id.getID()+",");
                    }
                    String idStr=stringBuffer.substring(0,stringBuffer.length()-1);
                    OpenlayersQJMethodBaseModel myData=new OpenlayersQJMethodBaseModel("FeatureCollection");
                    List<区台格点数值预报站点Model> mydataYBs= 根据站点信息起报时间预报时效获取新方法预报(idStr,dataTypeIDInt,times,YbSx);
                    if(mydataYBs.size()>0){
                        OpenlayersQJMethodModel[] myFeas= new OpenlayersQJMethodModel[mydataYBs.size()];
                        String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(dataTypeIDInt);
                        String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(DataTypeID));
                        String myYsUnit =地面实况数据类型转换.地面预报数据类型单位转换(Integer.parseInt(DataTypeID));
                        for(int i=0;i<mydataYBs.size();i++){
                            try{
                                区台格点数值预报站点Model datals=mydataYBs.get(i);
                                站点信息 station= ids.stream().filter(y->y.getID().equals(datals.getID())).findFirst().get();
                                //风有两个变量特殊处理
                                if (dataTypeIDInt==4) {

                                }else {
                                    double ybvalue= Convert.toDouble(ReflectUtil.getFieldValue(datals, mySzybDataType), (double) -999999);
                                    myFeas[i]=new OpenlayersQJMethodModel("Feature",new OpenlayersgeometryModel("Point", new Double[]{station.getLon(), station.getLat()}),new OpenlayersQJMethodPropertiesModel(station.getName(),station.getID(),station.getLevel(),ybvalue,myYsText,myYsUnit));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        myData.setFeatures(myFeas);
                    }
                    return  myData;
                }else{
                    return null;
                }
            }else if(YBType.equals("RMAPS")){
                ids=根据ID获取区台新方法站点(DQID,StationTye);
                if(ids.size()>0){
                    OpenlayersQJMethodBaseModel myData=new OpenlayersQJMethodBaseModel("FeatureCollection");

                    return  myData;
                }else{
                    return null;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<站点信息> 根据ID获取区台新方法站点(String ID,String StationType) {
        web站点检索Model myweb站点信息 = new web站点检索Model();
        List<站点信息> stat = new ArrayList<>();
        if (ID == null) {
            myweb站点信息.setID("15");
        } else {
            String[] idSz=ID.split(",");
            if(idSz.length>1){
                String myIDs="";
                for (String myID:idSz
                ) {
                    myIDs+="^"+myID+"|";
                }
                myweb站点信息.setID(myIDs.substring(0,myIDs.length()-1));
            }else{
                myweb站点信息.setID("^"+ID);
            }

        }
        StringBuilder sb=new StringBuilder();
        if(StationType.contains("国家站")){
            sb.append("12,13,");

        }
        if(StationType.contains("区域站")){
            sb.append("14,");
        }
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        myweb站点信息.setStationLevlString(sb.toString());
        try {
            stat = stationDaoImpl.根据地区信息站点类型获取站点(myweb站点信息);


        } catch (Exception e) {
            e.getMessage();
        }
        return stat;
    }

    public List<区台格点数值预报站点Model> 根据站点信息起报时间预报时效获取新方法预报(String ids, int dataTypeId, long timespan,int YbSx) {
        DateTime sdate = DateUtil.date(timespan);
        List<区台格点数值预报站点Model> mydataYBs=new ArrayList<>();
        if(ids==null||ids.isEmpty()){
            return mydataYBs;
        }
        try {
            String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(dataTypeId);
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(ids, DateUtil.formatDateTime(sdate), mySzybDataType,1,103,YbSx,"Szyb_GD_ZD_"+DateUtil.format(sdate,"yyyyMMdd"));
            mydataYBs = stationDaoImpl.根据站点列表预报时效获取区台格点数值预报站点预报(indexData);

        } catch (Exception e) {
            e.getMessage();
        }
        return mydataYBs;
    }
}
