package com.yzh.hsqxtszyb.cimiss;

public  class 地面实况数据类型转换 {
    public static String cimiss地面实况数据类型转换(int paramType) {
        String myString = "";
        switch (paramType) {
            case 0:
                myString = "TEM";
                break;
            case 1:
                myString = "RHU";
                break;
            case 2:
                myString = "PRE";
                break;
            case 4:
                myString = "WIN_D_Avg_10mi,WIN_S_Avg_10mi";
                break;
            case 6:
                myString = "PRS";
                break;
            case 7:
                myString = "TEM_Max";
                break;
            case 8:
                myString = "TEM_Min";
                break;
            default:
                myString = "";
                break;
        }
        return myString;
    }
    public static String 地面实况数据类型文字描述转换(int paramType) {
        String myString = "";
        switch (paramType) {
            case 0:
                myString = "气温";
                break;
            case 1:
                myString = "相对湿度";
                break;
            case 2:
                myString = "降水量";
                break;
            case 4:
                myString = "10米风速";
                break;
            case 6:
                myString = "气压";
                break;
            case 7:
                myString = "最高气温";
                break;
            case 8:
                myString = "最低气温";
                break;
            default:
                myString = "气温";
                break;
        }
        return myString;
    }
    public static String 地面预报数据类型单位转换(int paramType) {
        String myString = "";
        switch (paramType) {
            case 0:
                myString = "℃";
                break;
            case 1:
                myString = "%";
                break;
            case 2:
                myString = "毫米";
                break;
            case 4:
                myString = "m/s";
                break;
            case 6:
                myString = "hPa";
                break;
            case 7:
                myString = "℃";
                break;
            case 8:
                myString = "℃";
                break;
            default:
                myString = "℃";
                break;
        }
        return myString;
    }
    public static String 区局数值预报格点预报数据库地面实况数据类型转换(int paramType) {
        String myString = "";
        switch (paramType) {
            case 0:
                myString = "TEM";
                break;
            case 1:
                myString = "ERH";
                break;
            case 2:
                myString = "PRE_3h";
                break;
            case 4:
                myString = "WIU10,WIV10";
                break;
            case 6:
                myString = "PRS";
                break;
            case 7:
                myString = "TMAX";
                break;
            case 8:
                myString = "TMIN";
                break;
            default:
                myString = "TEM";
                break;
        }
        return myString;
    }
}
