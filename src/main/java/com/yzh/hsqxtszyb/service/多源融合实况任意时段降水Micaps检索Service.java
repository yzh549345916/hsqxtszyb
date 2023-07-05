package com.yzh.hsqxtszyb.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.meteodata.DataInfo;
import org.meteoinfo.data.meteodata.MeteoDataInfo;

import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class 多源融合实况任意时段降水Micaps检索Service {
    @Test
    public void cs(){
        String sDateStr="2022-04-11 05:00:00";
        Date sdate= DateUtil.parse(sDateStr);
        String eDateStr="2022-04-12 05:00:00";
        Date edate= DateUtil.parse(eDateStr);
        根据起止时间合并多源融合实况降水数据(sDateStr,eDateStr);

    }

    public static void 根据起止时间合并多源融合实况降水数据(String sDateStr, String eDateStr){
        try{
            Date sDate=DateUtil.parse(sDateStr);
            Date eDate=DateUtil.parse(eDateStr);
            Micaps时间段同步(sDate,eDate);
            Date myDate=sDate;
            GridData myGridData = null;
            while(myDate.compareTo(eDate)<=0){
                try{
                   try{
                       String sFilePath=根据时间获Micaps文件名(myDate);
                       MeteoDataInfo meteoDataInfo=new MeteoDataInfo();
                       meteoDataInfo.openMICAPSData(sFilePath);
                       GridData tempGridData = meteoDataInfo.getGridData();
                       if(myGridData==null){
                           myGridData=tempGridData;
                       }else{
                           myGridData=myGridData.add(tempGridData);
                       }
                       meteoDataInfo.close();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                    myDate=DateUtil.offsetHour(myDate,1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            StringBuilder stringBuilder=new StringBuilder();

            stringBuilder.append(" diamond 4 ").append(DateUtil.format(sDate, "yyyy年MM月dd日HH时")+"至"+DateUtil.format(eDate, "yyyy年MM月dd日HH时")+"累计降水量(mm) ").append(DateUtil.format(eDate, "yy ")).append(DateUtil.format(eDate, "MM ")).append(DateUtil.format(eDate, "dd HH 0 9999 "));
            stringBuilder.append(NumberUtil.sub(myGridData.getXArray()[1],myGridData.getXArray()[0])).append(" ").append(NumberUtil.sub(myGridData.getYArray()[1],myGridData.getYArray()[0])).append(" ");
            stringBuilder.append(myGridData.getXArray()[0]).append(" ").append(myGridData.getXArray()[myGridData.getXArray().length-1]).append(" ");
            stringBuilder.append(myGridData.getYArray()[0]).append(" ").append(myGridData.getYArray()[myGridData.getYArray().length-1]).append(" ");
            stringBuilder.append(myGridData.getXArray().length).append(" ").append(myGridData.getYArray().length).append(" 1.000000 0.000000 10.00000 0.000000\r\n");
            for(int i=0;i<myGridData.getYArray().length;i++){
                for(int j=0;j<myGridData.getXArray().length;j++){
                    var fd1=myGridData.getValue(i,j);
                   try{
                       stringBuilder.append(NumberUtil.roundStr(fd1.doubleValue(),1)) .append(" ");
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                }
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                stringBuilder.append("\r\n");
            }
            if(stringBuilder.length()>2){
                stringBuilder.delete(stringBuilder.length()-3,stringBuilder.length()-1);
            }
            String myFilePath = 获取合并后文件名(sDateStr,eDateStr);
            try (BufferedWriter writer =
                         Files.newBufferedWriter(Paths.get(myFilePath), StandardCharsets.UTF_8)) {
                writer.write(stringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String 获取合并后文件名(String sDateStr, String eDateStr){
        String myDataPath=多源融合实况任意时段降水Micaps检索Service.获取跟路径()+"/DataTemp/DYRH/Pre/Data/";
        FileUtil.mkdir(myDataPath);
        Date sDate=DateUtil.parse(sDateStr);
        Date eDate=DateUtil.parse(eDateStr);
        return myDataPath+DateUtil.format(sDate, "yyyy年MM月dd日HH时")+"至"+DateUtil.format(eDate, "yyyy年MM月dd日HH时")+"累计降水量."+String.format("%03d",DateUtil.between(sDate,eDate, DateUnit.HOUR,true));
    }
    private static void Micaps时间段同步(Date sDate,Date eDate){
        Date myDate=sDate;
        while(myDate.compareTo(eDate)<=0){
          try{
              Micaps同步(myDate);
              myDate=DateUtil.offsetHour(myDate,1);
          } catch (Exception e) {
              e.printStackTrace();
          }
        }
    }
    private static void Micaps同步(Date myDate){
        try{
            String myFileName=DateUtil.format(myDate, "yyyyMMddHH")+"00.000";
            Ftp ftp = new Ftp("172.18.142.213", 21, "qxt", "qxt4348050", CharsetUtil.CHARSET_UTF_8);
            ftp.setBackToPwd(true);
            ftp.setMode(FtpMode.Passive);
            String ftpDPath=根据时间获取Micaps数据ftp文件夹(myDate,"r1");
            try{
                List<FTPFile> ftpFiles=ftp.lsFiles(ftpDPath, ftpFile -> ftpFile.getName().contains(myFileName));
                if(ftpFiles.size()>0){
                    String filePath=根据时间获Micaps文件名(myDate);
                    if(!FileUtil.exist(filePath)){
                        ftp.download(ftpDPath, myFileName, FileUtil.file(filePath));
                    }
                }
            } catch (IORuntimeException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String 根据时间获Micaps文件名(Date myDate){
        return 根据时间获取Micaps数据保存文件夹(myDate)+(new SimpleDateFormat("yyyyMMddHH")).format(myDate)+"00.000";
    }
    public static String 根据时间获取Micaps数据保存文件夹(Date myDate){
        String myDirNameSaveBase="";
        try{
            String format1 = (new SimpleDateFormat("yyyy/MM/")).format(myDate);
            myDirNameSaveBase = 获取跟路径() + "/DataTemp/DYRH/Pre/Source/"+format1;
            if (!FileUtil.exist(myDirNameSaveBase)) {
                FileUtil.mkdir(myDirNameSaveBase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDirNameSaveBase;
    }
    public static String 根据时间获取Micaps数据ftp文件夹(Date myDate,String ys){
        String myDirNameSaveBase="";
        try{
            String format1 = (new SimpleDateFormat("yyyy/MM/")).format(myDate);
            myDirNameSaveBase =  "数据/数值预报/多源融合实况/Micaps/"+ys+"/"+format1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDirNameSaveBase;
    }
    public static String 获取跟路径(){
        String path=FileUtil.getParent(new ClassPathResource("config").getAbsolutePath(), 2);
        if(path.contains("BOOT-INF")){
            return FileUtil.getParent(new ClassPathResource("config").getAbsolutePath(), 4);
        }else {
            return path;
        }
    }
}
