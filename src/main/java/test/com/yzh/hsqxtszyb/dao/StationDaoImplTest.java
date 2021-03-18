package com.yzh.hsqxtszyb.dao; 

import com.yzh.hsqxtszyb.model.数值预报检索Model;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* StationDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>3�� 12, 2021</pre> 
* @version 1.0 
*/ 
public class StationDaoImplTest {
    StationDaoImpl stationDao;
@Before
public void before() throws Exception {
    stationDao=new StationDaoImpl();
} 

@After
public void after() throws Exception { 
} 



@Test
public void test根据站点列表预报时效获取EC数值预报站点预报() throws Exception {

    数值预报检索Model ind=new 数值预报检索Model("('53466')","2021-03-14 20:00:00","WIU10,WIV10",12,"20210314");
    var sss=stationDao.根据站点列表预报获取EC数值预报站点预报(ind);
    sss.size();
//TODO: Test goes here...
}


} 
