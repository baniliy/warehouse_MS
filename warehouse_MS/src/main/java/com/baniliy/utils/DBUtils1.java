package com.baniliy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtils1 {
    private static DruidDataSource dataSource;
    private static Connection conns ;

    static {
        try {
//            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            ResourceBundle resource = ResourceBundle.getBundle("jdbc");
            String driver=resource.getString("driverClassName");
            String url=resource.getString("url");
            String user=resource.getString("username");
            String password=resource.getString("password");
            // 创建数据库连接池
            dataSource=new DruidDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setMaxActive(20);
            dataSource.setInitialSize(10);
            // 定时销毁物理连接
            dataSource.setFilters("stat");
            dataSource.setTimeBetweenEvictionRunsMillis(6000);
            dataSource.setMinEvictableIdleTimeMillis(3000);
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        try {
            conns = dataSource.getConnection();//从数据库连接池中获取连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conns;
    }
    public static DataSource getDataSource(){
        //System.out.println("dataSource="+dataSource);
        return dataSource;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        try {
            conns.commit(); // 提交 事务
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conns.close(); // 关闭连接，资源资源
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        if (conns != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                conns.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conns.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
