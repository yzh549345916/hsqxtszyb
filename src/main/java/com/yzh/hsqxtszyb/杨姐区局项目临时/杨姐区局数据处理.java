package com.yzh.hsqxtszyb.杨姐区局项目临时;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yzh.hsqxtszyb.cimiss.风向风速转换;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.util.公用类;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.meteodata.DataInfo;
import org.meteoinfo.data.meteodata.MeteoDataInfo;
import org.meteoinfo.data.meteodata.Variable;
import org.meteoinfo.projection.KnownCoordinateSystems;
import org.meteoinfo.projection.ProjectionInfo;
import org.meteoinfo.projection.ProjectionNames;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class 杨姐区局数据处理 {
    public static void Rmaps格点数据处理(String HomeDirPath, DateTime myDate, int YbSx){
        String myDirPath=HomeDirPath+ "/区台数值预报文件/rmaps数据/" + DateUtil.format(myDate, "yyyyMMdd") + "/";
        DateTime myDateUtc = DateUtil.offsetHour(myDate, -8);
        List<File> myfilesLS = FileUtil.loopFiles(myDirPath, pathname -> pathname.getName().endsWith("ALL_" + DateUtil.format(myDateUtc, "yyyyMMddHHmm")+"_"+String.format("%03d", YbSx) + "01.GRB2"));
        if(!myfilesLS.isEmpty()){
            File myFile=myfilesLS.get(0);
            String myPath=myFile.getPath();
            MeteoDataInfo grib2Data = new MeteoDataInfo();
            grib2Data.openData(myPath);
            DataInfo myDataInfo=grib2Data.getDataInfo();
            grib2Data.setTimeIndex(0);
            grib2Data.setLevelIndex(0);
            ProjectionInfo.factory(ProjectionNames.LongLat);
            GridData myGridDataU = grib2Data.getGridData("u-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataU);
            myGridDataU=myGridDataU.project(ProjectionInfo.factory(ProjectionNames.LongLat));
            GridData myGridDataV = grib2Data.getGridData("v-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataV);
            myGridDataV=myGridDataV.project(ProjectionInfo.factory(ProjectionNames.LongLat));
            Double lonStart=105.0,latStart=37.5;
            Integer lonCount=(int)Math.round((113-lonStart)/0.03);
            Integer latCount=(int)Math.round((42.5-latStart)/0.03);
            Integer Count1=0,Count2=0;
            List<Double> value1List=new ArrayList<>();
            List<Double> value2List=new ArrayList<>();
            for(double lon=lonStart;Count1<lonCount;lon+=0.03,Count1++){
                Count2=0;
                for(double lat=latStart;Count2<latCount;lat+=0.03,Count2++){
                    double winv=myGridDataV.getValue(lon,lat);
                    double winu=myGridDataU.getValue(lon,lat);
                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                    value1List.add(winfsDouble);
                    value2List.add(winfxDouble);
                }
            }
            杨姐区局项目临时数据Model myData=new 杨姐区局项目临时数据Model(lonStart,0.03,lonCount,latStart,0.03,latCount,value1List.toArray(new Double[0]),value2List.toArray(new Double[0]));
            JSONObject json2 = JSONUtil.parseObj(myData,true);

            String myJsonFilePath=获取Rmaps格点Json路径(HomeDirPath,myDate,YbSx);
            var sfs=json2.toStringPretty();
            File myJsonFile = FileUtil.touch(myJsonFilePath);
            FileUtil.writeUtf8String(sfs, myJsonFile);
        }
    }
    public static 地图站点详情数据内容 Rmaps格点详情数据处理(String HomeDirPath, DateTime myDate, int YbSx,Double lon,Double lat){

        String myDirPath=HomeDirPath+ "/区台数值预报文件/rmaps数据/" + DateUtil.format(myDate, "yyyyMMdd") + "/";
        DateTime myDateUtc = DateUtil.offsetHour(myDate, -8);
        List<File> myfilesLS = FileUtil.loopFiles(myDirPath, pathname -> pathname.getName().endsWith("ALL_" + DateUtil.format(myDateUtc, "yyyyMMddHHmm")+"_"+String.format("%03d", YbSx) + "01.GRB2"));
        if(!myfilesLS.isEmpty()){
            File myFile=myfilesLS.get(0);
            String myPath=myFile.getPath();
            MeteoDataInfo grib2Data = new MeteoDataInfo();
            grib2Data.openData(myPath);
            DataInfo myDataInfo=grib2Data.getDataInfo();
            ProjectionInfo mypro = grib2Data.getProjectionInfo();
            StationData stationData = new StationData();
            stationData.addData("wind",lon,lat,-999);
            stationData = stationData.project(ProjectionInfo.factory(ProjectionNames.LongLat), mypro);
            grib2Data.setTimeIndex(0);
            grib2Data.setLevelIndex(0);
            ProjectionInfo.factory(ProjectionNames.LongLat);
            GridData myGridDataU = grib2Data.getGridData("u-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataU);
            StationData myStationDataU =myGridDataU.toStation(stationData);
            GridData myGridDataV = grib2Data.getGridData("v-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataV);
            StationData myStationDataV =myGridDataV.toStation(stationData);
            Double winU=myStationDataU.getValue(0);
            Double winV=myStationDataV.getValue(0);
            return  new 地图站点详情数据内容(DateUtil.format(DateUtil.offsetHour(myDate,YbSx), "MM月dd日HH时"),NumberUtil.round(风向风速转换.GetFS(winV, winU), 1).doubleValue(),风向风速转换.GetFX(winV, winU),风向风速转换.GetFL(winV, winU));
        }
        return null;
    }

    public static String 获取Rmaps格点Json路径(String HomeDirPath, DateTime myDate, int YbSx){
       try{
           SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
           SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHH");
           String datePath1 = df.format(myDate);
           String timePath1 = df2.format(myDate);
           return StrUtil.format(HomeDirPath+"/区台数值预报文件/szyb/json/GDSJ/RMAPS/WIND/{}/RMAPS_wind_{}_{}.json", datePath1,timePath1,String.format("%04d", YbSx) );
       } catch (Exception e) {
           return "";
       }
    }

    public static void 国家智能网格格点数据处理(String HomeDirPath, DateTime myDate, int YbSx){
        String myDirPath=HomeDirPath+ "/区台数值预报文件/szyb/NAFP_NWFD_SCMOC/" + DateUtil.format(myDate, "yyyy-MM-dd") + "/EDA10/";
        List<File> myfilesLS = FileUtil.loopFiles(myDirPath, pathname -> pathname.getName().endsWith("_" + DateUtil.format(myDate, "yyyyMMddHHmm")+"_24003.GRB2"));
        if(!myfilesLS.isEmpty()){
            File myFile=myfilesLS.get(0);
            String myPath=myFile.getPath();
            MeteoDataInfo grib2Data = new MeteoDataInfo();
            grib2Data.openData(myPath);
            DataInfo myDataInfo=grib2Data.getDataInfo();
            Integer ybsxIndex=NumberUtil.round(YbSx/3.0,0).toBigInteger().intValue()-1;
            grib2Data.setTimeIndex(ybsxIndex);
            grib2Data.setLevelIndex(0);
            ProjectionInfo.factory(ProjectionNames.LongLat);
            GridData myGridDataU = grib2Data.getGridData("u-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataU);
            myGridDataU=myGridDataU.project(ProjectionInfo.factory(ProjectionNames.LongLat));
            GridData myGridDataV = grib2Data.getGridData("v-component_of_wind_height_above_ground");
            公用类.格点数据排序(myGridDataV);
            myGridDataV=myGridDataV.project(ProjectionInfo.factory(ProjectionNames.LongLat));
            Double lonStart=105.0,latStart=37.5;
            Integer lonCount=(int)Math.round((113-lonStart)/0.05);
            Integer latCount=(int)Math.round((42.5-latStart)/0.05);
            Integer Count1=0,Count2=0;
            List<Double> value1List=new ArrayList<>();
            List<Double> value2List=new ArrayList<>();
            for(double lon=lonStart;Count1<lonCount;lon+=0.05,Count1++){
                Count2=0;
                for(double lat=latStart;Count2<latCount;lat+=0.05,Count2++){
                    double winv=myGridDataV.getValue(lon,lat);
                    double winu=myGridDataU.getValue(lon,lat);
                    double winfxDouble = NumberUtil.round(风向风速转换.GetFXDouble(winv, winu), 1).doubleValue();
                    double winfsDouble = NumberUtil.round(风向风速转换.GetFS(winv, winu), 1).doubleValue();
                    value1List.add(winfsDouble);
                    value2List.add(winfxDouble);
                }
            }
            杨姐区局项目临时数据Model myData=new 杨姐区局项目临时数据Model(lonStart,0.05,lonCount,latStart,0.05,latCount,value1List.toArray(new Double[0]),value2List.toArray(new Double[0]));
            JSONObject json2 = JSONUtil.parseObj(myData,true);

            String myJsonFilePath=获取国家智能网格格点Json路径(HomeDirPath,myDate,YbSx);
            var sfs=json2.toStringPretty();
            File myJsonFile = FileUtil.touch(myJsonFilePath);
            FileUtil.writeUtf8String(sfs, myJsonFile);
        }
    }
    public static String 获取国家智能网格格点Json路径(String HomeDirPath, DateTime myDate, int YbSx){
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHH");
            String datePath1 = df.format(myDate);
            String timePath1 = df2.format(myDate);
            return StrUtil.format(HomeDirPath+"/区台数值预报文件/szyb/json/GDSJ/ZNWG/WIND/{}/ZNWG_wind_{}_{}.json", datePath1,timePath1,String.format("%04d", YbSx) );
        } catch (Exception e) {
            return "";
        }

    }
}
