package com.yzh.hsqxtszyb.dao;

import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.model.EC.ECDataModel;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StationDaoImpl extends BaseDaoImpl implements StationDao {
    public List<站点信息> 获取站点信息() {
        SqlSession sqlSession = getSqlSession();
        List<站点信息> stations = null;
        try {
            stations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.获取站点信息");
        } finally {
            sqlSession.close();
        }
        return stations;
    }
    @Override
    public List<区台格点数值预报站点Model> select_Szyb_GD_ZD_StationId_date(@Param("ID")String ID,@Param("DateString")String DateString, @Param("DataType") String DataType){
        SqlSession sqlSession = getSqlSession();
        List<区台格点数值预报站点Model> mydatas = new ArrayList<>();
        int count = 0;
        try {
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Szyb_GD_ZD_StationId_date");
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }

    @Override
    public List<web站点信息> 根据省份ID获取盟市(web站点信息 myStation) {
        SqlSession sqlSession = getSqlSession();
        List<web站点信息> stations = null;
        try {
            stations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据省份ID获取盟市", myStation);
            for (web站点信息 station : stations
            ) {
                station.setHasChildren(true);
            }
            stations.size();
        } finally {
            sqlSession.close();
        }
        return stations;
    }
    public List<web站点信息> 根据省份ID获取省份(web站点信息 myStation) {
        SqlSession sqlSession = getSqlSession();
        List<web站点信息> stations = null;
        try {
            stations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据省份ID获取省份", myStation);
            for (web站点信息 station : stations
            ) {
                station.setHasChildren(true);
            }
            stations.size();
        } finally {
            sqlSession.close();
        }
        return stations;
    }
    public List<web站点信息> 根据盟市ID获取盟市(web站点信息 myStation) {
        SqlSession sqlSession = getSqlSession();
        List<web站点信息> stations = null;
        try {
            stations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据盟市ID获取盟市", myStation);
            for (web站点信息 station : stations
            ) {
                station.setHasChildren(true);
            }
            stations.size();
        } finally {
            sqlSession.close();
        }
        return stations;
    }
    @Override
    public int 根据地区信息获取站点个数(web站点信息 data) {
        SqlSession sqlSession = getSqlSession();
        int n = 0;
        try {
            String myID=data.getID()+"%";
            n = sqlSession.selectOne("com.yzh.hsqxtszyb.dao.StationDao.根据地区信息获取站点个数", myID);
        } finally {
            sqlSession.close();
        }
        return n;
    }
    @Override
    public List<站点信息> 根据地区信息获取站点(web站点信息 data) {
        SqlSession sqlSession = getSqlSession();
        List<站点信息> myStations=new ArrayList<>();
        try {
            data.setID(data.getID()+"%");
            myStations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据地区信息获取站点", data);
        } finally {
            sqlSession.close();
        }
        return myStations;
    }
    public List<站点信息> 根据地区信息站点类型获取站点(web站点检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<站点信息> myStations=new ArrayList<>();
        try {
            //data.setID(data.getID()+"%");
            data.setStationLevlString("("+data.getStationLevlString()+")");
            myStations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.GetStationByIdAndStationType", data);
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            sqlSession.close();
        }
        return myStations;
    }
    public List<站点信息> 根据地区信息站点类型获取Rmaps站点(web站点检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<站点信息> myStations=new ArrayList<>();
        try {
            //data.setID(data.getID()+"%");
            data.setStationLevlString("("+data.getStationLevlString()+")");
            myStations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.GetRmapsStationByIdAndStationType", data);
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            sqlSession.close();
        }
        return myStations;
    }
    @Override
    public List<web站点信息> 根据盟市ID获取旗县区(web站点信息 myStation) {
        SqlSession sqlSession = getSqlSession();
        List<web站点信息> stations = null;
        try {
            stations = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据盟市ID获取旗县区", myStation);
            stations.removeIf(y->根据地区信息获取站点个数(y)<=0);
            for (web站点信息 station : stations
            ) {
                station.setHasChildren(true);
            }
        } finally {
            sqlSession.close();
        }
        return stations;
    }

    public List<User> 获取指定ID用户信息(User myuser) {
        SqlSession sqlSession = getSqlSession();
        List<User> users = null;
        try {
            users = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.获取指定ID用户信息", myuser);
        } finally {
            sqlSession.close();
        }
        return users;
    }


    @Override
    public List<区台格点数值预报站点Model> 获取区台格点数值预报站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<区台格点数值预报站点Model> mydatas = new ArrayList<>();
        int count = 0;
        try {
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Szyb_GD_ZD_StationId_date", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<站点信息> 根据站点ID列表获取站点信息(web站点检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<站点信息> mydatas;
        try {
            StringBuilder sb1=new StringBuilder();
            sb1.append("(");
            for (String myid:data.getID().split(",")
            ) {
                sb1.append("'").append(myid).append("'").append(",");
            }
            data.setID(sb1.substring(0,sb1.length()-1)+")");
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.根据ID获取站点信息", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<区台格点数值预报站点Model> 根据站点列表获取区台格点数值预报站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<区台格点数值预报站点Model> mydatas;
        try {
            StringBuilder sb1=new StringBuilder();
            sb1.append("(");
            for (String myid:data.getID().split(",")
                 ) {
                sb1.append("'").append(myid).append("'").append(",");
            }
            data.setID(sb1.substring(0,sb1.length()-1)+")");
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Szyb_GD_ZD_StationIds_date", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<区台格点数值预报站点Model> 根据站点列表预报时效获取区台格点数值预报站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<区台格点数值预报站点Model> mydatas;
        try {
            StringBuilder sb1=new StringBuilder();
            sb1.append("(");
            for (String myid:data.getID().split(",")
            ) {
                sb1.append("'").append(myid).append("'").append(",");
            }
            data.setID(sb1.substring(0,sb1.length()-1)+")");
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Szyb_GD_ZD_StationIds_date_SX", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<Rmaps格点数值预报站点Model> 根据站点列表预报时效获取Rmaps数值预报站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<Rmaps格点数值预报站点Model> mydatas;
        try {
            StringBuilder sb1=new StringBuilder();
            sb1.append("(");
            for (String myid:data.getID().split(",")
            ) {
                sb1.append("'").append(myid).append("'").append(",");
            }
            data.setID(sb1.substring(0,sb1.length()-1)+")");
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Rmaps_GD_ZD_StationIds_date_SX", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<Rmaps格点数值预报站点Model> 根据站点列表预报获取Rmaps数值预报站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<Rmaps格点数值预报站点Model> mydatas;
        try {
            StringBuilder sb1=new StringBuilder();
            sb1.append("(");
            for (String myid:data.getID().split(",")
            ) {
                sb1.append("'").append(myid).append("'").append(",");
            }
            data.setID(sb1.substring(0,sb1.length()-1)+")");
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_Rmaps_GD_ZD_StationIds_date", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }
    public List<区台格点数值预报站点Model> 获取区台格点数值预报全部类型站点预报(数值预报检索Model data) {
        SqlSession sqlSession = getSqlSession();
        List<区台格点数值预报站点Model> mydatas = new ArrayList<>();
        int count = 0;
        try {
            mydatas = sqlSession.selectList("com.yzh.hsqxtszyb.dao.StationDao.select_All_Szyb_GD_ZD_StationId_date", data);
        } finally {
            sqlSession.close();
        }
        return mydatas;
    }

    public List<ECDataModel> 根据站点列表预报获取EC数值预报站点预报(数值预报检索Model data){
        SqlSessionFactory sqlSessionFactoryEc = SqlSessionFactoryUtil.getECFactory();
        SqlSession sessionEc = sqlSessionFactoryEc.openSession();
        EcSurfaceDao ecDao = sessionEc.getMapper(EcSurfaceDao.class);
        StringBuilder sb1=new StringBuilder();
        sb1.append("(");
        for (String myid:data.getID().split(",")
        ) {
            sb1.append("'").append(myid).append("'").append(",");
        }
        data.setID(sb1.substring(0,sb1.length()-1)+")");
        List<ECDataModel> datas ;
        try{
           datas=ecDao.getECSurface(data.getTableName(),data.getID(),data.getDateString(),data.getDataType());
        }
        finally {
            sessionEc.close();
        }
        return datas;
    }
    public List<站点信息> 根据地区信息站点类型获取EC站点(web站点检索Model data) {
        List<站点信息> myStations=new ArrayList<>();
        SqlSessionFactory sqlSessionFactoryEc = SqlSessionFactoryUtil.getECFactory();
        SqlSession sessionEc = sqlSessionFactoryEc.openSession();
        try {

            EcSurfaceDao ecDao = sessionEc.getMapper(EcSurfaceDao.class);
            myStations= ecDao.GetECStationByIdAndStationType(data.getID(),data.getStationLevlString());
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            sessionEc.close();
        }
        return myStations;
    }
    public List<ECDataModel> 根据站点列表预报时效获取EC数值预报站点预报(数值预报检索Model data){
        SqlSessionFactory sqlSessionFactoryEc = SqlSessionFactoryUtil.getECFactory();
        SqlSession sessionEc = sqlSessionFactoryEc.openSession();

        EcSurfaceDao ecDao = sessionEc.getMapper(EcSurfaceDao.class);
        StringBuilder sb1=new StringBuilder();
        sb1.append("(");
        for (String myid:data.getID().split(",")
        ) {
            sb1.append("'").append(myid).append("'").append(",");
        }
        data.setID(sb1.substring(0,sb1.length()-1)+")");
        List<ECDataModel> datas ;
        try{
            datas=ecDao.getECSurfaceBySXIdsDataType(data.getTableName(),data.getID(), data.getDateString(), data.getDataType(),data.getYbSx());
        }
        finally {
            sessionEc.close();
        }
        return datas;
    }

}
