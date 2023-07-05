package com.yzh.hsqxtszyb.service;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.NumberUtil;
import com.yzh.hsqxtszyb.mapper.DuoYuanRongHeSKMapper;
import com.yzh.hsqxtszyb.mapper.YBJYStationsMapper;
import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.model.huanbao.YBJYStations;
import com.yzh.hsqxtszyb.model.预报检验.多元融合检验结果;
import com.yzh.hsqxtszyb.model.预报检验.多源融合实况统计Model;
import com.yzh.hsqxtszyb.util.JianYanSqlSessionFactoryUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.yzh.hsqxtszyb.service.多源融合实况任意时段降水Micaps检索Service.获取合并后文件名;

public class YBJYservice {
    SqlSessionFactory sqlSessionFactory = JianYanSqlSessionFactoryUtil.getSqlSessionFactory();
    SqlSession session = sqlSessionFactory.openSession();
    @Test
    public void 测试(){
        根据起止时间合并多源融合实况降水数据("2022-03-30 11:00:00","2022-03-31 11:00:00");
       /* 根据站点级别起止时间获取多元融合实况检验统计结果("12,13,14","2022-03-20 11:00:00","2022-03-31 11:00:00");
        String sDateStr="2022-03-24 05:00:00";
        String eDateStr="2022-03-29 09:00:00";
        Date sdate= DateUtil.parse(sDateStr);
        Date edate= DateUtil.parse(eDateStr);
        Collection<String> stationIds=new ArrayList<>();
        stationIds.add("53463");
        stationIds.add("53368");
        stationIds.add("53464");
        DuoYuanRongHeSKMapper duoYuanRongHeSKMapper = session.getMapper(DuoYuanRongHeSKMapper.class);
        var fs=duoYuanRongHeSKMapper.selectTByMydatetimeBetweenEqualAndStationidInJoinSK(2022,sdate,edate,stationIds);*/
        int f=1;
        /*根据要素站点级别起止时间获取多元融合实况检验结果("TEM","12,13","2022-03-20 11:00:00","2022-03-21 11:00:00");*/
    }
    public List<多源融合实况统计Model> 根据站点级别起止时间获取多元融合实况检验统计结果( String stationLevlStr, String sDateStr, String eDateStr){
        List<多源融合实况统计Model> resultDataList=new ArrayList<>();
        String[] eleStrSz=new String[]{"TEM","PRE_1h","RHU","DPT"};
        String[] eleChineseStrSz=new String[]{"温度","1小时降水","相对湿度","露点温度"};
        for (int i = 0, eleStrSzLength = eleStrSz.length; i < eleStrSzLength; i++) {
            String ele = eleStrSz[i];
            List<多元融合检验结果> jyList = 根据要素站点级别起止时间获取多元融合实况检验结果(ele, stationLevlStr, sDateStr, eDateStr);
            double jfgwc = 0.0, pjjdwc = 0.0,pcjz=0.0;
            String jsInfo="";
            for (多元融合检验结果 jyItem : jyList
            ) {
                double cz = jyItem.getCZValue();
                jfgwc = NumberUtil.add(jfgwc, NumberUtil.pow(cz, 2).doubleValue());
                pjjdwc = NumberUtil.add(pjjdwc, Math.abs(cz));
                if(pcjz<Math.abs(cz)){
                    pcjz=Math.abs(cz);
                    jsInfo=cz+"("+jyItem.getStationIdC()+"站"+ DateUtil.format(jyItem.getDatetime(), "yyyy年MM月dd日HH时")+")";
                }
            }
            jfgwc = NumberUtil.div(jfgwc, jyList.size());
            jfgwc = Math.sqrt(jfgwc);
            pjjdwc = NumberUtil.div(pjjdwc, jyList.size());
            resultDataList.add(new 多源融合实况统计Model(ele,eleChineseStrSz[i],NumberUtil.round(pjjdwc,3).doubleValue(),jyList.size(),NumberUtil.round(jfgwc,3).doubleValue(),jsInfo));
        }
        return  resultDataList;
    }

