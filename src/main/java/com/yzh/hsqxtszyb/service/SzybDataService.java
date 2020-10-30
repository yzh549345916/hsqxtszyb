package com.yzh.hsqxtszyb.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yzh.hsqxtszyb.cimiss.Cimiss地面实况;
import com.yzh.hsqxtszyb.cimiss.地面实况数据类型转换;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.dao.StationDao;
import com.yzh.hsqxtszyb.dao.StationDaoImpl;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class SzybDataService {
    StationDaoImpl stationDaoImpl = new StationDaoImpl();
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
    SqlSession session = sqlSessionFactory.openSession();
    StationDao stationDao = session.getMapper(StationDao.class);

    public List<站点信息> 获取所有站点() {

        List<站点信息> mydata = new ArrayList<>();
        try {
            mydata = stationDaoImpl.获取站点信息();
        } catch (Exception e) {
        }

        return mydata;
    }



    public List<web站点信息> 根据ID获取地区(String[] ID) {
        web站点信息 myweb站点信息 = new web站点信息();
        if (ID == null) {
            myweb站点信息.setID("15");
            return stationDaoImpl.根据省份ID获取省份(myweb站点信息);
        } else {
            myweb站点信息.setID(ID[ID.length - 1]);
        }
        try {
            if (myweb站点信息.getID().length() == 2) {
                return stationDaoImpl.根据省份ID获取盟市(myweb站点信息);
            } else if (myweb站点信息.getID().length() == 4) {
                List<web站点信息> myStations = stationDaoImpl.根据盟市ID获取旗县区(myweb站点信息);
                return myStations;
            } else if (myweb站点信息.getID().length() == 6 && !myweb站点信息.getID().startsWith("C")) {
                List<站点信息> stat = stationDaoImpl.根据地区信息获取站点(myweb站点信息);
                List<web站点信息> myStations = new ArrayList<>();
                for (站点信息 item : stat
                ) {
                    myStations.add(new web站点信息(item.getID(), item.getName() + "," + item.getID(), false));
                }
                return myStations;
            }
        } catch (Exception e) {
        }
        List<web站点信息> mydata = new ArrayList<>();
        try {
            mydata = stationDaoImpl.根据盟市ID获取旗县区(myweb站点信息);
        } catch (Exception ignored) {

        }
        return mydata;
    }

    public List<地区Model> 根据ID获取地区Tree(String ID) {
        地区Model returnData = new 地区Model();
        List<地区Model> myDatas=new ArrayList<>();
        List<web站点信息> myDataLS=new ArrayList<>();
        web站点信息 myweb站点信息 = new web站点信息();
        if (ID==null|| ID.isEmpty()) {
            myweb站点信息.setID("15");
        }
        else{
            myweb站点信息.setID(ID);
        }
        try {
            if (myweb站点信息.getID().length() == 2) {
                try{
                    web站点信息 mySF= stationDaoImpl.根据省份ID获取省份(myweb站点信息).get(0);
                    returnData.setID(mySF.getID());
                    returnData.setName(mySF.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                myDataLS= stationDaoImpl.根据省份ID获取盟市(myweb站点信息);
                for (web站点信息 myData1:myDataLS
                     ) {
                    if(myData1.isHasChildren()){
                        地区Model data=myData1.ConverTo地区();
                        List<地区Model> myDatas2=new ArrayList<>();
                        web站点信息 myweb站点信息LS = new web站点信息();
                        myweb站点信息LS.setID(myData1.getID());
                        List<web站点信息> myDataLS2= stationDaoImpl.根据盟市ID获取旗县区(myweb站点信息LS);
                        for (web站点信息 myData2:myDataLS2
                             ) {
                            if(myData2.isHasChildren()){
                                地区Model data2=myData2.ConverTo地区();
                                data2.setChildren(new ArrayList<>());
                                myDatas2.add(data2);
                            }
                        }
                        data.setChildren(myDatas2);
                        myDatas.add(data);
                    }
                }

            } else if (myweb站点信息.getID().length() == 4) {
                myDataLS= stationDaoImpl.根据盟市ID获取旗县区(myweb站点信息);
                try{
                    web站点信息 mySF= stationDaoImpl.根据盟市ID获取盟市(myweb站点信息).get(0);
                    returnData.setID(mySF.getID());
                    returnData.setName(mySF.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (web站点信息 myData1:myDataLS
                ) {
                    if(myData1.isHasChildren()){
                        地区Model data=myData1.ConverTo地区();
                        data.setChildren(new ArrayList<>());
                        myDatas.add(data);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        returnData.setChildren(myDatas);
        List<地区Model> myDatasReturn=new ArrayList<>();
        myDatasReturn.add(returnData);
        return myDatasReturn;
    }
    public List<web站点信息> 根据ID获取站点(String ID,String StationType) {
        web站点检索Model myweb站点信息 = new web站点检索Model();
        List<web站点信息> myStations = new ArrayList<>();
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
            List<站点信息> stat = stationDaoImpl.根据地区信息站点类型获取站点(myweb站点信息);
            for (站点信息 item : stat
            ) {
                myStations.add(new web站点信息(item.getID(), item.getName() + "," + item.getID(), false));
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return myStations;
    }

    public void cs(){
        GetSzybByTimeAndStionIDs("53463,53464","0",1598875200000L);
    }
    public List<数值预报查询Model> GetSzybByTimeAndStionIDs(String ids, String dataTypeId, long timespan) {
        DateTime sdate = DateUtil.date(timespan);

        List<数值预报查询Model> datasReturn=new ArrayList<>();
        if(ids==null||ids.isEmpty()){
            return datasReturn;
        }
        try {
            if(dataTypeId.equals("")){
                dataTypeId="0";
            }
            String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(Integer.parseInt(dataTypeId));
            //处理区局格点数值预报

            数值预报检索Model indexData = new 数值预报检索Model(ids, DateUtil.formatDateTime(sdate), mySzybDataType,1,103,"Szyb_GD_ZD_"+DateUtil.format(sdate,"yyyyMMdd"));
            String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(dataTypeId));
            List<区台格点数值预报站点Model> mydataYBs = stationDaoImpl.根据站点列表获取区台格点数值预报站点预报(indexData);
            if(mydataYBs.size()==0){
                return datasReturn;
            }
            List<站点信息> myidList=stationDaoImpl.根据站点ID列表获取站点信息(new web站点检索Model(ids));
            for (String id:ids.split(",")
                 ) {
                数值预报查询Model mydata = new 数值预报查询Model();

                mydata.setTitle(DateUtil.format(sdate, "MM月dd日HH时起报") + myYsText + "要素预报");
                List<区台格点数值预报站点Model> mydataYB=mydataYBs.stream().filter(y->y.getID().equals(id)).collect(Collectors.toUnmodifiableList());
                if (mydataYB.size() == 0) {
                    continue;
                }
                mydata.setYb1Name(id+myidList.stream().filter(y->y.getID().equals(id)).findFirst().get().getName());
                double[][] yb1 = new double[mydataYB.size()][2];
                if (mySzybDataType.contains("WIU10")) {
                    Object[][] yb2 = new Object[mydataYB.size()][3];
                    mydata.setYb2Name("10米风向预报");
                    for (int i = 0; i < mydataYB.size(); i++) {
                        区台格点数值预报站点Model ybls = mydataYB.get(i);
                        double myTime = DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()).getTime();
                        yb1[i][0] = myTime;
                        yb2[i][0] = myTime;
                        Double winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU10"), (double) -999999);
                        Double winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV10"), (double) -999999);
                        if (winU < -99999 || winV < -99999) {
                            yb1[i][1] = 0;
                            yb2[i][1] = "0";
                            yb2[i][2] = null;
                        } else {
                            yb1[i][1] = NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue();
                            yb2[i][1] = NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue();
                            yb2[i][2] = 风向风速转换.GetFX(winV, winU);
                        }
                    }
                    mydata.setYbValue2(yb2);
                } else {
                    for (int i = 0; i < mydataYB.size(); i++) {
                        区台格点数值预报站点Model ybls = mydataYB.get(i);
                        yb1[i][0] = DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()).getTime();
                        yb1[i][1] = Convert.toDouble(ReflectUtil.getFieldValue(ybls, mySzybDataType), (double) -999999);

                    }
                }
                mydata.setYbValue1(yb1);
                datasReturn.add(mydata);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return datasReturn;
    }
    public 数值预报查询Model GetSzybAndSkByTimeAndStionID(String id, String dataTypeId, long timespan) {
        DateTime sdate = DateUtil.date(timespan);
        数值预报查询Model mydata = new 数值预报查询Model();
        try {
            String myYsText = 地面实况数据类型转换.地面实况数据类型文字描述转换(Integer.parseInt(dataTypeId));
            mydata.setTitle(id + "站" + DateUtil.format(sdate, "MM月dd日HH时起报") + myYsText + "要素预报");

            String mySzybDataType = 地面实况数据类型转换.区局数值预报格点预报数据库地面实况数据类型转换(Integer.parseInt(dataTypeId));
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(id, DateUtil.formatDateTime(sdate), mySzybDataType,1,103,"Szyb_GD_ZD_"+DateUtil.format(sdate,"yyyyMMdd"));
            List<区台格点数值预报站点Model> mydataYB = stationDaoImpl.获取区台格点数值预报站点预报(indexData);
            if (mydataYB.size() == 0) {
                return mydata;
            }
            mydata.setYb1Name(myYsText + "预报");
            double[][] yb1 = new double[mydataYB.size()][2];

            if (mySzybDataType.contains("WIU10")) {
                Object[][] yb2 = new Object[mydataYB.size()][3];
                mydata.setYb2Name("10米风向预报");
                for (int i = 0; i < mydataYB.size(); i++) {
                    区台格点数值预报站点Model ybls = mydataYB.get(i);
                    double myTime = DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()).getTime();
                    yb1[i][0] = myTime;
                    yb2[i][0] = myTime;
                    Double winU = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIU10"), (double) -999999);
                    Double winV = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIV10"), (double) -999999);
                    if (winU < -99999 || winV < -99999) {
                        yb1[i][1] = 0;
                        yb2[i][1] = "0";
                        yb2[i][2] = null;
                    } else {
                        yb1[i][1] = NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue();
                        yb2[i][1] = NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue();
                        yb2[i][2] = 风向风速转换.GetFX(winV, winU);
                    }
                }
                mydata.setYbValue2(yb2);
            } else {
                for (int i = 0; i < mydataYB.size(); i++) {
                    区台格点数值预报站点Model ybls = mydataYB.get(i);
                    yb1[i][0] = DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()).getTime();
                    yb1[i][1] = Convert.toDouble(ReflectUtil.getFieldValue(ybls, mySzybDataType), (double) -999999);

                }
            }
            mydata.setYbValue1(yb1);

            if (sdate.isBeforeOrEquals(DateUtil.date())) {
                String myCimissDataType = 地面实况数据类型转换.cimiss地面实况数据类型转换(Integer.parseInt(dataTypeId));
                Date edate = DateUtil.offsetHour(sdate, 72);
                Date dateNow = DateUtil.date();
                if (edate.compareTo(DateUtil.date()) > 0) {
                    edate = DateUtil.date();
                }
                List<Date> myDates = new ArrayList<>();
                for (int i = 0; i <= 72; i = i + 3) {
                    int finalI = i;
                    if (!mydataYB.stream().anyMatch(y -> y.getSX() == finalI)) {
                        continue;
                    }
                    Date myDate = DateUtil.offsetHour(sdate, i);
                    if (myDate.compareTo(DateUtil.date()) <= 0) {
                        myDates.add(myDate);
                    } else {
                        break;
                    }
                }
                if (myDates.size() > 0) {
                    String ss = Cimiss地面实况.SURF_CHN_MUL_HOR_getSurfEleByTimeAndStaID_JSON(myDates, id, myCimissDataType);
                    JSONObject jsonObject = JSONUtil.parseObj(ss);
                    JSONArray mys = jsonObject.getJSONArray("DS");
                    List<Cimiss地面小时实况Model> mydataYB1 = mys.toList(ClassUtil.getClass(new Cimiss地面小时实况Model()));
                    if (!mydataYB1.isEmpty() && mydataYB1.size() > 0) {
                        mydata.setSk1Name(myYsText + "实况");
                        double[][] sk1 = new double[mydataYB1.size()][2];
                        if (myCimissDataType.contains("WIN_S_Avg_10mi")) {
                            for (int i = 0; i < mydataYB1.size(); i++) {
                                Cimiss地面小时实况Model ybls = mydataYB1.get(i);
                                sk1[i][0] = DateUtil.offsetHour(ybls.getDatetime(), 8).getTime();
                                sk1[i][1] = Convert.toDouble(ReflectUtil.getFieldValue(ybls, "WIN_S_Avg_10mi"), (double) -999999);
                            }
                        } else {
                            for (int i = 0; i < mydataYB1.size(); i++) {
                                Cimiss地面小时实况Model ybls = mydataYB1.get(i);
                                sk1[i][0] = DateUtil.offsetHour(ybls.getDatetime(), 8).getTime();
                                sk1[i][1] = Convert.toDouble(ReflectUtil.getFieldValue(ybls, myCimissDataType), (double) -999999);
                            }
                        }
                        mydata.setSkValue1(sk1);
                    }
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return mydata;
    }

    public 数值预报方法多要素查询Model GetSzybAndSkByTimeAndStionIDNoDataType(String id, long timespan) {
        DateTime sdate = DateUtil.date(timespan);
        数值预报方法多要素查询Model mydata = new 数值预报方法多要素查询Model();
        try {

            mydata.setTitle(id + "站" + DateUtil.format(sdate, "MM月dd日HH时起报") + "预报");
            //处理区局格点数值预报
            数值预报检索Model indexData = new 数值预报检索Model(id, DateUtil.formatDateTime(sdate), "",1,103,"Szyb_GD_ZD_"+DateUtil.format(sdate,"yyyyMMdd"));

            List<区台格点数值预报站点Model> mydataYB = stationDaoImpl.获取区台格点数值预报全部类型站点预报(indexData);
            if (mydataYB.size() == 0) {
                return mydata;
            }
            List<折线显示Model> temYbLists = new ArrayList<>();
            List<折线显示Model> tmaYbxLists = new ArrayList<>();
            List<折线显示Model> tminYbLists = new ArrayList<>();
            List<折线显示Model> rhuYbLists = new ArrayList<>();
            List<折线显示Model> windYb1Lists = new ArrayList<>();
            List<折线显示Model> windYb2Lists = new ArrayList<>();
            for (int i = 0; i < mydataYB.size(); i++) {
                区台格点数值预报站点Model ybls = mydataYB.get(i);
                long myTime = DateUtil.offsetHour(ybls.getMyDate(), ybls.getSX()).getTime();
                if (i == 0 || i == mydataYB.size() - 1) {
                    temYbLists.add(new 折线显示Model(ybls.getTEM(), myTime));
                    tmaYbxLists.add(new 折线显示Model(ybls.getTMAX(), myTime));
                    tminYbLists.add(new 折线显示Model(ybls.getTMIN(), myTime));
                    rhuYbLists.add(new 折线显示Model(ybls.getERH(), myTime));
                    Double winU = ybls.getWIU10();
                    Double winV = ybls.getWIV10();
                    windYb1Lists.add(new 折线显示Model(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(), myTime));
                    if (winV < -99999 || winU < -99999 || winV > 99999 || winU > 99999) {
                        windYb2Lists.add(new 折线显示Model(-999999, null, myTime));
                    } else {
                        windYb2Lists.add(new 折线显示Model(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(), 风向风速转换.GetFX(winV, winU), myTime));
                    }
                } else {
                    double value = ybls.getTEM();
                    if (value < 99999 && value > -99999) {
                        temYbLists.add(new 折线显示Model(value, myTime));
                    }
                    value = ybls.getTMAX();
                    if (value < 99999 && value > -99999) {
                        tmaYbxLists.add(new 折线显示Model(value, myTime));
                    }
                    value = ybls.getTMIN();
                    if (value < 99999 && value > -99999) {
                        tminYbLists.add(new 折线显示Model(value, myTime));
                    }
                    value = ybls.getERH();
                    if (value < 99999 && value > -99999) {
                        rhuYbLists.add(new 折线显示Model(value, myTime));
                    }
                    Double winU = ybls.getWIU10();
                    Double winV = ybls.getWIV10();
                    if (winU < 99999 && winU > -99999 && winV < 99999 && winV > -99999) {
                        windYb1Lists.add(new 折线显示Model(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(), myTime));
                        windYb2Lists.add(new 折线显示Model(NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(), 风向风速转换.GetFX(winV, winU), myTime));
                    }

                }
            }
            double[][] temYbValue = new double[temYbLists.size()][2];
            double[][] TmaxYbValue = new double[tmaYbxLists.size()][2];
            double[][] TminYbValue = new double[tminYbLists.size()][2];
            double[][] RHUYbValue = new double[rhuYbLists.size()][2];
            double[][] Wind1YbValue = new double[windYb1Lists.size()][2];
            Object[][] Wind2YbValue = new Object[windYb2Lists.size()][3];
            myListToArray(temYbValue, temYbLists);
            myListToArray(TmaxYbValue, tmaYbxLists);
            myListToArray(TminYbValue, tminYbLists);
            myListToArray(RHUYbValue, rhuYbLists);
            myListToArray(Wind1YbValue, windYb1Lists);
            windListToArray(Wind2YbValue, windYb2Lists);
            mydata.setYB(temYbValue, TmaxYbValue, TminYbValue, RHUYbValue, Wind1YbValue, Wind2YbValue);
            if (sdate.isBeforeOrEquals(DateUtil.date())) {
                List<Date> myDates = new ArrayList<>();
                List<Date> myDates24 = new ArrayList<>();
                for (int i = 0; i <= 72; i = i + 3) {
                    int finalI = i;
                    Date myDate = DateUtil.offsetHour(sdate, i);
                    if (myDate.compareTo(DateUtil.date()) > 0) {
                        break;
                    }
                    if (i <= 72) {
                        if (!mydataYB.stream().anyMatch(y -> y.getSX() == finalI)) {
                            continue;
                        }
                        myDates.add(myDate);
                        if (i % 24 == 0) {
                            myDates24.add(myDate);
                        }
                    }

                }
                if (myDates.size() > 0) {
                    String ss = Cimiss地面实况.SURF_CHN_MUL_HOR_getSurfEleByTimeAndStaID_JSON(myDates, id, "TEM,RHU,WIN_D_Avg_10mi,WIN_S_Avg_10mi,PRE");
                    JSONArray mys = JSONUtil.parseObj(ss).getJSONArray("DS");
                    List<Cimiss地面小时实况Model> mydataSK = mys.toList(ClassUtil.getClass(new Cimiss地面小时实况Model()));
                    if (!mydataSK.isEmpty() && mydataSK.size() > 0) {
                        String strTmaxTmin = "";
                        if (myDates24.size() > 0) {
                            strTmaxTmin = Cimiss地面实况.SURF_CHN_MUL_HOR_getSurfEle24HourTmaxTminByTimeAndStaID_JSON(myDates24, id);
                            JSONArray jsonObject2 = JSONUtil.parseObj(strTmaxTmin).getJSONArray("DS");
                            List<Cimiss地面小时实况Model> mydataYB2 = jsonObject2.toList(ClassUtil.getClass(new Cimiss地面小时实况Model()));
                            for (Date myDateLS : myDates24
                            ) {
                                for (int i = 0; i < mydataSK.size(); i++) {
                                    if (mydataSK.get(i).getDatetime().compareTo(DateUtil.offsetHour(myDateLS, -8)) == 0) {
                                        List<Cimiss地面小时实况Model> maxYB = mydataYB2.stream().filter(y -> y.getDatetime().compareTo(DateUtil.offsetHour(myDateLS, -8 - 23)) >= 0 && y.getDatetime().compareTo(DateUtil.offsetHour(myDateLS, -8)) <= 0).collect(Collectors.toUnmodifiableList());
                                        mydataSK.get(i).setTEM_Max(maxYB.stream().mapToDouble(y -> y.getTEM_Max()).max().orElse(-999999));
                                        mydataSK.get(i).setTEM_Min(maxYB.stream().mapToDouble(y -> y.getTEM_Min()).min().orElse(-999999));
                                    }
                                }

                            }

                        }

                        List<折线显示Model> temSkLists = new ArrayList<>();
                        List<折线显示Model> tmaSkxLists = new ArrayList<>();
                        List<折线显示Model> tminSkLists = new ArrayList<>();
                        List<折线显示Model> rhuSkLists = new ArrayList<>();
                        List<折线显示Model> windSkLists = new ArrayList<>();
                        for (int i = 0; i < mydataSK.size(); i++) {
                            Cimiss地面小时实况Model ybls = mydataSK.get(i);
                            long myTime = DateUtil.offsetHour(ybls.getDatetime(), 8).getTime();
                            if (i == 0 || i == mydataSK.size() - 1) {
                                temSkLists.add(new 折线显示Model(ybls.getTEM(), myTime));
                                tmaSkxLists.add(new 折线显示Model(ybls.getTEM_Max(), myTime));
                                tminSkLists.add(new 折线显示Model(ybls.getTEM_Min(), myTime));
                                rhuSkLists.add(new 折线显示Model(ybls.getRHU(), myTime));
                                windSkLists.add(new 折线显示Model(ybls.getWIN_S_Avg_10mi(), myTime));
                            } else {
                                double value = ybls.getTEM();
                                if (value < 99999 && value > -99999) {
                                    temSkLists.add(new 折线显示Model(value, myTime));
                                }
                                value = ybls.getTEM_Max();
                                if (value < 99999 && value > -99999) {
                                    tmaSkxLists.add(new 折线显示Model(value, myTime));
                                }
                                value = ybls.getTEM_Min();
                                if (value < 99999 && value > -99999) {
                                    tminSkLists.add(new 折线显示Model(value, myTime));
                                }
                                value = ybls.getRHU();
                                if (value < 99999 && value > -99999) {
                                    rhuSkLists.add(new 折线显示Model(value, myTime));
                                }
                                value = ybls.getWIN_S_Avg_10mi();
                                if (value < 99999 && value > -99999) {
                                    windSkLists.add(new 折线显示Model(value, myTime));
                                }
                            }

                        }
                        double[][] temSkValue = new double[temSkLists.size()][2];
                        double[][] TmaxSkValue = new double[tmaSkxLists.size()][2];
                        double[][] TminSkValue = new double[tminSkLists.size()][2];
                        double[][] RHUSkValue = new double[rhuSkLists.size()][2];
                        double[][] Wind1SkValue = new double[windSkLists.size()][2];
                        myListToArray(temSkValue, temSkLists);
                        myListToArray(TmaxSkValue, tmaSkxLists);
                        myListToArray(TminSkValue, tminSkLists);
                        myListToArray(RHUSkValue, rhuSkLists);
                        myListToArray(Wind1SkValue, windSkLists);
                        mydata.setSK(temSkValue, TmaxSkValue, TminSkValue, RHUSkValue, Wind1SkValue);
                    }

                    mydataSK.size();
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return mydata;
    }

    private void myListToArray(double[][] myArray, List<折线显示Model> temSkLists) {
        for (int i = 0; i < myArray.length; i++) {
            myArray[i][0] = temSkLists.get(i).getTimes();
            myArray[i][1] = temSkLists.get(i).getValue();
        }
    }

    private void windListToArray(Object[][] myArray, List<折线显示Model> temSkLists) {
        for (int i = 0; i < myArray.length; i++) {
            myArray[i][0] = temSkLists.get(i).getTimes();
            myArray[i][1] = temSkLists.get(i).getValue();
            myArray[i][2] = temSkLists.get(i).getValue2();
        }
    }
}
