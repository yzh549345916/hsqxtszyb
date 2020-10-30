package com.yzh.hsqxtszyb.cimiss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import cma.cimiss.client.DataQueryClient;
import cma.cimiss.util.FormatUtil;
import cn.hutool.core.date.DateUtil;


public class Cimiss地面实况 {


    /*
     * 根据时间段（世界时）、站号、要素检索中国地面逐小时资料，返回JSON格式字符串
     */

    /**
     *
     * @param sDate:开始时间，北京时
     * @param eDate:结束时间，北京时
     * @param stationID:区站号，如果多个则以半角逗号分割
     * @param dataType:要素种类，如果多个则以半角逗号分割
     * @return 返回Json字符串，如果数据异常则返回空字符串
     */

    public static String SURF_CHN_MUL_HOR_getSurfEleByTimeRangeAndStaID_JSON(Date sDate,Date eDate,String stationID,String dataType) {

        /* 1. 定义client对象 */
        DataQueryClient client = new DataQueryClient() ;

        /* 2. 调用方法的参数定义，并赋值 */
        /* 2.1 用户名&密码 */
        String userId = "BEHT_BFHT_2131" ;
        String pwd = "YZHHGDJM" ;
        /* 2.2  接口ID */
        String interfaceId = "getSurfEleByTimeRangeAndStaID" ;
        /* 2.3  接口参数，多个参数间无顺序 */
        HashMap<String, String> params = new HashMap<String, String>();
        //必选参数
        params.put("dataCode", "SURF_CHN_MUL_HOR") ; //资料代码
        params.put("elements", "Station_Id_C,Station_Name,Datetime,"+dataType) ;//检索要素：站号、站名、小时降水、气压、相对湿度、能见度、2分钟平均风速、2分钟风向
        String myDateFormat="yyyyMMddHHmmss";
        params.put("timeRange", "["+DateUtil.format(DateUtil.offsetHour(sDate, -8),myDateFormat)+","+DateUtil.format(DateUtil.offsetHour(eDate, -8),myDateFormat)+"]") ; //检索时间
        params.put("staIds", stationID) ;
        //可选参数
        //params.put("orderby", "Station_ID_C:ASC") ; //排序：按照站号从小到大
        // params.put("limitCnt", "10") ; //返回最多记录数：10
        /* 2.4 返回文件的格式 */
        String dataFormat = "json" ;
        /* 2.5 返回字符串 */
        StringBuffer retStr = new StringBuffer() ;
        /* 3. 调用接口 */
        try {
            //初始化接口服务连接资源
            client.initResources() ;
            //调用接口
            int rst = client.callAPI_to_serializedStr(userId, pwd, interfaceId, params, dataFormat, retStr) ;
            //输出结果
            //正常返回
            if(rst == 0) {
                FormatUtil formatUtil = new FormatUtil() ;

                return formatUtil.outputRstJson( retStr.toString() );

            } else {
                System.out.println( "[error] Cimiss地面实况." ) ;
                System.out.printf( "\treturn code: %d. \n", rst ) ;
            }
        } catch (Exception e) {
            //异常输出
            e.printStackTrace() ;
        } finally {
            //释放接口服务连接资源
            client.destroyResources() ;
        }
        return "";
    }

    /*
     * 根据时间点（世界时）、站号、要素检索中国地面逐小时资料，返回JSON格式字符串
     */

