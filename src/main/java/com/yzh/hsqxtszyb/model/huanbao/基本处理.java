package com.yzh.hsqxtszyb.model.huanbao;

public class 基本处理 {
    public static String 亚洲沙尘模式单位获取(String dataTypeStr){
        switch (dataTypeStr){
            case  "CONC_DUST" :
            case  "SCONC_DUST" :
                return "ug/m3";
            case  "DDEPO_DUST" :
            case  "LOAD_DUST" :
            case  "WDEPO_DUST" :
                return "ug/m2";
            case  "DFLUX_DUST" :
                return "ug/m2/s";
            case  "U10" :
            case  "V10" :
                return "m/s";
            default:
                return "-";
        }
    }
    public static String 亚洲沙尘模式中文名称(String dataTypeStr){
        switch (dataTypeStr){
            case  "AOD550_DUST" :
                return "550nm沙尘光学厚度";
            case  "CONC_DUST" :
                return "高空沙尘浓度";
            case  "SCONC_DUST" :
                return "地面沙尘浓度";
            case  "DDEPO_DUST" :
                return "3小时累积干沉积";
            case  "LOAD_DUST" :
                return "含尘量";
            case  "WDEPO_DUST" :
                return "3小时累计湿沉降";
            case  "DFLUX_DUST" :
                return "地面沙尘通量";
            case  "U10" :
                return "10米风u分量";
            case  "V10" :
                return "10米风v分量";
            default:
                return "";
        }
    }
    public static String 区台沙尘模式中文名称(String dataTypeStr){
        switch (dataTypeStr){
            case  "PM10" :
                return "PM10";
            case  "PM2.5" :
                return "PM2.5";
            case  "DUSTDRY" :
                return "干沉降";
            case  "EDUST" :
                return "起沙";
            case  "PBLH" :
                return "边界层高度";
            case  "SURFACEDUST" :
                return "地面沙尘浓度";
            default:
                return "";
        }
    }
    public static String 区台沙尘模式单位获取(String dataTypeStr){
        switch (dataTypeStr){
            case  "PM10" :
            case  "PM2.5" :
            case  "SURFACEDUST" :
                return "ug/m3";
            case  "DUSTDRY" :
                return "ug/m2/s";
            case  "EDUST" :
                return "ug/m2";
            case  "PBLH" :
                return "m";
            default:
                return "";
        }
    }
}
