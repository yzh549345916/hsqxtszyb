package com.yzh.hsqxtszyb.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.yzh.hsqxtszyb.util.SqlSessionFactoryUtil;

public class BaseDaoImpl implements BaseDao {


    public SqlSession getSqlSession() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //sqlSessionFactory.openSession(true)自动提交事务
        return sqlSessionFactory.openSession();
    }
    public SqlSession getSqlSessionSqlserver() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlServerSessionFactory();
        //sqlSessionFactory.openSession(true)自动提交事务
        return sqlSessionFactory.openSession();
    }

}