    public List<多元融合检验结果> 根据要素站点级别起止时间获取多元融合实况检验结果(String eleName,String stationLevlStr,String sDateStr,String eDateStr){
        List<多元融合检验结果> dataResults=new ArrayList<>();
        if(!stationLevlStr.isBlank()){
            String ybname="所有自动站信息";
            Collection<Integer> stationLevlCollection = new ArrayList<>();
            for (String levelStr:stationLevlStr.split(",")
                 ) {
                try{
                    stationLevlCollection.add(Integer.valueOf(levelStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            List<YBJYStations> stations=根据预报名称站点级别查询站点(ybname,stationLevlCollection);
            Collection<String> stationIds=new ArrayList<>();
            for (YBJYStations station:stations
                 ) {
                stationIds.add(station.getStationid());
            }
            dataResults= 多元融合实况检验数据处理(sDateStr,eDateStr,eleName,stationIds);
        }
        return dataResults;
    }

    public  List<多元融合检验结果> 根据要素站点起止时间获取多元融合实况检验结果(String eleName,String stationIDs,String sDateStr,String eDateStr){
        List<多元融合检验结果> dataResults=new ArrayList<>();
        if(!stationIDs.isBlank()){
            Collection<String> stationIds=new ArrayList<>();
            for (String stationID:stationIDs.split(",")
            ) {
                try{
                    stationIds.add(stationID);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            dataResults= 多元融合实况检验数据处理(sDateStr,eDateStr,eleName,stationIds);
        }
        return dataResults;
    }
    private  List<多元融合检验结果> 多元融合实况检验数据处理(String sDateStr,String eDateStr,String eleName,Collection<String> stationIds){
        Date sdate= DateUtil.parse(sDateStr);
        Date edate= DateUtil.parse(eDateStr);
        List<Integer> yearLis=new ArrayList<>();
        if(DateUtil.year(sdate)!=DateUtil.year(edate)){
            Integer yearLS=DateUtil.year(sdate);
            while (yearLS<=DateUtil.year(edate)){
                yearLis.add(yearLS++);
            }
        }else{
            yearLis.add(DateUtil.year(sdate));
        }
        DuoYuanRongHeSKMapper duoYuanRongHeSKMapper = session.getMapper(DuoYuanRongHeSKMapper.class);
        List<多元融合检验结果> dataResults=new ArrayList<>();
        for (Integer year:yearLis
        ) {
            switch (eleName){
                //气温
                case "TEM":
                    dataResults.addAll(duoYuanRongHeSKMapper.selectTByMydatetimeBetweenEqualAndStationidInJoinSK(year,sdate,edate,stationIds));
                    break;
                //露点温度
                case "DPT":
                    dataResults.addAll(duoYuanRongHeSKMapper.selectTdByMydatetimeBetweenEqualAndStationidInJoinSK(year,sdate,edate,stationIds));
                    break;
                //相对湿度
                case "RHU":
                    dataResults.addAll(duoYuanRongHeSKMapper.selectRHByMydatetimeBetweenEqualAndStationidInJoinSK(year,sdate,edate,stationIds));
                    break;
                //1小时降水量
                case "PRE_1h":
                    dataResults.addAll(duoYuanRongHeSKMapper.selectR1ByMydatetimeBetweenEqualAndStationidInJoinSK(year,sdate,edate,stationIds));
                    break;
                default:
                    break;
            }
        }
        Collection<Integer> stationLevlCollection=new ArrayList<>();
        stationLevlCollection.add(11);
        stationLevlCollection.add(12);
        stationLevlCollection.add(13);
        stationLevlCollection.add(14);
        List<YBJYStations> stations=根据预报名称站点级别查询站点("所有自动站信息",stationLevlCollection);
        if(!dataResults.isEmpty()){
            dataResults=dataResults.stream().sorted(Comparator.comparing(多元融合检验结果::getDatetime).thenComparing(多元融合检验结果::getStationIdC)).collect(Collectors.toList());
            String idLS="";
            String nameLS="";
            for (多元融合检验结果 skItem:dataResults
            ) {
                try{
                    if(!skItem.getStationIdC().equals(idLS)){
                        idLS=skItem.getStationIdC();
                        String finalIdLS = idLS;
                        nameLS=stations.stream().filter(y->y.getStationid().equals(finalIdLS)).findFirst().get().getStationname();
                    }
                    skItem.setDYSKValue(NumberUtil.round(skItem.getDYSKValue(),1).doubleValue());
                    skItem.setStationSKValue(NumberUtil.round(skItem.getStationSKValue(),1).doubleValue());
                    skItem.setCZValue(NumberUtil.round(skItem.getDYSKValue()-skItem.getStationSKValue(),1).doubleValue());
                    skItem.setStationName(nameLS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dataResults;
    }
    private String 根据ID检索站名(List<YBJYStations> stations,String stationID){
        String name="";
        try{
            for (YBJYStations station:stations
                 ) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    public List<YBJYStations> 根据预报名称站点级别查询站点(String ybname, Collection<Integer> stationLevlCollection) {
        YBJYStationsMapper stationsMapper = session.getMapper(YBJYStationsMapper.class);
        List<YBJYStations> mydata = stationsMapper.selectAllByYbnameAndStationLevlIn(ybname,stationLevlCollection);
        return mydata;
    }
    public List<YBJYStations> 根据预报名称站点级别地区查询站点(String ybname, Collection<Integer> stationLevlCollection,@Param("AdminCodeReg")String AdminCodeReg) {
        YBJYStationsMapper stationsMapper = session.getMapper(YBJYStationsMapper.class);
        List<YBJYStations> mydata = stationsMapper.selectAllByYbnameAndStationLevlInAndAdminCodeReg(ybname,stationLevlCollection,AdminCodeReg);
        return mydata;
    }
    public List<web站点信息> 根据站点级别获取多源融合实况站点(String stationLevlStr){
        List<web站点信息> myStations = new ArrayList<>();
        if(!stationLevlStr.isBlank()) {
            String ybname = "所有自动站信息";
            Collection<Integer> stationLevlCollection = new ArrayList<>();
            for (String levelStr : stationLevlStr.split(",")
            ) {
                try {
                    stationLevlCollection.add(Integer.valueOf(levelStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            List<YBJYStations> stations = 根据预报名称站点级别查询站点(ybname, stationLevlCollection);
            if(!stations.isEmpty()){
                for (YBJYStations station:stations
                     ) {
                    myStations.add(new web站点信息(station.getStationid(), station.getStationname()+ "," + station.getStationid(), false));
                }
            }
        }
        return myStations;
    }
    public List<web站点信息> 根据站点级别地区ID获取多源融合实况站点(String adminCode,String stationLevlStr){
        List<web站点信息> myStations = new ArrayList<>();
        String AdminCodeReg="";
        if(!stationLevlStr.isBlank()) {
            String ybname = "所有自动站信息";
            Collection<Integer> stationLevlCollection = new ArrayList<>();
            for (String levelStr : stationLevlStr.split(",")
            ) {
                try {
                    stationLevlCollection.add(Integer.valueOf(levelStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (adminCode == null) {
                AdminCodeReg="15";
            } else {
                String[] idSz=adminCode.split(",");
                if(idSz.length>1){
                    String myIDs="";
                    for (String myID:idSz
                    ) {
                        myIDs+="^"+myID+"|";
                    }
                    AdminCodeReg=myIDs.substring(0,myIDs.length()-1);
                }else{
                    AdminCodeReg="^"+adminCode;
                }

            }

            List<YBJYStations> stations = 根据预报名称站点级别地区查询站点(ybname, stationLevlCollection,AdminCodeReg);
            if(!stations.isEmpty()){
                for (YBJYStations station:stations
                ) {
                    myStations.add(new web站点信息(station.getStationid(), station.getStationname()+ "," + station.getStationid(), false));
                }
            }
        }
        return myStations;
    }

    public ResponseEntity<FileSystemResource> 根据起止时间合并多源融合实况降水数据(String sDateStr, String eDateStr){
        try{
           /* ApplicationHome appHome = new ApplicationHome(getClass());
            File jarF = appHome.getSource();
            String myDataPath=jarF.getParentFile().toString()+"/DataTemp/DYRH/Pre/";*/
            String filePath=获取合并后文件名(sDateStr,eDateStr);
            File myFile=new File(filePath);
            if(!FileUtil.exist(myFile)){
                多源融合实况任意时段降水Micaps检索Service.根据起止时间合并多源融合实况降水数据(sDateStr,eDateStr);
                return export(myFile);
            }
           return export(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        /*headers.add("Content-Disposition", "attachment; filename=" + file.getName());*/
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", URLEncoder.encode(file.getName(), StandardCharsets.UTF_8)));
        headers.add("fileName",  URLEncoder.encode(file.getName(), StandardCharsets.UTF_8));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        headers.add("Access-Control-Expose-Headers", "content-disposition");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
}