    /**
     *
     * @param szDate:时间数组，北京时
     * @param stationID:区站号，如果多个则以半角逗号分割
     * @param dataType:要素种类，如果多个则以半角逗号分割
     * @return 返回Json字符串，如果数据异常则返回空字符串
     */
    public static String SURF_CHN_MUL_HOR_getSurfEleByTimeAndStaID_JSON(List<Date> szDate,String stationID,String dataType) {

        /* 1. 定义client对象 */
        DataQueryClient client = new DataQueryClient() ;

        /* 2. 调用方法的参数定义，并赋值 */
        /* 2.1 用户名&密码 */
        String userId = "BEHT_BFHT_2131" ;
        String pwd = "YZHHGDJM" ;
        /* 2.2  接口ID */
        String interfaceId = "getSurfEleByTimeAndStaID" ;
        /* 2.3  接口参数，多个参数间无顺序 */
        HashMap<String, String> params = new HashMap<String, String>();
        //必选参数
        params.put("dataCode", "SURF_CHN_MUL_HOR") ; //资料代码
        params.put("elements", "Station_Id_C,Station_Name,Datetime,"+dataType) ;//检索要素：站号、站名、小时降水、气压、相对湿度、能见度、2分钟平均风速、2分钟风向
        String myDateFormat="yyyyMMddHH0000";
        StringBuilder sb=new StringBuilder();
        for (Date myDate:szDate
             ) {
            sb.append(DateUtil.format(DateUtil.offsetHour(myDate, -8),myDateFormat)).append(',');
        }
        params.put("times", sb.deleteCharAt(sb.length() - 1).toString()) ; //检索时间
        params.put("staIds", stationID) ;
        //可选参数
        //params.put("orderby", "Station_ID_C:ASC") ; //排序：按照站号从小到大
        // params.put("limitCnt", "10") ; //返回最多记录数：10
        /* 2.4 返回文件的格式 */
        String dataFormat = "json" ;
        /* 2.5 返回字符串 */
        StringBuffer retStr = new StringBuffer() ;
        /* 3. 调用接口 */
        try {
            //初始化接口服务连接资源
            client.initResources() ;
            //调用接口
            int rst = client.callAPI_to_serializedStr(userId, pwd, interfaceId, params, dataFormat, retStr) ;
            //输出结果
            //正常返回
            if(rst == 0) {
                FormatUtil formatUtil = new FormatUtil() ;

                return formatUtil.outputRstJson( retStr.toString() );

            } else {
                System.out.println( "[error] Cimiss地面实况." ) ;
                System.out.printf( "\treturn code: %d. \n", rst ) ;
            }
        } catch (Exception e) {
            //异常输出
            e.printStackTrace() ;
        } finally {
            //释放接口服务连接资源
            client.destroyResources() ;
        }
        return "";
    }

    public static String SURF_CHN_MUL_HOR_getSurfEle24HourTmaxTminByTimeAndStaID_JSON(List<Date> szDate,String stationID) {

        /* 1. 定义client对象 */
        DataQueryClient client = new DataQueryClient() ;

        /* 2. 调用方法的参数定义，并赋值 */
        /* 2.1 用户名&密码 */
        String userId = "BEHT_BFHT_2131" ;
        String pwd = "YZHHGDJM" ;
        /* 2.2  接口ID */
        String interfaceId = "getSurfEleByTimeAndStaID" ;
        /* 2.3  接口参数，多个参数间无顺序 */
        HashMap<String, String> params = new HashMap<String, String>();
        //必选参数
        params.put("dataCode", "SURF_CHN_MUL_HOR") ; //资料代码
        params.put("elements", "Station_Id_C,Station_Name,Datetime,TEM_Max,TEM_Min") ;//检索要素：站号、站名、小时降水、气压、相对湿度、能见度、2分钟平均风速、2分钟风向
        String myDateFormat="yyyyMMddHH0000";
        StringBuilder sb=new StringBuilder();
        for (Date myDate:szDate
        ) {
            Date myDatetime= DateUtil.offsetHour(myDate, -8);
            for(int i=0;i>-24;i--){
                sb.append(DateUtil.format(DateUtil.offsetHour(myDatetime, i),myDateFormat)).append(',');
            }

        }
        params.put("times", sb.deleteCharAt(sb.length() - 1).toString()) ; //检索时间
        params.put("staIds", stationID) ;
        //可选参数
        //params.put("orderby", "Station_ID_C:ASC") ; //排序：按照站号从小到大
        // params.put("limitCnt", "10") ; //返回最多记录数：10
        /* 2.4 返回文件的格式 */
        String dataFormat = "json" ;
        /* 2.5 返回字符串 */
        StringBuffer retStr = new StringBuffer() ;
        /* 3. 调用接口 */
        try {
            //初始化接口服务连接资源
            client.initResources() ;
            //调用接口
            int rst = client.callAPI_to_serializedStr(userId, pwd, interfaceId, params, dataFormat, retStr) ;
            //输出结果
            //正常返回
            if(rst == 0) {
                FormatUtil formatUtil = new FormatUtil() ;

                return formatUtil.outputRstJson( retStr.toString() );

            } else {
                System.out.println( "[error] Cimiss地面实况." ) ;
                System.out.printf( "\treturn code: %d. \n", rst ) ;
            }
        } catch (Exception e) {
            //异常输出
            e.printStackTrace() ;
        } finally {
            //释放接口服务连接资源
            client.destroyResources() ;
        }
        return "";
    }

}
