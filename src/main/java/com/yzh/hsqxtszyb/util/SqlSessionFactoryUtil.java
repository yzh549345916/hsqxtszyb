package com.yzh.hsqxtszyb.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSessionFactory sqlserverSessionFactory;
    private static SqlSessionFactory sqlXzjxhSessionFactory;
    private static SqlSessionFactory sqlZNWGSessionFactory;
    private static SqlSessionFactory sqlECFactory;
    private static SqlSessionFactory sqlHuanJingFactory;
    /**
     * 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为中心的。
     * SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
     * 而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例。
     *
     * SqlSessionFactoryBuilder 最佳作用域是方法作用域（也就是局部方法变量）
     * SqlSessionFactory 最佳作用域是应用作用域
     * SqlSession 最佳的作用域是请求或方法作用域
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }
    public static SqlSessionFactory getSqlHuanjingSessionFactory() {
        if (sqlHuanJingFactory != null) {
            return sqlHuanJingFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlHuanJingFactory = new SqlSessionFactoryBuilder().build(inputStream,"developmentHuanJing");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlHuanJingFactory;
    }
    public static SqlSessionFactory getSqlServerSessionFactory() {
        if (sqlserverSessionFactory != null) {
            return sqlserverSessionFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlserverSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlserverSessionFactory;
    }
    public static SqlSessionFactory getXzjxhSessionFactory() {
        if (sqlXzjxhSessionFactory != null) {
            return sqlXzjxhSessionFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlXzjxhSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"developmentxzjxh");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlXzjxhSessionFactory;
    }
    public static SqlSessionFactory getZNWGSessionFactory() {
        if (sqlZNWGSessionFactory != null) {
            return sqlZNWGSessionFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlZNWGSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development127");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlZNWGSessionFactory;

    }
    public static SqlSessionFactory getECFactory() {
        if (sqlECFactory != null) {
            return sqlECFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlECFactory = new SqlSessionFactoryBuilder().build(inputStream,"developmentEC");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlECFactory;

    }
    private static SqlSessionFactory sqlZNWG203SessionFactory;
    public static SqlSessionFactory getZNWG203SessionFactory() {
        if (sqlZNWG203SessionFactory != null) {
            return sqlZNWG203SessionFactory;
        }
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
            sqlZNWG203SessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"developmentZNWG203");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlZNWG203SessionFactory;
    }
}
