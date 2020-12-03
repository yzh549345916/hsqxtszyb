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
            case 1900:
                myString = "能见度";
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
            case 1900:
                myString = "m";
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
    public static String Rmaps预报数据库地面实况数据类型转换(int paramType,int levelType,double level) {
        if(paramType==0){
            if(levelType==1){
                return "TEM_surface";
            }else if(levelType==100){
                if(level==50000){
                    return "TEM_isobaric_500";
                }else if(level==70000){
                    return "TEM_isobaric_700";
                }else if(level==85000){
                    return "TEM_isobaric_850";
                }
            }else if(levelType==103){
                return "TEM";
            }
        }
        else if(paramType==2){
            return "PRE";
        }
        else if(paramType==4){
            if(levelType==100){
                if(level==50000){
                    return "WIU10_isobaric_500";
                }else if(level==70000){
                    return "WIU10_isobaric_700";
                }else if(level==85000){
                    return "WIU10_isobaric_850";
                }
            }else if(levelType==103){
                return "WIU10,WIV10";
            }
        }
        else if(paramType==5){
            if(levelType==100){
                if(level==50000){
                    return "WIV10_isobaric_500";
                }else if(level==70000){
                    return "WIV10_isobaric_700";
                }else if(level==85000){
                    return "WIV10_isobaric_850";
                }
            }else if(levelType==103){
                return "WIV10";
            }
        }
        else if(paramType==1900){
            return "VIS";
        }
        else if(paramType==603){
            return "Low_cloud";
        }
        else if(paramType==604){
            return "Medium_cloud";
        }
        else if(paramType==605){
            return "High_cloud";
        }
        else if(paramType==301){
            return "PRS";
        }
        else if(paramType==1){
            if(levelType==100){
                if(level==50000){
                    return "RHU_isobaric_500";
                }else if(level==70000){
                    return "RHU_isobaric_700";
                }else if(level==85000){
                    return "RHU_isobaric_850";
                }
            }else if(levelType==103){
                return "RHU";
            }
        }
        else if(paramType==305){
            if(levelType==100){
                if(level==50000){
                    return "Geopotential_height_500";
                }else if(level==70000){
                    return "Geopotential_height_700";
                }else if(level==85000){
                    return "Geopotential_height_850";
                }
            }
        }
        return "";
    }
}
